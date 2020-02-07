package com.example.retrofitdeneme2;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.AsyncTask;
import android.text.Editable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.StringTokenizer;

public class AsyncTaskDeneme extends AsyncTask<Integer, String, Integer> {

    Context context;
    TextView textView;
    Button counterButton;
    Button basla;
    EditText sayacSayısı;
    String sayacCount;
    ProgressDialog progressDialog;
    Dialog dialog;
    Thread thread = Thread.currentThread();
    boolean deger = false;
    int x;

    AsyncTaskDeneme(Context context, TextView textView, Button counterButton) {
        this.counterButton = counterButton;
        this.context = context;
        this.textView = textView;
    }

    @Override
    protected Integer doInBackground(Integer... integers) {


        int i = 0;
        while (!thread.isInterrupted()) {
            if (dialog.isShowing()) {
                publishProgress(String.valueOf(0));
            } else {
                try {
                    Thread.sleep(1000);
                    i = i + x;
                    if (deger == true)
                        thread.interrupt();
                    publishProgress(String.valueOf(i));
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

            }

        }
        return 0;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        // PROGRES DİALOG İŞLEMLERİ BURDA YAPILIYOR. !!!!!


        dialog = new Dialog(context);
        dialog.setContentView(R.layout.custom_dialog_box);
        dialog.setCancelable(false);
        dialog.show();
        basla = dialog.findViewById(R.id.basla);
        basla.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sayacSayısı = dialog.findViewById(R.id.sayacArtis);
                sayacCount = sayacSayısı.getText().toString();
                System.out.println("yek : " + sayacSayısı.getText());
                x = Integer.parseInt(sayacCount);
                dialog.dismiss();
            }
        });

//        progressDialog = new ProgressDialog(context);
//        progressDialog.setContentView(R.layout.custom_dialog_box);
//        progressDialog.setCancelable(false);
//        progressDialog.setButton(R.id.basla, "Başlat", new DialogInterface.OnClickListener() {
//            @Override
//            public void onClick(DialogInterface dialog, int which) {
//                dialog.dismiss();
//            }
//        });
//        progressDialog.show();
    }

    @Override
    protected void onPostExecute(Integer integer) {
        textView.setText(String.valueOf(integer));
    }

    @Override
    protected void onProgressUpdate(String... values) {
        String value = values[0];
        textView.setText(value);

        counterButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                deger = true;
            }
        });


    }
}
