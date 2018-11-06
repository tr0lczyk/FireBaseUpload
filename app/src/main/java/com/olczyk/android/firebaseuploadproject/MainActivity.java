package com.olczyk.android.firebaseuploadproject;

import android.content.Intent;
import android.net.Uri;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.ViewById;
import org.androidannotations.annotations.ViewsById;

@EActivity(R.layout.activity_main)
public class MainActivity extends AppCompatActivity {

    public static final int PICK_IMAGE_REQUEST = 1;

    @ViewById
    Button buttonChooseFile, buttonUpload;
    @ViewById
    EditText editTextFileName;
    @ViewById
    TextView textViewShowUploads;
    @ViewById
    ProgressBar progressBar;
    @ViewById
    ImageView imageView;

    private Uri imageViewUri;


    @AfterViews
    public void aVoid(){
        buttonChooseFile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openFileChooser();
            }
        });

        buttonUpload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        textViewShowUploads.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                
            }
        });
    }

    private void openFileChooser() {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(intent,PICK_IMAGE_REQUEST);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode == PICK_IMAGE_REQUEST && resultCode == RESULT_OK &&
                data != null && data.getData() != null){
            imageViewUri = data.getData();

            Picasso.get().load(imageViewUri).fit().centerCrop().into(imageView);
        }
    }
}
