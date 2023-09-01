package com.ahmadroni.movieproapp.service;

import com.ahmadroni.movieproapp.model.MovieResult;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;

public interface MovieDataService {
    @GET("movie/popular")
    Call<MovieResult> getPopularMovie(@Header("Authorization") String authorization);
}
