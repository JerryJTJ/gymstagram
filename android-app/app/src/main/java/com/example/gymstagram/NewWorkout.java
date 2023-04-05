package com.example.gymstagram;

import android.os.Bundle;
import android.view.View;
import androidx.annotation.NonNull;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;

import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.Button;

import utils.Utils;

import com.example.gymstagram.databinding.FragmentNewWorkoutBinding;
import com.example.gymstagram.model.Workout;
import com.example.gymstagram.retrofit.ApiClient;
import com.google.android.material.textfield.TextInputEditText;

public class NewWorkout extends Fragment {

    private FragmentNewWorkoutBinding binding;
    Utils utils = new Utils();

    TextInputEditText name, reps, sets, weight, duration, date;
    Button add_workout;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);




    }

    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {

        binding = FragmentNewWorkoutBinding.inflate(inflater, container, false);
        View view = inflater.inflate(R.layout.fragment_new_workout, container, false);
        name = view.findViewById(R.id.name);
        reps = view.findViewById(R.id.reps);
        sets = view.findViewById(R.id.sets);
        date = view.findViewById(R.id.workoutdate);
        weight = view.findViewById(R.id.weight);
        duration = view.findViewById(R.id.duration);
        add_workout = view.findViewById(R.id.add_workout);

        add_workout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                saveWorkout(createRequest());
            }
        });

        return binding.getRoot();


    }

    public Workout createRequest(){
        Workout workout = new Workout();
        workout.setName(utils.convertEditTextToString(name));
        workout.setReps(utils.convertEditTextToInt(reps));
        workout.setSets(utils.convertEditTextToInt(sets));
        workout.setDate(utils.convertEditTextToString(date));
        workout.setWeight(utils.convertEditTextToInt(weight));
        workout.setDuration(utils.convertEditTextToInt(duration));

        return workout;
    }

    public void saveWorkout(Workout workout) {
        Call<WorkoutResponse> workoutResponseCall = ApiClient.getWorkoutService().saveWorkout(workout);
        workoutResponseCall.enqueue(new Callback<WorkoutResponse>() {
            @Override
            public void onResponse(Call<WorkoutResponse> call, Response<WorkoutResponse> response) {
                if(response.isSuccessful()){

                }else{

                }
            }

            @Override
            public void onFailure(Call<WorkoutResponse> call, Throwable t) {

            }
        });


    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);


        name = view.findViewById(R.id.name);
        reps = view.findViewById(R.id.reps);
        sets = view.findViewById(R.id.sets);
        date = view.findViewById(R.id.workoutdate);
        weight = view.findViewById(R.id.weight);
        duration = view.findViewById(R.id.duration);
        add_workout = view.findViewById(R.id.add_workout);

        add_workout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                saveWorkout(createRequest());
            }
        });

        binding.buttonCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NavHostFragment.findNavController(NewWorkout.this)
                        .navigate(R.id.action_newWorkout_pop);
            }
        });

        binding.buttonAddPhoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NavHostFragment.findNavController(NewWorkout.this)
                        .navigate(R.id.action_newWorkout_to_photoWorkout);
            }
        });

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}