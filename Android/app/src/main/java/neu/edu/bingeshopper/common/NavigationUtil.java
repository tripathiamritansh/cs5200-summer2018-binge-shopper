package neu.edu.bingeshopper.common;

import android.support.v4.app.FragmentTransaction;

public class NavigationUtil {


    public static void navigate(android.support.v4.app.Fragment fragment, FragmentTransaction fragmentTransaction, int container) {

        fragmentTransaction.replace(container, fragment);
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();

    }
    public static void navigateWithOutBackStack(android.support.v4.app.Fragment fragment, FragmentTransaction fragmentTransaction, int container) {

        fragmentTransaction.replace(container, fragment);
        fragmentTransaction.commit();

    }


}
