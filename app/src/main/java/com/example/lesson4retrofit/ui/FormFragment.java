package com.example.lesson4retrofit.ui;

import static java.nio.channels.SocketChannel.open;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.lesson4retrofit.R;
import com.example.lesson4retrofit.data.RetrofitClient;
import com.example.lesson4retrofit.databinding.FragmentFormBinding;
import com.example.lesson4retrofit.databinding.FragmentPostBinding;
import com.example.lesson4retrofit.models.Post;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class FormFragment extends Fragment {
    private FragmentFormBinding binding;
    private FormAdapter adapter;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentFormBinding.inflate(inflater,container,false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        adapter  = new FormAdapter();

        RetrofitClient.getApi().getPosts().enqueue(new Callback<List<Post>>() {
            @Override
            public void onResponse(Call<List<Post>> call, Response<List<Post>> response) {
                if (response.isSuccessful() && response != null){
                    adapter.addItems((ArrayList<Post>) response.body());
                }
            }

            @Override
            public void onFailure(Call<List<Post>> call, Throwable t) {

            }


        });
        binding.rvForm.setAdapter(adapter);
        binding.formFab.setOnClickListener(view1 -> {
            open();
        });

        adapter.setOnItemCLickListener(new FormAdapter.OnItemCLickListener() {
            @Override
            public void clickItem(Post model) {
                Bundle b = new Bundle();
                b.putSerializable("ff_key",model);
                NavController navController = Navigation.findNavController(requireActivity(),R.id.nav_host_fragment);
                navController.navigate(R.id.postFragment,b);
            }
        });

    }

    private void open() {

        NavController navController = Navigation.findNavController(requireActivity(),R.id.nav_host_fragment);
        navController.navigate(R.id.postFragment);

    }
}