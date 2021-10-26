package com.example.lesson4retrofit.repository;

import androidx.lifecycle.MutableLiveData;

import com.example.lesson4retrofit.App;
import com.example.lesson4retrofit.models.Film;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class GhibliRepository {
    public MutableLiveData<List<Film>> getFilms(){
        MutableLiveData<List<Film>> films= new MutableLiveData<>();
        App.api.getFilms().enqueue(new Callback<List<Film>>() {
            @Override
            public void onResponse(Call<List<Film>> call, Response<List<Film>> response) {
                if (response.isSuccessful()&response.body()!=null){
                    films.setValue(response.body());
                }else {
                    films.setValue(null);
                }
            }

            @Override
            public void onFailure(Call<List<Film>> call, Throwable t) {
                films.setValue(null);
            }
        });
        return films;
    }
}
