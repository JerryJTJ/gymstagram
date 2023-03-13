package com.example.gymstagram;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.gymstagram.databinding.FragmentNewMealBinding;
import com.example.gymstagram.model.Meal;
import com.example.gymstagram.retrofit.MealAPI;
import com.example.gymstagram.retrofit.RetrofitService;

import java.util.List;
import java.util.UUID;

import utils.Utils;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class NewMeal extends Fragment {

    private FragmentNewMealBinding binding;

    Utils utils = new Utils();

    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {

        binding = FragmentNewMealBinding.inflate(inflater, container, false);
        return binding.getRoot();

    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        binding.buttonAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String name = utils.convertEditTextToString(binding.inputName);

                if(name.equals("")){
                    Toast.makeText(getActivity(), "Name cannot be empty!", Toast.LENGTH_SHORT).show();
                    return;
                }

                String id = UUID.randomUUID().toString();
                int calories = utils.convertEditTextToInt(binding.inputCalories);
                int carbs = utils.convertEditTextToInt(binding.inputCarbs);
                int protein = utils.convertEditTextToInt(binding.inputProtein);
                int fat = utils.convertEditTextToInt(binding.inputFat);

                RetrofitService retrofitService = new RetrofitService();
                MealAPI mealAPI = retrofitService.getRetrofit().create(MealAPI.class);
                Meal newMeal = new Meal(id, name, calories, carbs, protein, fat);

                mealAPI.createMeal(newMeal).enqueue(new Callback<Meal>() {
                    @Override
                    public void onResponse(Call<Meal> call, Response<Meal> response) {
                        //Success, go back to add;
                        NavHostFragment.findNavController(NewMeal.this).popBackStack(R.id.addFragment, false);
                    }

                    @Override
                    public void onFailure(Call<Meal> call, Throwable t) {
                        Toast.makeText(getActivity(), t.toString(), Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });

        binding.buttonCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NavHostFragment.findNavController(NewMeal.this)
                        .navigate(R.id.action_newMeal_pop);
            }
        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

}