package com.example.gymstagram;

import android.os.Bundle;
import android.view.View;
import androidx.annotation.NonNull;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.fragment.NavHostFragment;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.gymstagram.databinding.ActivityMainBinding;
import com.example.gymstagram.databinding.FragmentNewWorkoutBinding;
import com.google.android.material.textfield.TextInputEditText;

public class NewWorkout extends Fragment {

    private FragmentNewWorkoutBinding binding;

    TextInputEditText name, reps, sets, weight, duration;
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

    public WorkoutRequest createRequest(){
        WorkoutRequest workoutRequest = new WorkoutRequest();
        workoutRequest.setName(name.getText().toString());
        workoutRequest.setReps(Integer.parseInt(reps.getText().toString()));
        workoutRequest.setSets(Integer.parseInt(sets.getText().toString()));
        workoutRequest.setWeight(Integer.parseInt(weight.getText().toString()));
        workoutRequest.setDuration(Integer.parseInt(duration.getText().toString()));

        return workoutRequest;
    }

    public void saveWorkout(WorkoutRequest workoutRequest) {
        Call<WorkoutResponse> workoutResponseCall = ApiClient.getWorkoutService().saveWorkout(workoutRequest);
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