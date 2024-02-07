package com.example.apidriver.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.util.Log;

import com.android.volley.Request;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.apidriver.model.ModelClass;
import com.example.apidriver.R;
import com.example.apidriver.controller.DriverAdapter;
import com.example.apidriver.interfaces.Driver_Interface;
import com.example.apidriver.network.RetrofitClient;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    DriverAdapter adapter;

    RecyclerView recyclerView;

    ProgressDialog progressDialog;

    ArrayList<ModelClass.MRDataTable.DriverTable.DriversTable> driverList = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerView = findViewById(R.id.rec);
        adapter = new DriverAdapter(this, driverList);
        recyclerView.setAdapter(adapter);

        progressDialog = new ProgressDialog(this);
        progressDialog.setTitle("Loading");
        progressDialog.setMax(100);
        progressDialog.setMessage("Wait while loading.....");
        progressDialog.setCancelable(false);
        progressDialog.show();

//        apiCallVolley();
        apiCall();
    }

    @Override
    protected void onStart() {
        super.onStart();

    }


    public void apiCallVolley() {

        StringRequest request = new StringRequest(
                Request.Method.GET, "https://ergast.com/api/f1/drivers.json", new com.android.volley.Response.Listener<String>() {
            @SuppressLint("NotifyDataSetChanged")
            @Override
            public void onResponse(String response) {
                Log.d(getClass().getSimpleName(), "onResponse: " + response);

                try {

                    JSONObject object = new JSONObject(response);
                    JSONObject dataObj = object.getJSONObject("MRData");
                    JSONObject tableObj = dataObj.getJSONObject("DriverTable");
                    JSONArray array = tableObj.getJSONArray("Drivers");

                    progressDialog.dismiss();
                    driverList.clear();


                    for (int i = 0; i < array.length(); i++) {

                        JSONObject driverItem = new JSONObject(array.get(i).toString());
//                        Log.d(getClass().getSimpleName(), "onResponse: " + i + " " + object.getString("driverItem"));

                        driverList.add(new ModelClass.MRDataTable.DriverTable.DriversTable(
                                driverItem.getString("driverId"), driverItem.getString("url"), driverItem.getString("givenName"),
                                driverItem.getString("familyName"), driverItem.getString("dateOfBirth"),
                                driverItem.getString("nationality")
                        ));

                    }

                    adapter.notifyDataSetChanged();

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }, new com.android.volley.Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d(getClass().getSimpleName(), "error : " + error);
                progressDialog.dismiss();

            }
        }
        );

        Volley.newRequestQueue(this).add(request);

    }


    private void apiCall() {

        Driver_Interface driverInterface;

        driverInterface = RetrofitClient.getRetrofit().create(Driver_Interface.class);

        Call<ModelClass> apiCall = driverInterface.getData();

        apiCall.enqueue(new Callback<ModelClass>() {
            @SuppressLint("NotifyDataSetChanged")
            @Override
            public void onResponse(Call<ModelClass> call, Response<ModelClass> response) {
                Log.d(getClass().getSimpleName(), "onResponse: " + response.body());

                Log.d(getClass().getSimpleName(), "onResponse LIST : " + response.body().MRData.toString());
                Log.d(getClass().getSimpleName(), "onResponse LIST : " + response.body().MRData.DriverTable.Drivers.toString());

                progressDialog.dismiss();
                driverList.clear();
                for (int i = 0; i < response.body().MRData.DriverTable.Drivers.size(); i++) {
                    driverList.add(new ModelClass.MRDataTable.DriverTable.DriversTable(response.body().MRData.DriverTable.Drivers.get(i).driverId,
                            response.body().MRData.DriverTable.Drivers.get(i).getUrl(), response.body().MRData.DriverTable.Drivers.get(i).getGivenName(),
                            response.body().MRData.DriverTable.Drivers.get(i).getFamilyName(),
                            response.body().MRData.DriverTable.Drivers.get(i).getDateOfBirth(), response.body().MRData.DriverTable.Drivers.get(i).getNationality()));
                }
                adapter.notifyDataSetChanged();

                Log.d(getClass().getSimpleName(), "onResponse: " + driverList);
            }

            @Override
            public void onFailure(Call<ModelClass> call, Throwable t) {
                progressDialog.dismiss();
            }
        });

    }

}


