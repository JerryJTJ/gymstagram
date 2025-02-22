package com.example.gymstagram;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.gymstagram.databinding.FragmentFirstBinding;
import com.example.gymstagram.databinding.FragmentNewMealBinding;
import com.example.gymstagram.databinding.FragmentPhotoWorkoutBinding;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link photoWorkout#newInstance} factory method to
 * create an instance of this fragment.
 */
public class photoWorkout extends Fragment {
    private FragmentPhotoWorkoutBinding binding;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public photoWorkout() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment photoWorkout.
     */
    // TODO: Rename and change types and number of parameters
    public static photoWorkout newInstance(String param1, String param2) {
        photoWorkout fragment = new photoWorkout();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        binding.cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NavHostFragment.findNavController(photoWorkout.this)
                        .navigate(R.id.action_photoWorkout_pop);
            }
        });

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        binding = FragmentPhotoWorkoutBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }


}