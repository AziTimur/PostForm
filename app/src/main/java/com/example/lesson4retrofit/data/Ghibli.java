package com.example.lesson4retrofit.data;

import com.example.lesson4retrofit.models.Film;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface Ghibli {
    @GET("/films")
    Call<List<Film>>getFilms();
}
