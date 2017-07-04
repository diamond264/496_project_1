package com.example.q.a496project;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.widget.ImageView;

import uk.co.senab.photoview.PhotoViewAttacher;

public class ImageActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.image_slide);
        ImageView imageView = (ImageView)findViewById(R.id.imageView);
        /*
        PhotoViewAttacher mAttacher;
        mAttacher = new PhotoViewAttacher(imageView);
        mAttacher.setScaleType(ImageView.ScaleType.FIT_CENTER);*/
        setImage(imageView);
    }

    private void setImage(ImageView imageView) {
        Intent receivedIntent = getIntent();

        Bitmap imageID = (Bitmap)receivedIntent.getExtras().get("image ID");
        imageView.setImageBitmap(imageID);
    }
}
