package com.example.login;


import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
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


/**
 * A simple {@link Fragment} subclass.
 */
public class HomeFragment extends Fragment{

//    private TextView mTextViewResult;
    private RequestQueue mQueue;
    TextView userToken;

    private RecyclerView recyclerView;
    private GridLayoutManager gridLayoutManager;

    private RecyclerView.Adapter adapter;
    private String urlJsonArry = "https://estate29.com/server2/api/category-listing";
    private List<HomeFragmentList> listItems;


    ImageView category ;

    public HomeFragment() {
        // Required empty public constructor
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment

        View v = inflater.inflate(R.layout.fragment_home, container, false);
        recyclerView = (RecyclerView) v.findViewById(R.id.recyclerViewHome);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        RecyclerView myrv = (RecyclerView) v.findViewById(R.id.recyclerViewHome);

        userToken = (TextView) v.findViewById(R.id.userTokenKey);


        //      MyAdapter myadapter = new MyAdapter(this,listItems);
        myrv.setLayoutManager(new GridLayoutManager(getActivity(),2));

        listItems = new ArrayList<>();

        mQueue = Volley.newRequestQueue(getActivity().getApplicationContext());

        // ----------- category listing -------------------
        categoryListing();

        category = (ImageView) v.findViewById(R.id.categoryTitle);



        return v;
    }


    /*------------------------------------------------------------------------------
                        Home Category Listing
    -------------------------------------------------------------------------------*/

    private void categoryListing(){
        JsonArrayRequest req = new JsonArrayRequest(urlJsonArry,
                new Response.Listener<JSONArray>() {

                    @Override
                    public void onResponse(JSONArray response) {

                        try {
                            for (int i = 0; i < response.length(); i++) {
                                Toast.makeText(getActivity().getApplicationContext(), "Hello", Toast.LENGTH_LONG).show();

                                JSONObject category = (JSONObject) response.get(i);

                                String category_title = category.getString("category_title");
                                String price = category.getString("price");

                                HomeFragmentList item = new HomeFragmentList(
                                        category.getString("category_title"),
                                        category.getString("price")

                                );

                                listItems.add(item);
                                adapter = new HomeFragmentAdapter(listItems,getActivity().getApplicationContext());
                                recyclerView.setAdapter(adapter);
                            }

                        } catch (JSONException e) {
                            e.printStackTrace();
                            Toast.makeText(getActivity().getApplicationContext(),
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




    // -------------- [ Test Function ] --------------------

}
