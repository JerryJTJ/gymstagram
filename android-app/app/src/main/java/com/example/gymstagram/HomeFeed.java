package com.example.gymstagram;
import java.util.List;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.LinearLayout;

import androidx.fragment.app.Fragment;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.navigation.fragment.NavHostFragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.gymstagram.databinding.FragmentHomeFeedBinding;
import androidx.core.content.ContextCompat;
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
        viewModel = new ViewModelProvider(requireActivity()).get(PostsViewModel.class);
        linearLayout = (LinearLayout)view.findViewById(R.id.linearlayout);
        linearLayout.setOrientation(LinearLayout.VERTICAL);
        linearLayout.setPadding(70,20,70,70);
        linearLayout.setVerticalScrollBarEnabled(true);

        List<Post> posts = viewModel.getPosts();

        if (posts != null){
            for (int i = 0; i < posts.size(); i++) {
                TextView textView = new TextView(getContext());
                textView.setText("@"+posts.get(i).getTitle() + "\n" + posts.get(i).getDescription());
                textView.setMinHeight(400);
                textView.setTextSize(20);
                textView.setBackground(ContextCompat.getDrawable(getContext(), R.drawable.back));

                textView.setPadding(20,20,20,60);
                TextView dateAndLocationView = new TextView(getContext());
                dateAndLocationView.setText(posts.get(i).getDateCreated() + " , CIF, Waterloo, ON");
                dateAndLocationView.setTextSize(16);
                dateAndLocationView.setY(-75);
                dateAndLocationView.setX(20);

                linearLayout.addView(textView);
                linearLayout.addView(dateAndLocationView);
            }
        }

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}