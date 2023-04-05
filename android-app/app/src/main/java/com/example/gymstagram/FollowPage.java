package com.example.gymstagram;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.gymstagram.databinding.FragmentFirstBinding;
import com.example.gymstagram.databinding.FragmentFollowPageBinding;
import com.example.gymstagram.databinding.FragmentHomeFeedBinding;
import com.example.gymstagram.model.User;
import com.example.gymstagram.retrofit.MealAPI;
import com.example.gymstagram.retrofit.RetrofitService;
import com.example.gymstagram.retrofit.UserAPI;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FollowPage extends Fragment {

    private FragmentFollowPageBinding binding;
    private ProgressBar progressBar;
    private List<String> currentFollowing;

    public FollowPage() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {

        binding = FragmentFollowPageBinding.inflate(inflater, container, false);
        return binding.getRoot();

        //get list of all users


    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        //Set progress bar to visible while loading
        progressBar = view.findViewById(R.id.progress_bar);
        progressBar.setVisibility(View.VISIBLE);

        LinearLayout container = view.findViewById(R.id.scroll_container);

        //Create retrofit service
        RetrofitService retrofitService = new RetrofitService();
        UserAPI userAPI = retrofitService.getRetrofit().create(UserAPI.class);

        //Get current user
        Call<User> getUserCall = userAPI.getUserById(MainActivity.userId);

        getUserCall.enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                if (response.isSuccessful()) {
                    assert response.body() != null;
                    currentFollowing = response.body().getFollowing();

                    //Only call API and create ProfileCards when currentFollowing is assigned
                    Call<List<User>> getAllUsersCall = userAPI.getAllUsers();

                    getAllUsersCall.enqueue(new Callback<List<User>>() {
                        @Override
                        public void onResponse(Call<List<User>> call, Response<List<User>> response) {
                            if (response.isSuccessful()) {
                                List<User> users = response.body();

                                // Loop through the list of users and add profile cards for each user
                                for (User user : users) {
                                    if(!Objects.equals(user.getId(), MainActivity.userId)){
                                        ProfileCard profileCard = ProfileCard.newInstance(user.getId(), user.getUsername(), currentFollowing.contains(user.getId()));

                                        getChildFragmentManager().beginTransaction()
                                                .add(container.getId(), profileCard)
                                                .commit();
                                    }
                                }
                                //Hide progress bar
                                progressBar.setVisibility(View.GONE);
                            } else {
                                Toast.makeText(getActivity(), "Error loading profiles", Toast.LENGTH_SHORT).show();
                            }
                        }

                        @Override
                        public void onFailure(Call<List<User>> call, Throwable t) {
                            Toast.makeText(getActivity(), "Error loading profiles", Toast.LENGTH_SHORT).show();
                        }
                    });
                } else {
                    Toast.makeText(getActivity(), "Error loading profiles", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {
                Toast.makeText(getActivity(), "Error loading profiles", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

}