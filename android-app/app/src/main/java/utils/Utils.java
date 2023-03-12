package utils;

import androidx.annotation.IdRes;
import androidx.navigation.NavBackStackEntry;
import androidx.navigation.NavController;

public class Utils {
    public boolean isDestinationInNavStack(@IdRes int destination, NavController navController) {
        try {
            navController.getBackStackEntry(destination);
            return true;
        } catch(Throwable e) {
            return false;
        }
    }
}

