package com.example.teams;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.util.Log;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;

public class recycle_view2 extends AppCompatActivity {

    ArrayList<ModelApi2> teamList = new ArrayList<>();

    TeamAdapter adapter;

    RecyclerView recyclerView;

    ArrayList<DataClass.Data> dataArrayList = new ArrayList<>();

    private int teams;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        recyclerView = findViewById(R.id.recView2);
        adapter = new TeamAdapter(this, dataArrayList);
        recyclerView.setAdapter(adapter);

        apiCallThroughRetrofit();

    }

    private void apiCallThroughRetrofit() {

        ApiInterface apiInterface;

        apiInterface = RetrofitClient.getRetrofit().create(ApiInterface.class);

        Call<DataClass> apiCall = apiInterface.getData();

//        apiCall.enqueue(new Callback<retrofit2.Response<String>>() {
//            @Override
//            public void onResponse(Call<retrofit2.Response<String>> call, retrofit2.Response<retrofit2.Response<String>> response) {
//                Log.d(getClass().getSimpleName(), "onResponse: " + response);
//            }
//
//            @Override
//            public void onFailure(Call<retrofit2.Response<String>> call, Throwable t) {
//                Log.d(getClass().getSimpleName(), "onFailure: " + t.getMessage());
//            }
//        });
        apiCall.enqueue(new Callback<DataClass>() {
            @Override
            public void onResponse(Call<DataClass> call, retrofit2.Response<DataClass> response) {
                Log.d(getClass().getSimpleName(), "onResponse: " + response.body());

                dataArrayList.clear();
                for (int i = 0; i < response.body().data.size(); i++) {
                    Log.d(getClass().getSimpleName(), "onResponse: " + response.body().data.get(i).getName());

                    dataArrayList.add(new DataClass.Data(
                            response.body().data.get(i).getId(), response.body().data.get(i).getAbbreviation(),
                            response.body().data.get(i).getCity(), response.body().data.get(i).getConference(),response.body().data.get(i).getDivision(),
                            response.body().data.get(i).getFull_name(), response.body().data.get(i).getName()));
                }

                adapter.notifyDataSetChanged();
            }



            @Override
            public void onFailure(Call<DataClass> call, Throwable t) {

            }

        });
    }


            public void apiCallVolley() {

        StringRequest request = new StringRequest(
                Request.Method.GET, "https://balldontlie.io/api/v1/teams", new Response.Listener<String>() {
            @SuppressLint("NotifyDataSetChanged")
            @Override
            public void onResponse(String response) {


                Log.d(getClass().getSimpleName(), "onResponse: " + response);

                try {

                    JSONObject object = new JSONObject(response);

                    JSONArray array = object.getJSONArray("data");

                    teamList.clear();

                    for (int i = 0; i < array.length(); i++) {
                        JSONObject teamitem = new JSONObject(array.get(i).toString());
                        Log.d(getClass().getSimpleName(), "onResponse: " + teamitem);

                        teamList.add(new ModelApi2(
                                String.valueOf(teamitem.getInt("id")), teamitem.getString("abbreviation"),
                                teamitem.getString("city"), teamitem.getString("conference"),
                                teamitem.getString("division"), teamitem.getString("full_name"),
                                teamitem.getString("name")));

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
        )
        {
            @Nullable
            @Override
            protected Map<String, String> getParams () throws AuthFailureError {

            HashMap<String, String> map = new HashMap<>();
            map.put("teams", String.valueOf(teams));

            return map;
        }

        };

        Volley.newRequestQueue(this).add(request);
    }
}
















