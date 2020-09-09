package com.example.movieapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.List;

public class MovieAdapter extends RecyclerView.Adapter <MovieAdapter.Holder> {

    List<Movie> movies;
    Context context;
    RecycleOnClickListener listener;

    public interface RecycleOnClickListener{
        void onItemClick (int position);
    }

    public MovieAdapter(List<Movie> movies, Context context) {
        this.movies = movies;
        this.context = context;
    }

    public void setOnItemClickListener (RecycleOnClickListener listener) {
        this.listener = listener;
    }

    @NonNull
    @Override
    public MovieAdapter.Holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.movie_card, parent, false);
        return new Holder(view, listener);
    }

    @Override
    public void onBindViewHolder(@NonNull MovieAdapter.Holder holder, int position) {
        Picasso.get().load(movies.get(position).getPoster_path())
                .placeholder(R.mipmap.ic_launcher)
                .into(holder.poster);

        holder.progressView.setProgress((int)(movies.get(position).getVote_average()*10), true);
        holder.rating.setText(String.valueOf(movies.get(position).getVote_average()));
    }

    @Override
    public int getItemCount() {
        return movies.size();
    }

    public class Holder extends RecyclerView.ViewHolder {

        ImageView poster;
        TextView rating;
        ProgressBar progressView;

        public Holder(@NonNull View itemView, final RecycleOnClickListener listener) {
            super(itemView);
            poster = itemView.findViewById(R.id.poster);
            progressView = itemView.findViewById(R.id.progressView);
            rating = itemView.findViewById(R.id.rating);
            itemView.setOnClickListener(view -> {
                int position = getAdapterPosition();
                listener.onItemClick(position);
            });
        }
    }
}
