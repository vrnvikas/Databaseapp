package com.materialtest.vikaskumar.databaseapp;

import android.os.Bundle;
import android.support.v4.app.Fragment;

/**
 * Created by Vikas Kumar on 18-07-2015.
 */
public class NonUITaskFragment extends Fragment {
    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        setRetainInstance(true);
    }
}
