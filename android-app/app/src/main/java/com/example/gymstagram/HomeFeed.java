package com.example.gymstagram;
import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import android.app.Activity;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import androidx.core.app.ActivityCompat;
import androidx.fragment.app.Fragment;
import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModelProvider;

import com.example.gymstagram.databinding.FragmentHomeFeedBinding;
import com.example.gymstagram.model.Post;
import com.example.gymstagram.model.User;
import com.example.gymstagram.retrofit.ApiClient;
import com.example.gymstagram.retrofit.RetrofitService;
import com.example.gymstagram.retrofit.UserAPI;

import androidx.core.content.ContextCompat;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HomeFeed extends Fragment {

    private FragmentHomeFeedBinding binding;
    private PostsViewModel viewModel;
    private View view;
    LinearLayout linearLayout;
    List<String> following;
    private int READ_STORAGE_PERMISSION_REQUEST_CODE = 41;

    public boolean checkPermissionForReadExtertalStorage() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            int result = getContext().checkSelfPermission(android.Manifest.permission.READ_EXTERNAL_STORAGE);
            return result == PackageManager.PERMISSION_GRANTED;
        }
        return false;
    }
    public void requestPermissionForReadExtertalStorage() throws Exception {
        try {
            ActivityCompat.requestPermissions((Activity) getContext(), new String[]{android.Manifest.permission.READ_EXTERNAL_STORAGE},
                    READ_STORAGE_PERMISSION_REQUEST_CODE);
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
    }
    public HomeFeed() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {
        binding = FragmentHomeFeedBinding.inflate(inflater, container, false);
        view = inflater.inflate(R.layout.fragment_home_feed, container, false);
        //TODO: Move the photo permissions to a more logical page
        boolean permissionsGiven = checkPermissionForReadExtertalStorage();
        if (!permissionsGiven){
            try {
                requestPermissionForReadExtertalStorage();
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
        return binding.getRoot();
    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        linearLayout = (LinearLayout)view.findViewById(R.id.homefeedlinearlayout);
        linearLayout.setOrientation(LinearLayout.VERTICAL);
        linearLayout.setVerticalScrollBarEnabled(true);

        viewModel = new ViewModelProvider(requireActivity()).get(PostsViewModel.class);
        String userID = MainActivity.userId;
        RetrofitService retrofitService = new RetrofitService();
        UserAPI userAPI = retrofitService.getRetrofit().create(UserAPI.class);
        Call<User> currUser = userAPI.getUserById(userID);
        currUser.enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                if(response.isSuccessful()){
                    following = response.body().getFollowing();
                    if (following!= null){
                        for (int i = 0; i<following.size(); i++){
                            String followedUserID = following.get(i);

                            //Get all posts from followed user
                            Call<List<Post>> allPostsByUser = ApiClient.getPostService().getAllPostsByUserId(followedUserID);

                            allPostsByUser.enqueue(new Callback<List<Post>>() {
                                @Override
                                public void onResponse(Call<List<Post>> call, Response<List<Post>> response) {
                                    if (response.isSuccessful()) {
                                        List<Post> posts = response.body();
                                        //show recent posts first
                                        Collections.reverse(posts);
                                        if (posts != null){
                                            for (int i = 0; i < posts.size(); i++) {
                                                String userID = posts.get(i).getUserId();
                                                String id = posts.get(i).getId();
                                                String dateAndLocation = convertTime(posts.get(i).getTimestamp());
                                                String postContent = posts.get(i).getDescription();
                                                int numLikesToDisplay = posts.get(i).getNumLikes();
                                                boolean liked = posts.get(i).getLikes().contains(MainActivity.userId);

                                                CardForPost cardView = new CardForPost(getContext(), liked, numLikesToDisplay);

                                                cardView.updateCard(id, userID,dateAndLocation,postContent, numLikesToDisplay);

                                                cardView.setBackground(ContextCompat.getDrawable(getContext(), R.drawable.card));

                                                linearLayout.addView(cardView);
                                            }
                                        }
                                    } else {
                                        // Handle HTTP error
                                    }
                                }

                                @Override
                                public void onFailure(Call<List<Post>> call, Throwable t) {
                                    // Handle network error
                                    Log.e("hhhh", "Network error: " + t.getMessage());
                                }
                            });

                        }
                    }
                }
            }
            @Override
            public void onFailure(Call<User> call, Throwable t) {
                Log.e("hhhh", "could not get user by userid");
            }
        });

    }
    public String convertTime(long time){
        Date date = new Date(time);
        Format format = new SimpleDateFormat("yyyy MM dd HH:mm:ss");
        return format.format(date);
    }
    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}