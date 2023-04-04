package com.example.gymstagram;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.fragment.NavHostFragment;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.gymstagram.databinding.FragmentNewPostBinding;
import com.example.gymstagram.model.Post;
import com.example.gymstagram.retrofit.ApiClient;

import java.text.SimpleDateFormat;
import java.util.Date;

public class NewPost extends Fragment {

    private FragmentNewPostBinding binding;
    private PostsViewModel viewModel;

    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {
        View view = inflater.inflate(R.layout.fragment_new_post, container, false);

        binding = FragmentNewPostBinding.inflate(inflater, container, false);
        return binding.getRoot();

    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        viewModel = new ViewModelProvider(getActivity()).get(PostsViewModel.class);

        binding.buttonUploadMedia.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Handler here
            }
        });



        binding.buttonCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NavHostFragment.findNavController(NewPost.this)
                        .navigate(R.id.action_newPost_pop);
            }
        });

        binding.buttonPost.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Handler here
                String description = binding.description.getText().toString();
                // TODO:get actual username
                String userID = "someUser1";
                Date dNow = new Date();
                SimpleDateFormat ft = new SimpleDateFormat("yyMMddhhmmssMs");
                String id = ft.format(dNow);
                Post post = new Post(id, userID, description);
                Call<Post> newPost = ApiClient.getPostService().createPost(post);
                newPost.enqueue(new Callback<Post>() {
                    @Override
                    public void onResponse(Call<Post> call, Response<Post> response) {
                        if(response.isSuccessful()){
                            Log.i("Add Post", "Created post successfully");
                        }
                    }
                    @Override
                    public void onFailure(Call<Post> call, Throwable t) {
                        Log.e("Add Post", "onFailure: Could not add post");
                    }
                });

//                viewModel.addPost(newPost);
            }
        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

}