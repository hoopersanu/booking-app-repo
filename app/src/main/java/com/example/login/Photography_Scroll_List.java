package com.example.login;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
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

import static android.icu.lang.UCharacter.GraphemeClusterBreak.L;

public class Photography_Scroll_List extends AppCompatActivity {
    private TextView mTextViewResult;
    private RequestQueue mQueue;
    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;
    private String urlJsonArry = "https://jsonplaceholder.typicode.com/users";
    private List<PhotographerListItems> listItems;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_photography__scroll__list);


        recyclerView = (RecyclerView) findViewById(R.id.recyclerViewPhotographer);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        RecyclerView myrv = (RecyclerView) findViewById(R.id.recyclerViewPhotographer);

        mTextViewResult = (TextView) findViewById(R.id.textViewTitle);
        listItems = new ArrayList<>();

        mQueue = Volley.newRequestQueue(this);
        storeListing();

    }

    /* ----------------------------------------------------------------------
                             Store Listing
     -----------------------------------------------------------------------*/

    private void storeListing(){
        JsonArrayRequest req = new JsonArrayRequest(urlJsonArry,
                new Response.Listener<JSONArray>() {

                    @Override
                    public void onResponse(JSONArray response) {

                        try {
                            for (int i = 0; i < response.length(); i++) {
                                Toast.makeText(getApplicationContext(), "Hello", Toast.LENGTH_LONG).show();

                                JSONObject person = (JSONObject) response.get(i);
                                String name = person.getString("name");
                                PhotographerListItems item = new PhotographerListItems(
                                        person.getString("name")
                                );

                                listItems.add(item);
                                adapter = new PhotographerListAdapter(listItems,getApplicationContext());
//                                adapter = new PhotographerListAdapter(listItems,getApplicationContext());
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
