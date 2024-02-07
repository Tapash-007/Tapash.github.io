package com.example.articles;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {


    ArticlesAdapter adapter;

    RecyclerView recyclerView;

    ArrayList<ModelClass> articleList = new ArrayList<>();

    private String TAG = getClass().getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.recView3);
        adapter = new ArticlesAdapter(this,articleList);
        recyclerView.setAdapter(adapter);

        apiCall();
    }

    private void apiCall () {

        ArticleInterface articleInterface;

        articleInterface = RetrofitClient.getRetrofit().create(ArticleInterface.class);

        Call<ArrayList<ModelClass>> apiCall = articleInterface.getData();

        apiCall.enqueue(new Callback<ArrayList<ModelClass>>() {
            @Override
            public void onResponse(Call<ArrayList<ModelClass>> call, Response<ArrayList<ModelClass>> response) {
                Log.d(TAG, "onResponse: " + response.body());

                articleList.clear();
                for (int i = 0; i < response.body().size(); i++) {

                    articleList.add(new ModelClass(
                            response.body().get(i).getImage(), response.body().get(i).getId(),
                            response.body().get(i).getTitle(),response.body().get(i).getSummary(),
                            response.body().get(i).getNewsSite()));

                }
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onFailure(Call<ArrayList<ModelClass>> call, Throwable t) {
                Log.d(TAG, "onFailure: " + t.getMessage());

            }
        });

    }

}