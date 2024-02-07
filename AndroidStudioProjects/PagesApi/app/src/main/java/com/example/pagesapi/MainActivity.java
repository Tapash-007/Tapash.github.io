package com.example.pagesapi;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.widget.NestedScrollView;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    PageAdapter adapter;

    RecyclerView recyclerView;

    ArrayList<ModelClass.Data> pageList = new ArrayList<>();

    private int page = 1;
    private int limit = 2;

    private NestedScrollView nestedSV;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        nestedSV = findViewById(R.id.nested);
        recyclerView = findViewById(R.id.rec);
        adapter = new PageAdapter (this,pageList);
        recyclerView.setAdapter(adapter);

        nestedSV.setOnScrollChangeListener(new NestedScrollView.OnScrollChangeListener() {
            @Override
            public void onScrollChange(@NonNull NestedScrollView v, int scrollX, int scrollY, int oldScrollX, int oldScrollY) {
                if (scrollY == v.getChildAt(0).getMeasuredHeight() - v.getMeasuredHeight()) {
                    page++;
                    apiCall(page, limit);
                    Log.d(getClass().getSimpleName(), "onScrollChange: ");
                }
            }
        });


        apiCall(page, limit);
    }

   private void apiCall (int page, int limit) {

        if (page > limit) {
           Toast.makeText(this, "That's all the data..", Toast.LENGTH_SHORT).show();
           return;
         
        }

       PageInterface pageInterface;

        pageInterface = RetrofitClient.getRetrofit().create(PageInterface.class);

       Call<ModelClass> apiCall = pageInterface.getData(page);

       apiCall.enqueue(new Callback<ModelClass>() {
           @SuppressLint("NotifyDataSetChanged")
           @Override
           public void onResponse(Call<ModelClass> call, Response<ModelClass> response) {
               Log.d(getClass().getSimpleName(), "onResponse: " + response.body());

               if (page == 1) pageList.clear();
               for (int i=0; i<response.body().data.size(); i++) {
                   Log.d(getClass().getSimpleName(), "onResponse: " + response.body().data.get(i).getFirst_name());

                   pageList.add(new ModelClass.Data(
                         response.body().data.get(i).getId(),
                           response.body().data.get(i).getEmail(),response.body().data.get(i).getFirst_name(),
                           response.body().data.get(i).getLast_name(),response.body().data.get(i).getAvatar()));

               }

               adapter.notifyDataSetChanged();

           }

           @Override
           public void onFailure(Call<ModelClass> call, Throwable t) {
               Log.d(getClass().getSimpleName(), "onFailure: " + t.getMessage());

           }
       });

   }

}

