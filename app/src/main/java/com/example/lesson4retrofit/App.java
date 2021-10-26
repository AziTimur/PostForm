package com.example.lesson4retrofit;

import android.app.Application;

import com.example.lesson4retrofit.data.Ghibli;
import com.example.lesson4retrofit.data.RetrofitClient;
import com.example.lesson4retrofit.repository.GhibliRepository;

public class App extends Application {
    public static Ghibli api;
    public  static GhibliRepository repository;

    @Override
    public void onCreate() {
        super.onCreate();
        api=new RetrofitClient().provideGhibli();
        repository=new GhibliRepository();
    }
}
