package com.example.retrofitdeneme2;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;

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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Retrofit retrofit = new Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl("https://jsonplaceholder.typicode.com/")
                .build();

        PhotosServices photosServices = retrofit.create(PhotosServices.class);
        Call<ArrayList<Photos>> call = photosServices.listPhotos();
        call.enqueue(new Callback<ArrayList<Photos>>() {
            @Override
            public void onResponse(Call<ArrayList<Photos>> call, Response<ArrayList<Photos>> response) {

                if (response.isSuccessful()) {

                    for (Photos photo : response.body()) {
                        for (int i= 0 ; i<10 ; i++)
                        {photos.add(photo);}
                    }
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
