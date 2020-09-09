package com.example.movieapp;

import java.util.List;

public class Movie {

    private String popularity;
    private String vote_count;
    private String video;
    private String poster_path;
    private String id;
    private String adult;
    private String backdrop_path;
    private String original_language;
    private String original_title;
    private List<Integer> genre_ids;
    private String title;
    private Double vote_average;
    private String overview;
    private String release_date;

    public String getPopularity() {
        return popularity;
    }

    public String getVote_count() {
        return vote_count;
    }

    public String getVideo() {
        return video;
    }

    public String getPoster_path() {
        String path = "https://image.tmdb.org/t/p/w500" + poster_path;
        return path;
    }
    public String getPath() {
        return poster_path;
    }
    public String getId() {
        return id;
    }

    public String getAdult() {
        return adult;
    }

    public String getBackdrop_path() {
        String path = "https://image.tmdb.org/t/p/w500" + backdrop_path;
        return path;
    }

    public String getBack_path() {
        return backdrop_path;
    }

    public String getOriginal_language() {
        return original_language;
    }

    public String getOriginal_title() {
        return original_title;
    }

    public List<Integer> getGenre_ids() {
        return genre_ids;
    }

    public String getTitle() {
        return title;
    }

    public Double getVote_average() {
        return vote_average;
    }

    public String getOverview() {
        return overview;
    }

    public String getRelease_date() {
        return release_date;
    }
}


