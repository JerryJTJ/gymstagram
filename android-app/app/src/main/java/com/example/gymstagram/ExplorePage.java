package com.example.gymstagram;

import android.app.Activity;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.example.gymstagram.databinding.FragmentExplorePageBinding;
import com.example.gymstagram.model.Comment;
import com.example.gymstagram.model.Post;
import com.example.gymstagram.retrofit.ApiClient;

import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ExplorePage extends Fragment {

    private FragmentExplorePageBinding binding;
    private PostsViewModel viewModel;
    private View view;
    LinearLayout linearLayout;

    public ExplorePage() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {
        binding = FragmentExplorePageBinding.inflate(inflater, container, false);
        view = inflater.inflate(R.layout.fragment_explore_page, container, false);

        return binding.getRoot();
    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        linearLayout = (LinearLayout)view.findViewById(R.id.explorePagelinearlayout);
        linearLayout.setOrientation(LinearLayout.VERTICAL);
        linearLayout.setVerticalScrollBarEnabled(true);

        viewModel = new ViewModelProvider(requireActivity()).get(PostsViewModel.class);

        // just displaying all posts for now
        Call<List<Post>> allPostsResponse = ApiClient.getPostService().getAllPosts();
        allPostsResponse.enqueue(new Callback<List<Post>>() {
            @Override
            public void onResponse(Call<List<Post>> call, Response<List<Post>> response) {
                if(response.isSuccessful()){
                    List<Post> posts = response.body();
                    //show recent posts first
                    Collections.reverse(posts);
                    if (posts != null){
                        for (int i = 0; i < posts.size(); i++) {
                            String userID = posts.get(i).getUserId();
                            String photo = null;
                            if (!posts.get(i).getphoto().isEmpty()){

                                photo = posts.get(i).getphoto().get(0);
                            }

                            String id = posts.get(i).getId();
                            String dateAndLocation = convertTime(posts.get(i).getTimestamp());
                            String postContent = posts.get(i).getDescription();

                            int numLikesToDisplay = posts.get(i).getNumLikes();

                            boolean liked = posts.get(i).getLikes().contains(MainActivity.userId);

                            CardForPost cardView = new CardForPost(getContext(), liked, numLikesToDisplay);
                            List<Comment> commentsList = posts.get(i).getComments();

                            cardView.updateCard(id, userID,dateAndLocation,postContent, numLikesToDisplay, photo, commentsList);
                            cardView.setBackground(ContextCompat.getDrawable(getContext(), R.drawable.card));

                            linearLayout.addView(cardView);
                        }
                    }
                }
            }

            @Override
            public void onFailure(Call<List<Post>> call, Throwable t) {

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