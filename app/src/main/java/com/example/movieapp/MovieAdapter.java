package com.example.movieapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.List;

public class MovieAdapter extends RecyclerView.Adapter <MovieAdapter.Holder> {

    List<Movie> movies;
    Context context;

    public MovieAdapter(List<Movie> movies, Context context) {
        this.movies = movies;
        this.context = context;
    }

    @NonNull
    @Override
    public MovieAdapter.Holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.movie_card, parent, false);
        return new Holder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MovieAdapter.Holder holder, int position) {
        if (movies.get(position).getPoster_path() == null || movies.get(position).getPoster_path().isEmpty()){
            holder.poster.setImageResource(R.mipmap.ic_launcher);
        }
        else {
            Picasso.get().load(movies.get(position).getPoster_path())
                    .into(holder.poster);
        }
    }

    @Override
    public int getItemCount() {
        return movies.size();
    }

    public class Holder extends RecyclerView.ViewHolder {

        ImageView poster;

        public Holder(@NonNull View itemView) {
            super(itemView);
            poster = itemView.findViewById(R.id.poster);
        }
    }
}
