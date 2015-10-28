package com.materialtest.vikaskumar.databaseapp;

import android.content.Context;
import android.widget.Toast;

/**
 * Created by Vikas Kumar on 11-07-2015.
 */
public class Message {

    public static void mesage(Context context, String message) {

        Toast.makeText(context, message, Toast.LENGTH_LONG).show();

    }

}
