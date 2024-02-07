package com.example.articles;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class ArticlesAdapter extends RecyclerView.Adapter<ArticlesAdapter.ViewHolder> {

    Context context;

    ArrayList<ModelClass> articleList;

    public ArticlesAdapter(Context context, ArrayList<ModelClass> articleList) {
        this.context = context;
        this.articleList = articleList;
    }

    @NonNull
    @Override
    public ArticlesAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.single_view2,parent,false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ArticlesAdapter.ViewHolder holder, @SuppressLint("RecyclerView") int position) {
        holder.id.setText(articleList.get(position).getId().toString());
        holder.title.setText(articleList.get(position).getTitle());
        holder.summary.setText(articleList.get(position).getSummary());
        holder.newsSite.setText(articleList.get(position).getNewsSite());

        Picasso.with(context).load(articleList.get(position).getImage()).into(holder.image);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context, articleList.get(position).getId().toString(), Toast.LENGTH_SHORT).show();

                Intent intent = new Intent(context,GetDetails.class);

                intent.putExtra("id",articleList.get(position).getId());
                intent.putExtra("title",articleList.get(position).getTitle());
                intent.putExtra("summary",articleList.get(position).getSummary());
                intent.putExtra("newsSite",articleList.get(position).getNewsSite());
                intent.putExtra("image",articleList.get(position).getImage());

                context.startActivity(intent);


            }
        });

    }

    @Override
    public int getItemCount() {
        return articleList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView id,title,summary,newsSite;

        ImageView image;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            id = itemView.findViewById(R.id.id);
            title = itemView.findViewById(R.id.title);
            summary = itemView.findViewById(R.id.summary);
            newsSite = itemView.findViewById(R.id.newsSite);
            image = itemView.findViewById(R.id.img_api);

        }
    }
}
