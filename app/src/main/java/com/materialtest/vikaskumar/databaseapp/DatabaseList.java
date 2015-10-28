package com.materialtest.vikaskumar.databaseapp;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import java.util.ArrayList;


public class DatabaseList extends ActionBarActivity implements Communicator {

    VikasDatabaseAdapter helper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.database_list_activity);
        setTitle("All Data");

        helper = new VikasDatabaseAdapter(this);
        ArrayList<String> nameList = helper.viewNameData();



    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_custom_list_activity, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }


    @Override
    public ArrayList<String> listName() {
        ArrayList<String> nameList = helper.viewNameData();
        return nameList;
    }

    @Override
    public ArrayList<Integer> listId() {
        ArrayList<Integer> idList = helper.viewIdData();
        return idList;
    }

    @Override
    public ArrayList<String> listPassword() {
        ArrayList<String> passwordList = helper.viewPasswordData();
        return passwordList;
    }
}
