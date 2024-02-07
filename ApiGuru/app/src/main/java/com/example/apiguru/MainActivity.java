package com.example.apiguru;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        apiCall();


    }

    private void apiCall() {

        GuruInterface guruInterface;

        guruInterface = RetrofitClient.getRetrofit().create(GuruInterface.class);

        Call<String> apiCall = guruInterface.getData();

        apiCall.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
             //   Log.d(getClass().getSimpleName(), "onResponse: " + response.body());

                try {
                    JSONObject object = new JSONObject(response.body());

                    JSONObject object1 = object.getJSONObject("1forge.com");
                    JSONObject object2 = object1.getJSONObject("versions");
                    JSONObject object3 = object2.getJSONObject("0.0.1");
                    JSONObject object4 = object3.getJSONObject("info");
                    JSONObject object5 = object4.getJSONObject("contact");


                    object5.getString("email");
                    object5.getString("name");
                    object5.getString("url");

                    Log.d(getClass().getSimpleName(), "onResponse: " + object5.toString());




                } catch (Exception e) {
                    e.printStackTrace();
                }


            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {
                Log.d(getClass().getSimpleName(), "onFailure: "+t.getMessage());
            }
        });


    }
}