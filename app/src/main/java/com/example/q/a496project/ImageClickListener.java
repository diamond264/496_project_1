package com.example.q.a496project;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.view.View;
import android.view.View.OnClickListener;

import java.io.File;

public class ImageClickListener implements OnClickListener {

    Context context;

    File imageID;

    public ImageClickListener(Context context, File imageID) {
        this.context = context;
        this.imageID = imageID;
    }

    public void onClick(View v) {

        Intent intent = new Intent(context, ImageActivity.class);
        intent.putExtra("image ID", imageID);
        context.startActivity(intent);
    }
}