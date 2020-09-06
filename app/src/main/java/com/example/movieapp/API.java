package com.example.movieapp;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface API {
    @GET("movie/")
    Call<ApiResponse> getMovies(
            @Query("api_key") String apiKey,
            @Query("language") String language,
            @Query("sort_by") String sortBy);

}
