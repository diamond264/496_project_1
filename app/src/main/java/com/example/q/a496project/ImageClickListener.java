package com.example.q.a496project;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.view.View;
import android.view.View.OnClickListener;

public class ImageClickListener implements OnClickListener {

    Context context;

    Bitmap imageID;

    public ImageClickListener(Context context, Bitmap imageID) {
        this.context = context;
        this.imageID = imageID;
    }

    public void onClick(View v) {

        Intent intent = new Intent(context, ImageActivity.class);
        intent.putExtra("image ID", imageID);
        context.startActivity(intent);
    }
}