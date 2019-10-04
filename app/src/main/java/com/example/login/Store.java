package com.example.login;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class Store extends AppCompatActivity {

    private TextView mTextViewResult;
    private RequestQueue mQueue;
    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;
    private String urlJsonArry = "https://jsonplaceholder.typicode.com/users";
    private List<StoreListItem> listItems;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_store);


        recyclerView = (RecyclerView) findViewById(R.id.recyclerViewStore);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));


        mTextViewResult = (TextView) findViewById(R.id.serviceTitle);
        listItems = new ArrayList<>();

        mQueue = Volley.newRequestQueue(this);
        jsonParse();

    }

    private void jsonParse(){
//        String url = "https://api.myjson.com/bins/kp9wz";

        JsonArrayRequest req = new JsonArrayRequest(urlJsonArry,
                new Response.Listener<JSONArray>() {

                    @Override
                    public void onResponse(JSONArray response) {

                        try {
                            for (int i = 0; i < response.length(); i++) {
                                Toast.makeText(getApplicationContext(), "Hello", Toast.LENGTH_LONG).show();

                                JSONObject person = (JSONObject) response
                                        .get(i);

                                String name = person.getString("name");

                                StoreListItem item = new StoreListItem(
                                        person.getString("name")
                                );

                                listItems.add(item);
                                adapter = new StoreAdapter(listItems,getApplicationContext());
                                recyclerView.setAdapter(adapter);
                            }

                        } catch (JSONException e) {
                            e.printStackTrace();
                            Toast.makeText(getApplicationContext(),
                                    "Error: " + e.getMessage(),
                                    Toast.LENGTH_LONG).show();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();
            }
        });

        mQueue.add(req);
    }
}
