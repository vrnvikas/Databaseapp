package com.materialtest.vikaskumar.databaseapp;

import android.os.AsyncTask;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.Window;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.ProgressBar;

import java.util.ArrayList;


public class Threadingtest extends ActionBarActivity {

    ProgressBar progressBar;
    ListView listView;
    private String[] list = {"entry 1", "entry 2", "entry 3", "entry 4", "entry 5", "entry 6", "entry 7", "entry 8", "entry 9", "entry 10",
            "entry 11", "entry 12", "entry 13", "entry 14", "entry 15", "entry 16", "entry 17", "entry 18", "entry 19", "entry 20",
            "entry 21", "entry 22", "entry 23", "entry 24", "entry 25", "entry 26", "entry 27", "entry 28", "entry 29", "entry 30",
            "entry 31", "entry 32", "entry 33", "entry 34", "entry 35", "entry 36", "entry 37", "entry 38", "entry 39", "entry 40",
            "entry 41", "entry 42", "entry 43", "entry 44", "entry 45", "entry 46", "entry 47", "entry 48", "entry 49", "entry 50",
            "entry 51", "entry 52", "entry 53", "entry 54", "entry 55", "entry 56", "entry 57", "entry 58", "entry 59", "entry 60",
            "entry 61", "entry 62", "entry 63", "entry 64", "entry 65", "entry 66", "entry 67", "entry 68", "entry 69", "entry 70",
            "entry 71", "entry 72", "entry 73", "entry 74", "entry 75", "entry 76", "entry 77", "entry 78", "entry 79", "entry 80",
            "entry 81", "entry 82", "entry 83", "entry 84", "entry 85", "entry 86", "entry 87", "entry 88", "entry 89", "entry 90",
            "entry 91", "entry 92", "entry 93", "entry 94", "entry 95", "entry 96", "entry 97", "entry 98", "entry 99", "entry 100"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //requestWindowFeature(Window.FEATURE_PROGRESS);
        setContentView(R.layout.activity_threadingtest);
        listView = (ListView) findViewById(R.id.listview);
        progressBar = (ProgressBar) findViewById(R.id.progressBar2);
        listView.setAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, new ArrayList<String>()));
        new MyTask().execute();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_threadingtest, menu);
        return true;
    }

    class MyTask extends AsyncTask<Void, String, Void> {

        private ArrayAdapter<String> adapter;
        private int count;

        @Override
        protected void onPreExecute() {
            adapter = (ArrayAdapter<String>) listView.getAdapter();
            //setProgressBarIndeterminate(false);
            //setProgressBarVisibility(true);

        }

        @Override
        protected Void doInBackground(Void... params) {

            for (String item : list) {
                publishProgress(item);
                try {
                    Thread.sleep(200);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

            }
            return null;
        }

        @Override
        protected void onProgressUpdate(String... values) {
            adapter.add(values[0]);
            count++;
            //setProgress((int) (((double) count / list.length) * 10000));
            progressBar.setMax(list.length);
            progressBar.setProgress(count);

        }

        @Override
        protected void onPostExecute(Void aVoid) {


            //setProgressBarVisibility(false);
            // Message.mesage(MainActivity.this,"All Item Added");
        }

    }


}
