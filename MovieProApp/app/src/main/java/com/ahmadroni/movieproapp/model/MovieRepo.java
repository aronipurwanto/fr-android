package com.ahmadroni.movieproapp.model;

import android.app.Application;
import android.util.Log;

import androidx.lifecycle.MutableLiveData;

import com.ahmadroni.movieproapp.R;
import com.ahmadroni.movieproapp.service.MovieDataService;
import com.ahmadroni.movieproapp.service.RetrofitInstance;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MovieRepo {
    private ArrayList<MovieModel> movies = new ArrayList<>();
    private MutableLiveData<List<MovieModel>> mutableLiveData = new MutableLiveData<>();
    private Application application;

    public MovieRepo(Application application) {
        this.application = application;
    }

    public ArrayList<MovieModel> getMovies() {
        return movies;
    }

    public MutableLiveData<List<MovieModel>> getMutableLiveData() {
        MovieDataService movieDataService = RetrofitInstance.getMovieService();

        Call<MovieResult> call = movieDataService.getPopularMovie(application.getApplicationContext().getString(R.string.api_token));
        call.enqueue(new Callback<MovieResult>() {
            @Override
            public void onResponse(Call<MovieResult> call, Response<MovieResult> response) {
                Log.i("SUCCESS","Connected to api");
                MovieResult result = response.body();
                if(result != null && result.getResults() != null){
                    movies = (ArrayList<MovieModel>) result.getResults();
                    mutableLiveData.setValue(movies);
                }
            }

            @Override
            public void onFailure(Call<MovieResult> call, Throwable t) {
                Log.e("Error", "Can not connect to api, error: "+ t.getMessage());
            }
        });
        return mutableLiveData;
    }
}
