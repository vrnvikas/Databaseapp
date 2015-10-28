package com.materialtest.vikaskumar.databaseapp;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.ListFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

/**
 * Created by Vikas Kumar on 12-07-2015.
 */
public class idColumn extends ListFragment {

    ListView list;
    Communicator comm;
    ArrayList<Integer> idList;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.id_fragment,container,false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        list = getListView();
        comm = (Communicator) getActivity();
        idList = comm.listId();
        ArrayAdapter<Integer> adapter = new ArrayAdapter<Integer>(getActivity(),android.R.layout.simple_list_item_1,idList);
        list.setAdapter(adapter);
    }
}
