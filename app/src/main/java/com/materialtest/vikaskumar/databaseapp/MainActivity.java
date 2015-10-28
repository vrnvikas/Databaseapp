package com.materialtest.vikaskumar.databaseapp;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;


public class MainActivity extends ActionBarActivity implements View.OnClickListener {


    private EditText userName, password, detailEdittext, updateEdittext, deleteEdittext;
    private Button addUser, viewallUser, detailView, update, delete, customList,internalStorage,download,downloadList;
    VikasDatabaseAdapter adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        userName = (EditText) findViewById(R.id.editText);
        password = (EditText) findViewById(R.id.editText2);
        detailEdittext = (EditText) findViewById(R.id.editText3);
        addUser = (Button) findViewById(R.id.button);
        viewallUser = (Button) findViewById(R.id.button2);
        detailView = (Button) findViewById(R.id.button3);
        update = (Button) findViewById(R.id.button4);
        delete = (Button) findViewById(R.id.button5);
        customList = (Button) findViewById(R.id.button6);
        internalStorage = (Button) findViewById(R.id.button7);
        download = (Button) findViewById(R.id.button11);
        downloadList = (Button) findViewById(R.id.button12);

        addUser.setOnClickListener(this);
        adapter = new VikasDatabaseAdapter(this);
        viewallUser.setOnClickListener(this);
        detailView.setOnClickListener(this);
        update.setOnClickListener(this);
        delete.setOnClickListener(this);
        customList.setOnClickListener(this);
        internalStorage.setOnClickListener(this);
        download.setOnClickListener(this);
        downloadList.setOnClickListener(this);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
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
    public void onClick(View v) {
        if (v.getId() == R.id.button) {
            String user = userName.getText().toString();
            String pass = password.getText().toString();
            long id = adapter.insertData(user, pass);
            userName.setText("");
            password.setText("");

            if (id < 0) {
                Message.mesage(this, "Unsuccessfull");
            } else {
                Message.mesage(this, "Successfull");
            }
        }


        if (v.getId() == R.id.button2) {

            Intent intent = new Intent(this, ListActivity.class);
            startActivity(intent);
            //Message.mesage(this,adapter.viewAllData());

        }

        if (v.getId() == R.id.button3) {
            String name = detailEdittext.getText().toString();
            String data = adapter.viewData(name);
            detailEdittext.setText("");
            Message.mesage(this, data);

        }

        if (v.getId() == R.id.button4) {
            String text = detailEdittext.getText().toString();
            String oldname = text.substring(0, text.indexOf(" "));
            String newname = text.substring(text.indexOf(" ") + 1);
            detailEdittext.setText("");
            adapter.updateName(oldname, newname);

        }

        if (v.getId() == R.id.button5) {
            String userName = detailEdittext.getText().toString();
            detailEdittext.setText("");
            int count = adapter.deleteRow(userName);

            Message.mesage(this, "" + count);

        }

        if (v.getId() == R.id.button6) {
            Intent intent = new Intent(this, DatabaseList.class);
            startActivity(intent);

        }

        if (v.getId() == R.id.button7) {
            Intent intent = new Intent(this, InternalStorage.class);
            startActivity(intent);

        }

        if(v.getId() == R.id.button11){
            Intent intent = new Intent(this,DownloadActivity.class);
            startActivity(intent);
        }

        if(v.getId() == R.id.button12){
            Intent intent = new Intent(this,Threadingtest.class);
            startActivity(intent);

        }

    }

}
