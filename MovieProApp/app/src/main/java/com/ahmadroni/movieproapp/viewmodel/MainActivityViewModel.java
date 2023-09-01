package com.ahmadroni.movieproapp.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.ahmadroni.movieproapp.model.MovieModel;
import com.ahmadroni.movieproapp.model.MovieRepo;

import java.util.List;

public class MainActivityViewModel extends AndroidViewModel {
    private MovieRepo movieRepo;
    public MainActivityViewModel(@NonNull Application application) {
        super(application);
        movieRepo = new MovieRepo(application);
    }

    public LiveData<List<MovieModel>> getAllMovie(){
        return movieRepo.getMutableLiveData();
    }
}
