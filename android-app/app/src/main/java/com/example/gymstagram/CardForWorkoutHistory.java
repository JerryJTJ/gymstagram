package com.example.gymstagram;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.Date;

public class CardForWorkoutHistory extends LinearLayout {
    private TextView workoutName;
    private TextView totalVolume;
    private TextView weight;
    private TextView num_reps;
    private TextView num_sets;

    public CardForWorkoutHistory(Context context){
        super(context);
        initControl(context);
    }

    private void initControl(Context context) {
        LayoutInflater inflater = (LayoutInflater)
                context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        inflater.inflate(R.layout.fragment_workout_card, this);
        workoutName = (TextView)findViewById(R.id.workoutName);
        weight = (TextView)findViewById(R.id.weight);
        num_sets = (TextView)findViewById(R.id.sets);
        num_reps = (TextView)findViewById(R.id.reps);

    }
    public void updateCard(String workoutName_, int weight_,int numReps_,int numSets_, String date_){
        workoutName.setText(workoutName_);
        weight.setText("weight: " + weight_ + " lbs");
        num_sets.setText("sets: " + numReps_ );
        num_reps.setText("reps: " + numSets_ );
        Log.i("hhhh", "updateCard: " + date_);

    }
}
