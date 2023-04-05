package com.example.gymstagram;

import android.content.Context;
import android.view.LayoutInflater;
import android.widget.LinearLayout;
import android.widget.TextView;

public class CardForWorkoutHistory extends LinearLayout {
    private TextView workoutName;
    private TextView totalVolume;
    public CardForWorkoutHistory(Context context){
        super(context);
        initControl(context);
    }

    private void initControl(Context context) {
        LayoutInflater inflater = (LayoutInflater)
                context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        inflater.inflate(R.layout.fragment_workout_card, this);
        workoutName = (TextView)findViewById(R.id.workoutName);
        totalVolume = (TextView) findViewById(R.id.totalVolume);
    }
    public void updateCard(String workoutName_, int totalVolume_){
        workoutName.setText(workoutName_);
        String tot_vol =  Integer.toString(totalVolume_);
        totalVolume.setText(tot_vol + " lbs");
    }
}
