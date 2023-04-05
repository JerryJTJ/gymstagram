package com.example.gymstagram;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.widget.LinearLayout;
import android.widget.TextView;

public class CardForMealHistory extends LinearLayout {
    private TextView Calories;
    private TextView Protein;
    private TextView Fat;
    private TextView Carbs;

    public CardForMealHistory(Context context){
        super(context);
        initControl(context);
    }

    private void initControl(Context context) {
        LayoutInflater inflater = (LayoutInflater)
                context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        inflater.inflate(R.layout.fragment_meal_card, this);
        Calories = (TextView)findViewById(R.id.calories);
        Protein = (TextView)findViewById(R.id.protein);
        Carbs = (TextView)findViewById(R.id.carbs);
        Fat = (TextView)findViewById(R.id.fat);

    }
    public void updateCard(int cals_,int protein_,int carbs_, int fat_){
        Calories.setText( Integer.toString(cals_) + " kCal");
        Protein.setText( Integer.toString(protein_) + " g");
        Carbs.setText( Integer.toString(carbs_) + " g");
        Fat.setText( Integer.toString(fat_) + " g");

    }
}
