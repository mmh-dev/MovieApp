package com.example.movieapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SwitchCompat;
import androidx.core.content.res.ResourcesCompat;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    MovieAdapter adapter;
    RecyclerView recyclerView;
    SwipeRefreshLayout swipeRefreshLayout;
    SwitchCompat switcher;
    TextView byPopularity, byRating;
    String sort = "popularity.desc";
    List<Movie> movies = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        swipeRefreshLayout = findViewById(R.id.swipe_layout);
        switcher = (SwitchCompat) findViewById(R.id.switcher);
        byPopularity = findViewById(R.id.by_popularity_text);
        byRating = findViewById(R.id.by_rating_text);

        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new GridLayoutManager(this, 2));
        loadMovies(sort);

        switcher.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (switcher.isChecked()){
                    byRating.setTextColor(ResourcesCompat.getColor(getResources(), R.color.colorAccent, null));
                    byPopularity.setTextColor(ResourcesCompat.getColor(getResources(), R.color.colorWhite, null));
                    sort = "vote_average.desc";
                    loadMovies(sort);
                    adapter.notifyDataSetChanged();

                }

                if (!movies.isEmpty() && !switcher.isChecked()){
                    byPopularity.setTextColor(ResourcesCompat.getColor(getResources(), R.color.colorAccent, null));
                    byRating.setTextColor(ResourcesCompat.getColor(getResources(), R.color.colorWhite, null));
                    sort = "popularity.desc";
                    loadMovies(sort);
                    adapter.notifyDataSetChanged();
                }
            }
        });

        byPopularity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                byPopularity.setTextColor(ResourcesCompat.getColor(getResources(), R.color.colorAccent, null));
                byRating.setTextColor(ResourcesCompat.getColor(getResources(), R.color.colorWhite, null));
                sort = "popularity.desc";
                loadMovies(sort);
                switcher.setChecked(false);
                adapter.notifyDataSetChanged();
            }
        });

        byRating.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                byRating.setTextColor(ResourcesCompat.getColor(getResources(), R.color.colorAccent, null));
                byPopularity.setTextColor(ResourcesCompat.getColor(getResources(), R.color.colorWhite, null));
                sort = "vote_average.desc";
                loadMovies(sort);
                switcher.setChecked(true);
                adapter.notifyDataSetChanged();
            }
        });

        swipeRefreshLayout.setOnRefreshListener(() -> {
            loadMovies(sort);
        });
    }

    private void loadMovies(String sort) {
        swipeRefreshLayout.setRefreshing(true);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://api.themoviedb.org/3/discover/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        API api = retrofit.create(API.class);
        Call<ApiResponse> request = api.getMovies(Util.API_KEY, "en-US", sort, false, false);
        request.enqueue(new Callback<ApiResponse>() {
            @Override
            public void onResponse(Call<ApiResponse> call, Response<ApiResponse> response) {
                if (response.isSuccessful()){
                    movies.clear();
                    movies = response.body().getResults();
                    Log.i("tag1", String.valueOf(movies.size()));

                    for (int i = 0; i <movies.size() ; i++) {
                        if (movies.get(i).getPath() == null){
                            movies.remove(i);
                        }
                    }

                    Log.i("tag2", String.valueOf(movies.size()));

                    adapter = new MovieAdapter(movies, MainActivity.this);
                    recyclerView.setAdapter(adapter);
                    swipeRefreshLayout.setRefreshing(false);
                }
            }

            @Override
            public void onFailure(Call<ApiResponse> call, Throwable t) {
                swipeRefreshLayout.setRefreshing(false);
                Toast.makeText(MainActivity.this, "No server response", Toast.LENGTH_SHORT).show();
                Log.i("tag", t.getMessage());
            }
        });
    }
}