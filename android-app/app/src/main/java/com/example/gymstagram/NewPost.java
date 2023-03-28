package com.example.gymstagram;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.fragment.NavHostFragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.gymstagram.databinding.FragmentNewPostBinding;
import android.widget.EditText;
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

        binding.buttonAddLocation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Handler here
            }
        });

        binding.buttonLinkToWorkout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Handler here
            }
        });

        binding.buttonShare.setOnClickListener(new View.OnClickListener() {
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
                String title = binding.title.getText().toString();
                Post newPost = new Post(title, description);
                viewModel.addPost(newPost);
            }
        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

}