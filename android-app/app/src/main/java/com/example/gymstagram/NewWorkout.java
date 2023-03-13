package com.example.gymstagram;

import android.os.Bundle;
import android.view.View;
import androidx.annotation.NonNull;
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
        TextView name = view.findViewById(R.id.name);
        return binding.getRoot();


    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

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