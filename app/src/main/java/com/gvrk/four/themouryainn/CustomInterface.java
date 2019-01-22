package com.gvrk.four.themouryainn;

import android.view.View;

/**
 * Created by G V RAVI KUMAR on 6/13/2018.
 */

public interface CustomInterface {

    void initPayment(String email, String phoneno, String amount, String purpose, String username);

    void checkAvailability(View v, String name);

}
