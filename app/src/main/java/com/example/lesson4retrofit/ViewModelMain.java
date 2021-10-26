package com.example.lesson4retrofit;

import android.view.View;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.lesson4retrofit.App;
import com.example.lesson4retrofit.models.Film;

import java.util.List;

public class ViewModelMain extends ViewModel {
    public LiveData<List<Film>> films;

    void getFilms() {

        films = App.repository.getFilms();
    }
}
