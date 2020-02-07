package com.example.retrofitdeneme2;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.text.method.CharacterPickerDialog;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.retrofitdeneme2.Adapters.PhotosAdapter;
import com.example.retrofitdeneme2.Services.PhotosServices;
import com.example.retrofitdeneme2.Services.PlaceHoldeer.Photos;

import java.util.ArrayList;
import java.util.List;

import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {


    ArrayList<Photos> photos = new ArrayList<>();

    RecyclerView recyclerView;
    TextView counter;
    Button counterButton;
    int deger;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        recyclerView = findViewById(R.id.liste);
        counter = findViewById(R.id.counter);
        counterButton = findViewById(R.id.buttonStop);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        //AsyncTask
        final AsyncTaskDeneme asyncTaskDeneme = new AsyncTaskDeneme(MainActivity.this, counter, counterButton);
        asyncTaskDeneme.execute();
        ///Retrofit
        Retrofit retrofit = new Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl("https://jsonplaceholder.typicode.com/")
                .build();

        //Call to Sercices
        PhotosServices photosServices = retrofit.create(PhotosServices.class);
        Call<ArrayList<Photos>> call = photosServices.listPhotos();
        call.enqueue(new Callback<ArrayList<Photos>>() {
            @Override
            public void onResponse(Call<ArrayList<Photos>> call, Response<ArrayList<Photos>> response) {
                if (response.isSuccessful()) {
                    for (Photos photo : response.body()) {
                        for (int i = 0; i < 100; i++) {
                            photos.add(photo);
                        }
                    }
                    recyclerView.setAdapter(new PhotosAdapter(MainActivity.this, photos));

                } else {
                    Log.d("Deneme", response.errorBody().toString());
                }
            }

            @Override
            public void onFailure(Call<ArrayList<Photos>> call, Throwable t) {


            }
        });

    }
}
