package com.example.retrofitdeneme2.Services;

import com.example.retrofitdeneme2.Services.PlaceHoldeer.Photos;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface PhotosServices {

    @GET("photos")
    Call<ArrayList<Photos>> listPhotos();


}
