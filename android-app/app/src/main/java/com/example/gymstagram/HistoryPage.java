package com.example.gymstagram;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.example.gymstagram.databinding.FragmentHistoryPageBinding;
import com.example.gymstagram.model.Workout;
import com.example.gymstagram.retrofit.ApiClient;

import java.util.Collections;
import java.util.List;

public class HistoryPage extends Fragment {

    LinearLayout linearLayout;
    private View view;
    private FragmentHistoryPageBinding binding;

    public HistoryPage() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentHistoryPageBinding.inflate(inflater, container, false);
        view = inflater.inflate(R.layout.fragment_history_page, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        linearLayout = (LinearLayout)view.findViewById(R.id.historyPageLinearLayout);
        linearLayout.setOrientation(LinearLayout.VERTICAL);
        linearLayout.setVerticalScrollBarEnabled(true);
        Call<List<Workout>> allWorkoutsResponse = ApiClient.getWorkoutService().getAllWorkouts();
        allWorkoutsResponse.enqueue(new Callback<List<Workout>>() {
            @Override
            public void onResponse(Call<List<Workout>> call, Response<List<Workout>> response) {
                if (response.isSuccessful()) {
                    List<Workout> workouts = response.body();
                    Collections.reverse(workouts);
                    if (workouts != null) {
                        for (int i = 0; i< workouts.size(); i++){
                            String workoutName = workouts.get(i).getName();
                            int numReps = workouts.get(i).getReps();
                            int numSets = workouts.get(i).getSets();
                            int weight = workouts.get(i).getWeight();
                            int totalVol = numReps*numSets*weight;

                            CardForWorkoutHistory cardView = new CardForWorkoutHistory(getContext());
                            cardView.updateCard(workoutName, totalVol);
//                            cardView.setBackground(ContextCompat.getDrawable(getContext(), R.drawable.card));
                            if(i == 0){
                                cardView.setTop(20);

                            }
                            linearLayout.addView(cardView);

                        }
                    }
                }
            }

            @Override
            public void onFailure(Call<List<Workout>> call, Throwable t) {

            }
        });
    }
}
