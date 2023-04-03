package com.example.gymstagram;
import java.util.List;

import android.media.Image;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.LinearLayout;

import androidx.fragment.app.Fragment;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.navigation.fragment.NavHostFragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.gymstagram.databinding.FragmentHomeFeedBinding;
import androidx.core.content.ContextCompat;

import com.example.gymstagram.CardForPost;
public class HomeFeed extends Fragment {

    private FragmentHomeFeedBinding binding;
    private PostsViewModel viewModel;
    private View view;
    LinearLayout linearLayout;

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

        return binding.getRoot();
    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        linearLayout = (LinearLayout)view.findViewById(R.id.homefeedlinearlayout);
        linearLayout.setOrientation(LinearLayout.VERTICAL);
        linearLayout.setVerticalScrollBarEnabled(true);

        //adding default posts to show up on home feed, this should be replaced later
        String username1 = "DefaultUser1";
        String dateAndLocation1 = "04/02/2023 21:18:21, CIF, Waterloo";
        String postContent1 = "Gym 41 is a group of independent personal fitness professionals providing you with the best results inside one gym. In addition to professional training, we give food! \n\nCome join us for an intro session this Friday...";
        CardForPost cardView1 = new CardForPost(getContext());
        cardView1.updateCard(username1,dateAndLocation1,postContent1);
        cardView1.setBackground(ContextCompat.getDrawable(getContext(), R.drawable.card));
        linearLayout.addView(cardView1);
        String username2 = "DefaultUser2";
        String dateAndLocation2 = "04/01/2023 21:18:21, CIF, Waterloo";
        String postContent2 = "App restart successful without requiring a re-install";
        CardForPost cardView2 = new CardForPost(getContext());
        cardView2.updateCard(username2,dateAndLocation2,postContent2);
        cardView2.setBackground(ContextCompat.getDrawable(getContext(), R.drawable.card));
        linearLayout.addView(cardView2);

        viewModel = new ViewModelProvider(requireActivity()).get(PostsViewModel.class);
        List<Post> posts = viewModel.getPosts();

        if (posts != null){
            for (int i = 0; i < posts.size(); i++) {
                String username = posts.get(i).getTitle();
                String dateAndLocation = posts.get(i).getDateCreated() + ", CIF, Waterloo";
                String postContent = posts.get(i).getDescription();
                CardForPost cardView = new CardForPost(getContext());
                cardView.updateCard(username,dateAndLocation,postContent);
                cardView.setBackground(ContextCompat.getDrawable(getContext(), R.drawable.card));

                linearLayout.addView(cardView);
            }
        }

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}