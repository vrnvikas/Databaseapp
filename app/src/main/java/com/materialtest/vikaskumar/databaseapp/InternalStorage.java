package com.materialtest.vikaskumar.databaseapp;

import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;


public class InternalStorage extends ActionBarActivity implements View.OnClickListener {

    Button back, save, load;
    EditText data;
    Thread thread;
    Handler handler;
    NonUITaskFragment fragment;
    ProgressBar progressBar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_internal_stroage);
        back = (Button) findViewById(R.id.button10);
        data = (EditText) findViewById(R.id.editText4);
        save = (Button) findViewById(R.id.button8);
        load = (Button) findViewById(R.id.button9);
        progressBar = (ProgressBar) findViewById(R.id.progressBar);
        back.setOnClickListener(this);
        save.setOnClickListener(this);
        load.setOnClickListener(this);


        thread = new Thread(new Mythread());
        thread.start();
        //handler = new Handler() {
          //  @Override
            //public void handleMessage(android.os.Message msg) {
          //      progressBar.setProgress(msg.arg1);
            //}
        //};

        if(savedInstanceState == null){
            fragment = new NonUITaskFragment();
            getSupportFragmentManager().beginTransaction().add(fragment,"TaskFragment").commit();
        }
        else {
            fragment = (NonUITaskFragment) getSupportFragmentManager().findFragmentByTag("TaskFragment");
        }




    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_internal_stroage, menu);
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
        if (v.getId() == R.id.button10) {
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);

        }

        if (v.getId() == R.id.button8) {
            FileOutputStream fileOutputStream = null;
            File file = null;
            String text = data.getText().toString();
            data.setText("");
            try {
                fileOutputStream = openFileOutput("vrn.txt", Context.MODE_PRIVATE);
                fileOutputStream.write(text.getBytes());
                file = getFilesDir();

            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                try {
                    fileOutputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            Message.mesage(this, "Data Saved to" + file + "/vrn.txt");
        }

        if (v.getId() == R.id.button9) {

            FileInputStream fileInputStream = null;
            File file = null;

            try {
                fileInputStream = openFileInput("vrn.txt");
                int read = -1;
                StringBuffer buffer = new StringBuffer();
                file = getFilesDir();
                while ((read = fileInputStream.read()) != -1) {
                    buffer.append((char) read);
                }

                data.setText(buffer);
                Message.mesage(this, file + "/vrn.txt");

            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }


        }
    }

    class Mythread implements Runnable {

        @Override
        public void run() {

            android.os.Message message = android.os.Message.obtain();

            for (int i = 0; i < 100; i++) {

                progressBar.setProgress(i);
                //message.arg1 = i;
                //handler.sendMessage(message);
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

            }

            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    progressBar.setVisibility(View.INVISIBLE);
                }
            });

        }
    }

}
