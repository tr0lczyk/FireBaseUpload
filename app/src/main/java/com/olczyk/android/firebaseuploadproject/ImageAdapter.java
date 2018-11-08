package com.olczyk.android.firebaseuploadproject;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;

import java.util.List;

public class ImageAdapter extends RecyclerView.Adapter<ImageAdapter.ImageVievHolder> {

    private Context context;
    private List<Upload> uploads;
    private OnItemClickListener onItemClickListener;

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
    public void onBindViewHolder(ImageVievHolder imageVievHolder, int position) {
        Upload upload = uploads.get(position);
        imageVievHolder.bind(upload);
    }

    @Override
    public int getItemCount() {
        return uploads.size();
    }

    public class ImageVievHolder extends RecyclerView.ViewHolder
            implements View.OnClickListener, View.OnCreateContextMenuListener,
            MenuItem.OnMenuItemClickListener {

        public Upload upload;
        public TextView textViewName;
        public ImageView imageView;
        private ProgressBar progressCircle;


        public ImageVievHolder(@NonNull View itemView) {
            super(itemView);
            textViewName = itemView.findViewById(R.id.textViewName);
            imageView = itemView.findViewById(R.id.imageViewUpload);
            progressCircle = itemView.findViewById(R.id.progressCircle);
            itemView.setOnClickListener(this);
            itemView.setOnCreateContextMenuListener(this);
        }

        public void bind(Upload upload){
            this.upload = upload;
            textViewName.setText(upload.getName());
            Picasso.get().load(upload.getImageUrl())
                    .fit().centerCrop()
                    .into(imageView, new Callback() {
                        @Override
                        public void onSuccess() {
                            progressCircle.setVisibility(View.GONE);
                        }

                        @Override
                        public void onError(Exception e) {
                            imageView.setImageResource(R.mipmap.ic_launcher);
                        }
                    });
        }

        @Override
        public void onClick(View v) {
            if(onItemClickListener != null){
                int position = getAdapterPosition();
                if(position != RecyclerView.NO_POSITION){
                    onItemClickListener.onItemClick(position);
                }
            }
        }

        @Override
        public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
            menu.setHeaderTitle("Select action");
            MenuItem doWhatever = menu.add(Menu.NONE,1,1, "Do whatever");
            MenuItem delete = menu.add(Menu.NONE,2,2, "Delete");
            doWhatever.setOnMenuItemClickListener(this);
            delete.setOnMenuItemClickListener(this);
        }

        @Override
        public boolean onMenuItemClick(MenuItem item) {
            if(onItemClickListener != null){
                int position = getAdapterPosition();
                if(position != RecyclerView.NO_POSITION){
                    switch(item.getItemId()){
                        case(1):
                            onItemClickListener.onWhateverClick(position);
                        case(2):
                            onItemClickListener.onDeleteClick(position);
                    }
                }
            }
            return false;
        }
    }

    public interface OnItemClickListener{
        void onItemClick(int position);

        void onWhateverClick(int position);

        void onDeleteClick(int position);
    }

    public void setOnItemClickListener(OnItemClickListener listener){
        onItemClickListener = listener;
    }
}
