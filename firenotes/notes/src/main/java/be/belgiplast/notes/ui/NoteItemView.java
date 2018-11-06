package be.belgiplast.notes.ui;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MediatorLiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.Observer;
import android.content.Context;
import android.content.res.Resources;
import android.icu.text.SimpleDateFormat;
import android.support.annotation.Nullable;
import android.support.constraint.ConstraintLayout;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.ToggleButton;

import java.util.Date;

import be.belgiplast.notes.R;
import be.belgiplast.notes.model.Note;

public class NoteItemView extends ConstraintLayout {
    private EditText name;
    private EditText description;
    private TextView name_rd;
    private TextView description_rd;
    private TextView date;
    private ToggleButton btn;

    private ProxyNote note;
    private SimpleDateFormat fmt = new SimpleDateFormat("MM-dd");

    public NoteItemView(Context context) {
        this(context, null);
    }

    public NoteItemView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public NoteItemView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        inflater.inflate(R.layout.note_item, this, true);
        name_rd = this.findViewById(R.id.note_read_title);
        description_rd = this.findViewById(R.id.note_read_content);
        name = this.findViewById(R.id.note_write_title);
        description = this.findViewById(R.id.note_write_content);
        name.setVisibility(GONE);
        description.setVisibility(GONE);
        description_rd.setVisibility(GONE);
        date = this.findViewById(R.id.note_date);
        btn = this.findViewById(R.id.note_btn);
        btn.setOnClickListener(new OnClickListener(){

            @Override
            public void onClick(View v) {
                name.setVisibility(!btn.isChecked() ? View.GONE : View.VISIBLE);
                description.setVisibility(btn.isChecked() ? View.GONE : View.VISIBLE);
                name_rd.setVisibility(btn.isChecked() ? View.GONE : View.VISIBLE);
//                description_rd.setVisibility(btn.isChecked() ? View.GONE : View.VISIBLE);
            }
        });
        this.setFocusable(true);
        note = new ProxyNote();
        note.observeForever(new Observer<Note>(){

            @Override
            public void onChanged(@Nullable Note note) {
                name.setText(note.getName());
                description.setText(note.getDescription());
                name_rd.setText(note.getName());
                description_rd.setText(note.getDescription());
                date.setText(fmt.format(note.getModification()));
            }
        });
        name.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                note.setName(s.toString());
            }
        });
        description.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                note.setDescription(s.toString());
            }
        });
    }

    public void bind(Note repo) {
        if (repo == null) {
            Resources resources = getResources();
        } else {
            this.note.setBackend(repo);
        }
    }

    public LiveData<Note> getNote() {
        return note;
    }

    private class ProxyNote extends MediatorLiveData<Note> {
        private MutableLiveData<String> name;
        private MutableLiveData<String> description;
        Date date;

        public ProxyNote() {
            setValue(new Note());
            name = new MutableLiveData<>();
            description = new MutableLiveData<>();

            addSource(name,name-> {
                try {
                    getValue().setName(name);
                    getValue().setModification(new Date());
                }catch (NullPointerException npe){}
            });
            addSource(description,desc-> {
                try {
                    getValue().setDescription(desc);
                    getValue().setModification(new Date());
                }catch (NullPointerException npe){}
            });
        }

        public void setName(String name){
            this.name.setValue(name);
        }

        public void setDescription(String desc){
            this.description.setValue(desc);
        }

        public String getName(){
            return name.getValue();
        }

        public String getDescription(){
            return description.getValue();
        }

        public Date getModification(){
            return getValue().getModification();
        }

        public Date getTimestamp(){
            return getValue().getTimestamp();
        }

        public Note getBackend() {
            return getValue();
        }

        public void setBackend(Note backend) {
            setValue(backend);
            name.setValue(backend.getName());
            description.setValue(backend.getDescription());
            date = backend.getTimestamp();
        }
    }
}
