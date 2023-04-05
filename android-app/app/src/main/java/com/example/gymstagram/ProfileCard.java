package com.example.gymstagram;

import android.content.res.ColorStateList;
import android.graphics.PorterDuff;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.gymstagram.databinding.FragmentHomeFeedBinding;
import com.example.gymstagram.databinding.FragmentProfileCardBinding;
import com.example.gymstagram.retrofit.RetrofitService;
import com.example.gymstagram.retrofit.UserAPI;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ProfileCard#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ProfileCard extends Fragment {
    private FragmentProfileCardBinding binding;
    private String cardUserId;
    private String cardUsername;
    private boolean cardIsFollowing;

    public ProfileCard(String id, String username, boolean isFollowing) {
        cardUserId = id;
        cardUsername = username;
        cardIsFollowing = isFollowing;
    }

    public static ProfileCard newInstance(String id, String username, boolean isFollowing) {
        ProfileCard fragment = new ProfileCard(id, username, isFollowing);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentProfileCardBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        // Set the username
        binding.username.setText(cardUsername);

        //If already following, change button to be following mode
        if(cardIsFollowing){
            handleButtonAppearance(true);
        }

        //Create retrofit service
        RetrofitService retrofitService = new RetrofitService();
        UserAPI userAPI = retrofitService.getRetrofit().create(UserAPI.class);

        //Follow button
        binding.buttonFollow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //Follow handler
                if(!cardIsFollowing){
                    Call<Void> followUserCall = userAPI.followUser(MainActivity.userId, cardUserId);

                    followUserCall.enqueue(new Callback<Void>() {
                        @Override
                        public void onResponse(Call<Void> call, Response<Void> response) {
                            if (response.isSuccessful()) {
                                //Follow success
                                handleButtonAppearance(true);
                            } else {
                                Toast.makeText(getActivity(), "Error following", Toast.LENGTH_SHORT).show();
                            }
                        }

                        @Override
                        public void onFailure(Call<Void> call, Throwable t) {
                            Toast.makeText(getActivity(), "Error following", Toast.LENGTH_SHORT).show();
                        }
                    });
                }

                //Unfollow handler
                else if(cardIsFollowing){
                    Call<Void> unfollowUserCall = userAPI.unfollowUser(MainActivity.userId, cardUserId);

                    unfollowUserCall.enqueue(new Callback<Void>() {
                        @Override
                        public void onResponse(Call<Void> call, Response<Void> response) {
                            if (response.isSuccessful()) {
                                //Follow success
                                handleButtonAppearance(false);
                            } else {
                                Toast.makeText(getActivity(), "Error unfollowing", Toast.LENGTH_SHORT).show();
                            }
                        }

                        @Override
                        public void onFailure(Call<Void> call, Throwable t) {
                            Toast.makeText(getActivity(), "Error unfollowing", Toast.LENGTH_SHORT).show();
                        }
                    });
                }
                else{
                    Toast.makeText(getActivity(), "Error handling follow/unfollow", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void handleButtonAppearance(boolean newFollow){
        Button followButton = getView().findViewById(R.id.button_follow);
        if(newFollow){
            //Just clicked follow
            followButton.setText("-");
            ColorStateList greenColorStateList = ColorStateList.valueOf(getResources().getColor(R.color.green));
            followButton.setBackgroundTintList(greenColorStateList);
            cardIsFollowing = true;
        }
        else{
            //Just clicked unfollow
            followButton.setText("+");
            ColorStateList greenColorStateList = ColorStateList.valueOf(getResources().getColor(R.color.purple));
            followButton.setBackgroundTintList(greenColorStateList);
            cardIsFollowing = false;
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}