package be.belgiplast.plugins.tasks;

import android.content.Context;
import android.databinding.ObservableArrayList;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import be.belgiplast.plugins.ObservableProvider;
import be.belgiplast.plugins.RequestQueueProvider;

public class TasksNetworker {
    private RequestQueue queue;
    private Context context;
    private JsonArrayRequest getAllTasks;
    private JsonObjectRequest addTask;

    private ObservableArrayList dataBackend;

    public TasksNetworker(Context context) throws ClassCastException{
        this.context = context;
        queue = ((RequestQueueProvider)context).getRequestQueue();
        dataBackend = ((ObservableProvider<MutableTaskImpl>)context).getObservable();
        getAllTasks = createGetAllRequest("http://192.168.1.10:18080/myapp/notes?id=1");
    }

    private JsonArrayRequest createGetAllRequest(String url) {
        return new JsonArrayRequest
                (Request.Method.GET, url, null, new Response.Listener<JSONArray>() {

                    @Override
                    public void onResponse(JSONArray response) {
                        for (int i = 0; i < response.length(); i++) {
                            MutableTaskImpl task = new MutableTaskImpl();
                            try {
                                task.setId(response.getInt(0));
                                task.setName(response.getString(2));
                                task.setDescription(response.getString(3));
                                dataBackend.add(task);
                            } catch (JSONException je) {
                            }
                        }

                    }
                }, new Response.ErrorListener() {

                    @Override
                    public void onErrorResponse(VolleyError error) {
                        System.out.println(error);
                    }
                });
    }

    private JsonObjectRequest createAddTaskRequest(String url, MutableTaskImpl task) {
        JSONObject req = new JSONObject();
        try {
            req.put("name", task.getName());
            req.put("description", task.getDescription());
        } catch (JSONException e) {
            e.printStackTrace();
            return null;
        }

        return new JsonObjectRequest
                (Request.Method.POST, url, req, new Response.Listener<JSONObject>() {

                    @Override
                    public void onResponse(JSONObject response) {
                        System.out.println("post request response");
                        MutableTaskImpl task = new MutableTaskImpl();
                        try {
                            task.setId(response.getInt("id"));
                            task.setName(response.getString("name"));
                            task.setDescription(response.getString("description"));
                            dataBackend.add(task);
                        } catch (JSONException e) {}
                    }
                }, new Response.ErrorListener() {

                    @Override
                    public void onErrorResponse(VolleyError error) {
                        System.out.println(error);
                    }
                });
    }

    public JsonArrayRequest getGetAllTasks() {
        return getAllTasks;
    }

    public JsonObjectRequest getAddTask(MutableTaskImpl task) {
        return createAddTaskRequest("http://192.168.1.10:18080/myapp/notes",task);
    }
}
