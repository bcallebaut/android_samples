package be.belgiplast.notes;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.Signature;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.security.cert.CertificateFactory;
import java.util.List;
import java.util.concurrent.Executors;

import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;

import be.belgiplast.notes.api.NoteService;
import be.belgiplast.notes.business.Note;
import be.belgiplast.notes.business.UserRepository;
import be.belgiplast.notes.data.FirestoreDatasource;
import be.belgiplast.notes.data.NoteRepository;
import be.belgiplast.notes.db.NoteDatabase;
import be.belgiplast.notes.db.NoteLocalCache;
import be.belgiplast.notes.network.Controller;
import be.belgiplast.notes.ui.AddNoteActivity;
import be.belgiplast.notes.ui.NoteViewModel;
import be.belgiplast.notes.ui.NotesAdapter;
import be.belgiplast.notes.ui.ViewModelFactory;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {
    private static String LAST_SEARCH_QUERY = "last_search_query";
    private static String DEFAULT_QUERY = "Android";

    private NoteRepository repo;

    private NoteViewModel viewModel;
    private NotesAdapter adapter;

    private FirestoreDatasource ds;

    public NoteLocalCache provideCache(Context context) {
        NoteDatabase database = NoteDatabase.getDatabase(context);
        return new NoteLocalCache(database.noteDao(), Executors.newSingleThreadExecutor());
    }

    public NoteService create(){
        HttpLoggingInterceptor logger = new HttpLoggingInterceptor();
        logger.setLevel(HttpLoggingInterceptor.Level.BASIC);

        OkHttpClient client = new OkHttpClient.Builder().addInterceptor(logger).build();
        return new Retrofit.Builder()
                .baseUrl(NoteService.BASE_URL)
                .client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(NoteService.class);
    }

    public NoteRepository provideGithubRepository(Context context) {
        repo = new NoteRepository(create(), provideCache(context));
        return repo;
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final PackageManager packageManager = this.getPackageManager();
        final List<PackageInfo> packageList = packageManager.getInstalledPackages(PackageManager.GET_SIGNATURES);

        StringBuilder sb = new StringBuilder();

        try{
            PackageInfo nfo = packageManager.getPackageInfo("be.belgiplast.notes",PackageManager.GET_SIGNATURES);
            dumpSignatureInfo(packageManager, sb, nfo);
        }catch (Exception e){
            e.printStackTrace();
        }

        for (PackageInfo p : packageList) {
            dumpSignatureInfo(packageManager, sb, p);
        }

        //Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        //setSupportActionBar(toolbar);

        //repo = new NoteRepository(this);

        Log.i("certs",sb.toString());

        viewModel = ViewModelProviders.of(this, new ViewModelFactory(provideGithubRepository(this))).get(NoteViewModel.class);

        // add dividers between RecyclerView's row items
        DividerItemDecoration decoration = new DividerItemDecoration(this, DividerItemDecoration.VERTICAL);
        ((android.support.v7.widget.RecyclerView)this.findViewById(R.id.list)).addItemDecoration(decoration);
        setupScrollListener();

        initAdapter();
        String query = DEFAULT_QUERY;
        try {
            query = savedInstanceState.getString(LAST_SEARCH_QUERY) != null ? savedInstanceState.getString(LAST_SEARCH_QUERY) : DEFAULT_QUERY;
        }catch (Exception e){}
        viewModel.searchRepo(0);
        //initSearch(query);



        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, AddNoteActivity.class);
                startActivityForResult(intent,0x1234);
            }
        });
    }

    private void dumpSignatureInfo(PackageManager packageManager, StringBuilder sb, PackageInfo p) {
        final String strName = p.applicationInfo.loadLabel(packageManager).toString();
        final String strVendor = p.packageName;


        sb.append("\n" + strName + " / " + strVendor + "\n");

        final Signature[] arrSignatures = p.signatures;
        for (final Signature sig : arrSignatures) {
            /*
             * Get the X.509 certificate.
             */
            final byte[] rawCert = sig.toByteArray();
            InputStream certStream = new ByteArrayInputStream(rawCert);

            StringBuilder b = new StringBuilder();

            try {
                CertificateFactory certFactory = CertificateFactory.getInstance("X509");
                X509Certificate x509Cert = (X509Certificate) certFactory.generateCertificate(certStream);

                b.append("PackageInfo : " +  strName +"\n");
                b.append("Package name : " +  strVendor +"\n");
                b.append("Certificate subject: " + x509Cert.getSubjectDN() + "\n");
                b.append("Certificate issuer: " + x509Cert.getIssuerDN() + "\n");
                b.append("Certificate serial number: " + x509Cert.getSerialNumber() + "\n");
                b.append("\n");

                Log.i("certs",b.toString());
                sb.append(b.toString());


            }
            catch (CertificateException e) {
                // e.printStackTrace();
            }
        }
    }

    private void initAdapter() {
        adapter = new NotesAdapter();
        ((android.support.v7.widget.RecyclerView)this.findViewById(R.id.list)).setAdapter(adapter);
        viewModel.getRepos().observe(this, new Observer<List<Note>>(){
            @Override
            public void onChanged(@Nullable List<Note> repos) {
                Log.d("Activity", String.format("list: %d",repos.size()));
                showEmptyList(repos.size() == 0);
                adapter.submitList(repos);
            }
        });
        viewModel.getNetworkErrors().observe(this, new Observer<String>(){
            @Override
            public void onChanged(@Nullable String s) {
                Toast.makeText(MainActivity.this, "\uD83D\uDE28 Wooops " + s, Toast.LENGTH_LONG).show();
            }
        });
    }

    private void showEmptyList(boolean show) {
        if (show) {
            this.findViewById(R.id.emptyList).setVisibility(View.VISIBLE);
            this.findViewById(R.id.list).setVisibility(View.GONE);
        } else {
            this.findViewById(R.id.emptyList).setVisibility(View.GONE);
            this.findViewById(R.id.list).setVisibility(View.VISIBLE);
        }
    }

    private void setupScrollListener() {
        final LinearLayoutManager layoutManager = (LinearLayoutManager)((android.support.v7.widget.RecyclerView)this.findViewById(R.id.list)).getLayoutManager();
        ((android.support.v7.widget.RecyclerView)this.findViewById(R.id.list)).addOnScrollListener(new RecyclerView.OnScrollListener() {
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                int totalItemCount = layoutManager.getItemCount();
                int visibleItemCount = layoutManager.getChildCount();
                int lastVisibleItem = layoutManager.findLastVisibleItemPosition();

                viewModel.listScrolled(visibleItemCount, lastVisibleItem, totalItemCount);
            }
        });
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        switch (resultCode){
            case 0:
                //repo.createNote(data.getStringExtra("name"),data.getStringExtra("description"));
                break;
            case 1:
                break;
            default:
                break;
        }
        super.onActivityResult(requestCode, resultCode, data);
    }
}
