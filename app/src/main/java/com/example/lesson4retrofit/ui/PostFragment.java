package com.example.lesson4retrofit.ui;
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
import com.example.lesson4retrofit.databinding.FragmentPostBinding;
import com.example.lesson4retrofit.models.Post;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PostFragment extends Fragment {
    private FragmentPostBinding binding;
    private int id;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentPostBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        if (getArguments() != null) {
            Post model;
            model = (Post) getArguments().getSerializable("ff_key");
            Log.d("tag", model.getTitle());

            binding.etTitle.setText(model.getTitle());
            binding.etContent.setText(model.getContent());
            binding.etGroup.setText(String.valueOf(model.getGroup()));
            binding.etUser.setText(String.valueOf(model.getUser()));
            id = model.getId();
         ;
        }

        binding.btnUpgrade.setOnClickListener(view1 -> {
            RetrofitClient.getApi().update(String.valueOf(id+""), new Post(binding.etTitle.getText().toString().trim(),
                    binding.etContent.getText().toString().trim(),
                    Integer.valueOf(binding.etGroup.getText().toString().trim()),
                    Integer.valueOf(binding.etUser.getText().toString().trim()))).enqueue(new Callback<Post>() {
                @Override
                public void onResponse(Call<Post> call, Response<Post> response) {
                    open();
                }

                @Override
                public void onFailure(Call<Post> call, Throwable t) {

                }
            });
        });

        binding.btnCreate.setOnClickListener(view1 -> {
            RetrofitClient.getApi().createMocker(new Post(binding.etTitle.getText().toString().trim(),
                    binding.etContent.getText().toString().trim(),
                    Integer.valueOf(binding.etGroup.getText().toString().trim()),
                    Integer.valueOf(binding.etUser.getText().toString().trim()))).enqueue(new Callback<Post>() {
                @Override
                public void onResponse(Call<Post> call, Response<Post> response) {
                    open();
                }

                @Override
                public void onFailure(Call<Post> call, Throwable t) {

                }
            });
        });

    }

    private void open() {

        NavController navController = Navigation.findNavController(requireActivity(), R.id.nav_host_fragment);
        navController.navigate(R.id.formFragment);
    }

}