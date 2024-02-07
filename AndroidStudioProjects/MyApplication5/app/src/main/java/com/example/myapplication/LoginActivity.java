package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.AuthFailureError;
import com.android.volley.NetworkResponse;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.Volley;
import com.example.myapplication.utils.VolleyMultipartRequest;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;

import java.util.HashMap;
import java.util.Map;

public class LoginActivity extends AppCompatActivity {

    TextInputEditText User_email, User_Password;

    MaterialButton login, signup;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        User_email = findViewById(R.id.user_email);
        User_Password = findViewById(R.id.user_password);
        login = findViewById(R.id.login);
        signup = findViewById(R.id.signup);


        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                validation();
            }

        });


        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(LoginActivity.this, MainActivity.class);
                startActivity(i);
            }
        });


    }


    void validation() {
        if (User_email.getText().toString().isEmpty())
            Toast.makeText(this, "enter username", Toast.LENGTH_SHORT).show();
        else if (User_Password.getText().toString().isEmpty()) {
            Toast.makeText(this, "enter password", Toast.LENGTH_SHORT).show();
        } else if (signup.getText().toString().isEmpty()) {
            Toast.makeText(this, "enter signup", Toast.LENGTH_SHORT).show();
        } else {
            apiCall();
        }
    }


    private void apiCall() {
        /*StringRequest stringRequest = new StringRequest(
                Request.Method.POST, "https://fce4-103-89-61-50.ngrok-free.app/", new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.d(getClass().getSimpleName(), "onResponse" + response);
                Toast.makeText(LoginActivity.this, "logged Successfully!", Toast.LENGTH_SHORT).show();

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d(getClass().getSimpleName(), "onErrorResponse:" + error.toString());
            }
        }){
            @Nullable
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {

                HashMap<String, String> params = new HashMap<>();
                params.put("email",User_email.getText().toString());
                params.put("password",User_Password.getText().toString());
                return params;
            }
        };

        Volley.newRequestQueue(this).add(stringRequest);
*/
        VolleyMultipartRequest request = new VolleyMultipartRequest(Request.Method.POST, "https://fce4-103-89-61-50.ngrok-free.app/api/v1/login", new Response.Listener<NetworkResponse>() {
            @Override
            public void onResponse(NetworkResponse response) {
                Toast.makeText(LoginActivity.this, "login successfully", Toast.LENGTH_SHORT).show();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d(getClass().getSimpleName(), "onErrorResponse: " + error.getMessage() + "\n" + error.networkResponse.data);

            }
        }) {
            @Nullable
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                HashMap<String, String> param = new HashMap<String, String>();
                param.put("email", User_email.getText().toString());
                param.put("password", User_Password.getText().toString());
                return param;
            }
        };

        Volley.newRequestQueue(this).add(request);
    }
}









