package com.example.retrofitdeneme2;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.AsyncTask;
import android.widget.Button;
import android.widget.TextView;

import java.util.StringTokenizer;

public class AsyncTaskDeneme extends AsyncTask<Integer, String, Integer> {


    Context context;
    TextView textView;
    Button counterButton;
    ProgressDialog progressDialog;

    AsyncTaskDeneme(Context context, TextView textView) {

        this.context = context;
        this.textView = textView;
    }

    @Override
    protected Integer doInBackground(Integer... integers) {
        int i = 0;

        if (!progressDialog.isShowing()){
            while (!Thread.interrupted()) {
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                i++;
                publishProgress(String.valueOf(i));
            }
        }
        return 0;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();

        progressDialog = new ProgressDialog(context);
        progressDialog.setTitle("Sayaç Başlasınmı.?");
        progressDialog.setCancelable(false);
        progressDialog.setButton(DialogInterface.BUTTON_POSITIVE, "Başlat", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                doInBackground(0);
                dialog.dismiss();
            }
        });
        progressDialog.show();

        // PROGRES DİALOG İŞLEMLERİ BURDA YAPILIYOR. !!!!!


    }

    @Override
    protected void onPostExecute(Integer integer) {

        textView.setText(String.valueOf(integer));
    }

    @Override
    protected void onProgressUpdate(String... values) {

        String value = values[0];
        textView.setText(value);

    }
}
