package com.example.retrofitdeneme2.Adapters;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;

import com.example.retrofitdeneme2.Services.PlaceHoldeer.Photos;

import java.util.ArrayList;

public class PhotosAdapter extends RecyclerView.Adapter<PhotosAdapter> {

    private ArrayList<Photos> photosArrayList;

    public PhotosAdapter(ArrayList<Photos> photos){


        photos = photosArrayList;


    }


    @NonNull
    @Override
    public PhotosAdapter onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull PhotosAdapter holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }
}
