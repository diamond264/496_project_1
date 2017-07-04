package com.example.q.a496project;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

import java.io.File;

import uk.co.senab.photoview.PhotoViewAttacher;

public class ImageActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.image_slide);
        ImageView imageView = (ImageView)findViewById(R.id.imageView);
        PhotoViewAttacher mAttacher;
        setImage(imageView);
        mAttacher = new PhotoViewAttacher(imageView);
        mAttacher.setScaleType(ImageView.ScaleType.CENTER_CROP);
        mAttacher.update();

    }

    private void setImage(ImageView imageView) {
        Intent receivedIntent = getIntent();

        File imageID = (File)receivedIntent.getExtras().get("image ID");
        Glide.with(this).load(imageID).into(imageView);
    }
}
