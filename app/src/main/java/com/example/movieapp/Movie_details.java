package com.example.movieapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;

import com.google.gson.Gson;
import com.squareup.picasso.Picasso;

public class Movie_details extends AppCompatActivity {

    Movie movie;
    ImageView poster;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_details);

        Gson gson = new Gson();
        String object = getIntent().getStringExtra("movie");
        movie = (Movie) gson.fromJson(object, Movie.class);
        poster = findViewById(R.id.poster);

        if (movie.getBack_path() == null){
            Picasso.get().load(movie.getPoster_path()).into(poster);
        }
        else {
            Picasso.get().load(movie.getBackdrop_path()).into(poster);
        }
    }
}