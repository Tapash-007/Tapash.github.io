package com.example.apicall;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;

import android.os.Bundle;
import android.util.Log;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

@SuppressLint("MissingInflatedId")
public class getdata extends AppCompatActivity {

    ArrayList<ModelApi> arrList = new ArrayList<>();

    RecyclerView recView;

    Reclistadapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recView = findViewById(R.id.recView);

//        arrList.add(new ModelApi("Cricket","sixes and four","Star_sports", R.id.image_api));
//        arrList.add(new ModelApi("Hockey","goals and point","Star_sports", R.id.image_api));
//        arrList.add(new ModelApi("Chess","check and mate","Star_sports", R.id.image_api));
        adapter = new Reclistadapter(this,arrList);
        recView.setAdapter(adapter);
        recView = findViewById(R.id.recView);


        apiCall();

    }


    public void apiCall(){

    StringRequest request = new StringRequest(
            Request.Method.GET, "https://api.spaceflightnewsapi.net/v3/articles", new Response.Listener<String>() {
     @SuppressLint("NotifyDataSetChanged")
     @Override
     public void onResponse(String response) {
         Log.d(getClass().getSimpleName(), "onResponse: " + response);

         try {
             JSONArray array = new JSONArray(response);

             for (int i = 0; i < array.length(); i++) {
                 JSONObject object = new JSONObject(array.get(i).toString());
                 Log.d(getClass().getSimpleName(), "onResponse: " + i + " " + object.getString("imageUrl"));

                 arrList.add(new ModelApi(
                         object.getString("title"), object.getString("summary"), object.getString("newsSite"),object.getString("imageUrl")

                 ));
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





