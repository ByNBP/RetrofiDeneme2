package com.example.retrofitdeneme2.Adapters;

import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.example.retrofitdeneme2.R;
import com.example.retrofitdeneme2.Services.PlaceHoldeer.Photos;
import com.squareup.picasso.Picasso;

import java.util.List;

public class PhotosAdapter extends RecyclerView.Adapter<PhotosAdapter.PhotosViewHolder> {


    private Context context;
    private List<Photos> photos;
    String text;

    public PhotosAdapter(Context context, List<Photos> photos) {
        this.context = context;
        this.photos = photos;
    }

    @NonNull
    @Override
    public PhotosViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        LayoutInflater layoutInflater = LayoutInflater.from(context);
        View view = layoutInflater.inflate(R.layout.custom_list_item, parent, false);


        return new PhotosViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PhotosViewHolder holder, int position) {

        Photos photo = photos.get(position);
        holder.title.setText(photo.getTitle());
        Picasso.get().load(photo.getThumbnailUrl().toString()).into(holder.image);

        text = photo.getTitle();


    }

    @Override
    public int getItemCount() {
        return photos.size();
    }

    public class PhotosViewHolder extends RecyclerView.ViewHolder {
        ImageView image;
        TextView title;
        ConstraintLayout item;

        public PhotosViewHolder(@NonNull View itemView) {
            super(itemView);

            item = itemView.findViewById(R.id.customItem);
            image = itemView.findViewById(R.id.photo);
            title = itemView.findViewById(R.id.title);


            item.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {


                    Toast.makeText(context, text, Toast.LENGTH_SHORT).show();


                    AlertDialog.Builder builder = new AlertDialog.Builder(context);

                    builder.setTitle(text);
                    builder.setMessage(text);
// Add the buttons
                    builder.setPositiveButton("Okey", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                            // User clicked OK button
                        }
                    });
                    builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                            // User cancelled the dialog
                        }
                    });
                    AlertDialog dialog = builder.create();

                    dialog.show();

                }
            });


        }
    }


}
