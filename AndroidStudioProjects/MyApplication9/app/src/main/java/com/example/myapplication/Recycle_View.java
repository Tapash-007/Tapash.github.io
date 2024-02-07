package com.example.myapplication;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

@SuppressLint("MissingInflatedId")
public class Recycle_View extends AppCompatActivity {


    ArrayList<ModelAp> memesList = new ArrayList<>();

    ListAdapter adapter;

    RecyclerView recyclerView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.rec1);
        adapter = new ListAdapter(this, memesList);
        recyclerView.setAdapter(adapter);


        apiCall();

    }

    public void apiCall() {

        StringRequest request = new StringRequest(
                Request.Method.GET, "https://api.imgflip.com/get_memes", new Response.Listener<String>() {
            @SuppressLint("NotifyDataSetChanged")
            @Override
            public void onResponse(String response) {
                Log.d(getClass().getSimpleName(), "onResponse: " + response);

                try {
                    JSONObject object = new JSONObject(response);

                    JSONObject dataObject = object.getJSONObject("data");
                    JSONArray array = dataObject.getJSONArray("memes");


                    memesList.clear();

                    for (int i = 0; i < array.length(); i++) {
                        JSONObject memesItem = new JSONObject(array.get(i).toString());
                        Log.d(getClass().getSimpleName(), "onResponse: " + " " + memesItem);

                        memesList.add(new ModelAp(
                                memesItem.getString("url"), memesItem.getString("id"), memesItem.getString("name"), memesItem.getString("width"),
                                memesItem.getString("height"), String.valueOf(memesItem.getInt("box_count")),
                                memesItem.getString("captions")));


                    }

                    adapter.notifyDataSetChanged();

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }


        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d(getClass().getSimpleName(), "error : " + error);

            }
        }
        );
        Volley.newRequestQueue(this).add(request);
    }
}






