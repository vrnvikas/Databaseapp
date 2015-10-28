package com.materialtest.vikaskumar.databaseapp;

import android.database.DataSetObserver;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.ListFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

/**
 * Created by Vikas Kumar on 13-07-2015.
 */
public class NameCoulmn extends ListFragment{

    ListView nameList;
    String[] data = {"vikas","kumar","chaudhary"};
    Communicator comm;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.name_layout,container,false);


        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        comm = (Communicator) getActivity();
        nameList = getListView();
        ArrayList<String> nameDataList = comm.listName();
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(),android.R.layout.simple_list_item_1,nameDataList);
        nameList.setAdapter(adapter);
    }


}
