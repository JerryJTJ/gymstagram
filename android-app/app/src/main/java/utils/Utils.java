package utils;

import android.util.Log;
import android.view.MenuItem;
import android.widget.EditText;

import androidx.annotation.IdRes;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavBackStackEntry;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;

import com.example.gymstagram.NewMeal;
import com.example.gymstagram.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class Utils {
    public boolean isDestinationInNavStack(@IdRes int destination, NavController navController) {
        try {
            navController.getBackStackEntry(destination);
            return true;
        } catch(Throwable e) {
            return false;
        }
    }

    public String convertEditTextToString(EditText editText){
        return editText.getText().toString();
    }


    public int convertEditTextToInt(EditText editText){
        if(editText.getText().toString().equals("")){
            return 0;
        }

        return Integer.parseInt(editText.getText().toString());
    }
}

