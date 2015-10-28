package com.materialtest.vikaskumar.databaseapp;

import android.net.Uri;
import android.os.Environment;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.ProgressBar;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;


public class DownloadActivity extends ActionBarActivity implements AdapterView.OnItemClickListener {

    Button download;
    EditText editText, nameEditText;
    ListView listView;
    String[] listOfImages;
    ProgressBar progressBar;
    LinearLayout loadingSection = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.download_activity);
        download = (Button) findViewById(R.id.button13);
        editText = (EditText) findViewById(R.id.editText5);
        nameEditText = (EditText) findViewById(R.id.filename);
        progressBar = (ProgressBar) findViewById(R.id.downloadProgress);
        loadingSection = (LinearLayout) findViewById(R.id.loadsection);
        listOfImages = getResources().getStringArray(R.array.imageUrls);
        listView = (ListView) findViewById(R.id.urlList);
        listView.setOnItemClickListener(this);
        // download.setOnClickListener((View.OnClickListener) this);


    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main_activity2, menu);
        return true;
    }

    public void downloadImage(View view) {

        String url = editText.getText().toString();
        Thread myThread = new Thread(new DownloadImageThread(url));
        myThread.start();
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        editText.setText(listOfImages[position]);
        String path = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES).getAbsolutePath();
        Uri uri = Uri.parse(listOfImages[position]);
        String name = uri.getLastPathSegment();
        nameEditText.setText(name);


    }


    public boolean imageDownloadHandler(String url) {
        boolean successfull = false;
        URL downloadUrl = null;
        int contentLength = -1;
        HttpURLConnection connection = null;
        InputStream inputStream = null;
        FileOutputStream fileOutputStream = null;
        File file = null;
        try {
            downloadUrl = new URL(url);
            connection = (HttpURLConnection) downloadUrl.openConnection();
            inputStream = connection.getInputStream();
            contentLength = connection.getContentLength();
            String path = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES).getAbsolutePath();
            //Uri uri = Uri.parse(url);
            //String last = uri.getLastPathSegment();
            String last = nameEditText.getText().toString();
            file = new File(path + "/" + last);
            fileOutputStream = new FileOutputStream(file);
            Message.mesage(this, "File Saved To" + file);
            byte[] buffer = new byte[1024];
            int read = -1;
            while ((read = inputStream.read(buffer)) != -1) {
                fileOutputStream.write(buffer, 0, read);

            }

            successfull = true;
        } catch (MalformedURLException e) {

        } catch (IOException e) {
            //Message.mesage(this, "error" );
        } finally {
            if (connection != null) {
                connection.disconnect();
            }

            if (inputStream != null) {
                try {
                    inputStream.close();
                } catch (IOException e) {
                    //Message.mesage(this, "error" );
                }
            }

        }


        return successfull;
    }

    private class DownloadImageThread implements Runnable {
        private String url;

        public DownloadImageThread(String url) {
            this.url = url;
        }

        @Override
        public void run() {

            imageDownloadHandler(url);
        }
    }
}
