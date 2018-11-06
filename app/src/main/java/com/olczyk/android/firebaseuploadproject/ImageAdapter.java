package com.olczyk.android.firebaseuploadproject;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

public class ImageAdapter extends RecyclerView.Adapter<ImageAdapter.ImageVievHolder> {

    private Context context;
    private List<Upload> uploads;

    public ImageAdapter(Context context, List<Upload> uploads){
        this.context = context;
        this.uploads = uploads;
    }

    @Override
    public ImageVievHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(context)
                .inflate(R.layout.image_item,viewGroup,false);
        return new ImageVievHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ImageVievHolder imageVievHolder, int position) {
        Upload upload = uploads.get(position);
        imageVievHolder.bind(upload);
    }

    @Override
    public int getItemCount() {
        return uploads.size();
    }

    public class ImageVievHolder extends RecyclerView.ViewHolder{

        public Upload upload;
        public TextView textViewName;
        public ImageView imageView;

        public ImageVievHolder(@NonNull View itemView) {
            super(itemView);
            textViewName = itemView.findViewById(R.id.textViewName);
            imageView = itemView.findViewById(R.id.imageViewUpload);
        }

        public void bind(Upload upload){
            this.upload = upload;
            textViewName.setText(upload.getName());
            Picasso.get().load(upload.getImageUrl()).fit().centerCrop().into(imageView);
        }
    }
}
