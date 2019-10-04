package com.example.login;


import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;


/**
 * A simple {@link Fragment} subclass.
 */
public class UserLoggedInFragment extends Fragment {

    private RequestQueue mQueue;

    public UserLoggedInFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_user_logged_in, container, false);
    }


    // -------------- [ Get User Data ] ------------------------
    private void getUserData() {


        String urlJsonObj = "https://estate29.com/server2/api/user-detail";


         final String tokenKey = "eyJ0eXAiOiJKV1QiLCJhbGciOiJSUzI1NiIsImp0aSI6Ijc4NzU0ZDQ2MTkzY2I0MDJiMjMwNWM0ZmJiNjE2NzE3MzMxYzMyZWQ0NTNlZGE2ZmZkZTk4MTQwOWFiMjM5NmE4ODUwNTcyOTVlMjc3NjkxIn0.eyJhdWQiOiIxIiwianRpIjoiNzg3NTRkNDYxOTNjYjQwMmIyMzA1YzRmYmI2MTY3MTczMzFjMzJlZDQ1M2VkYTZmZmRlOTgxNDA5YWIyMzk2YTg4NTA1NzI5NWUyNzc2OTEiLCJpYXQiOjE1NzAwOTYwNzAsIm5iZiI6MTU3MDA5NjA3MCwiZXhwIjoxNjAxNzE4NDcwLCJzdWIiOiI3MiIsInNjb3BlcyI6W119.C7Jk8b3IF45-qjPwPOuIUn14mKXNxZt9HbznzrB1rWpR7l09ybo0rWtbHcFnLWItwSMuE68uqDSKdrWBglhZZta6u-WMUcIEqKXCE0o17jWe-ncf6RLLaziZAKOnHRObGyWYUrnPES7y15q_GmRRTne1zoesk9jUxt7o6OWAZsSlHsqkB67D57ScDnGC5o-pC6rL1YCWDsSKToxodc6xGkoqvnqIYGBF3aZY4tqpIybUTpcAdBOCT0wRn_OBJWDhtZ1B0JBb1c60sjSFpBrbP5P4Fccc2hBhFexeePrluyeunIUVroM_1Bl69ME4DV4uCvy8V5AD3EPHFPz3yyqe_Nwd9WbYWLtWVnvUTCMnLTkwM9TKQ7KUOAfOEPjy8KyMOlOXsL8R80XmnDT4kni5eFeGAobcilk_mctiOdGESodWWy46YEmRAXYVfgdvXi6gLD6HOgg9P-NDbSINKpmK5vGBOF-FgnOtdvNW4x7m4FbiH0TfosBSsADOE2LxN1hd2-8iFSGL2QvOFQ5t0I8088e8m7wMOU_LxuwF1AFrqY9QsNaEkyu7VneeGtrXOXO6UfD8Kj2EYahSe6tYqSHdHcpx3pcUxakl-GdqSO3wfHROl5R_5smJYsiE06AuitEcOh0MxZnU21xqlZqLYVViAgBF0gEGYE6Yi9Bq4xvMoWg";


        JsonObjectRequest jsonObjReq = new JsonObjectRequest(Request.Method.GET,
                urlJsonObj, null, new Response.Listener<JSONObject>() {

            @Override
            public void onResponse(JSONObject response) {

                try {
                    // Parsing json object response
                    // response will be a json object

                    JSONObject user = response.getJSONObject("data");
                    String phoneNum = user.getString("phone");
                    String user_id = user.getString("user_id");

                } catch (JSONException e) {
                    e.printStackTrace();
                    Toast.makeText(getActivity().getApplicationContext(),
                            "Error: " + e.getMessage(),
                            Toast.LENGTH_LONG).show();
                }
//                hidepDialog();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();
            }
        })
        {
            @Override


            public Map getHeaders() throws AuthFailureError {
                HashMap headers = new HashMap();
                headers.put("Content-Type", "application/json");
                headers.put("Authorization", tokenKey);
                return headers;
            }
        };

        mQueue.add(jsonObjReq);
    }

}
