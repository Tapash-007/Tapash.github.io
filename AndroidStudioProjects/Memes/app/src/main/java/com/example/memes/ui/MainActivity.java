package com.example.memes.ui;

import static com.example.memes.network.RetrofitClient.getRetrofit;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;

import com.example.memes.R;
import com.example.memes.controller.MemesAdapter;
import com.example.memes.interfaces.MemesInterface;
import com.example.memes.model.ModelClass;
import com.example.memes.network.RetrofitClient;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    RecyclerView recyclerView;

    MemesAdapter adapter;

    ArrayList<ModelClass.Data.Memes> memesList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.rec);
        adapter = new MemesAdapter(this,memesList);
        recyclerView.setAdapter(adapter);


    }

    @Override
    protected void onStart() {
        super.onStart();
        apiCall();
    }

    private void apiCall() {

      MemesInterface memesInterface;

      memesInterface = RetrofitClient.getRetrofit().create(MemesInterface.class);

      Call<ModelClass> apiCall = memesInterface.getData();

      apiCall.enqueue(new Callback<ModelClass>() {

          @Override
          public void onResponse(Call<ModelClass> call, Response<ModelClass> response) {
              Log.d(getClass().getSimpleName(), "onResponse: " + response.body());

              memesList.clear();

              for (int i=0; i< response.body().data.memes.size(); i++)  {
                  memesList.add(new ModelClass.Data.Memes(response.body().data.memes.get(i).getId(),
                          response.body().data.memes.get(i).getName(),response.body().data.memes.get(i).getUrl(),
                          response.body().data.memes.get(i).getWidth(),response.body().data.memes.get(i).getHeight(),
                          response.body().data.memes.get(i).getBox_count()));

              }

              adapter.notifyDataSetChanged();
          }

          @Override
          public void onFailure(Call<ModelClass> call, Throwable t) {

          }
      });

  }
}