package com.example.q.a496project;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ImageView;

import java.io.File;

import static android.R.attr.data;
import static android.R.attr.layout_gravity;
import static android.app.Activity.RESULT_OK;

/**
 * Created by q on 2017-06-30.
 */

public class Tab2Images extends Fragment {
    public void doTakePhotoAction() {
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        String url = "tmp_" + String.valueOf(System.currentTimeMillis()) + ".jpg";

        intent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(new File(Environment.getExternalStorageDirectory(), url)));
        startActivityForResult(intent, 0);
    }

    public void doTakeAlbumAction() {
        Intent intent = new Intent(Intent.ACTION_PICK);
        intent.setType(MediaStore.Images.Media.CONTENT_TYPE);
        startActivityForResult(intent, 1);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.tab2_image, container, false);
        File mediaStorageDir = new File(Environment.getExternalStoragePublicDirectory(
                Environment.DIRECTORY_PICTURES), "MyCameraApp");

        Button getfrom = (Button) view.findViewById(R.id.fromgall);
        getfrom.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (v.getId() == R.id.fromgall) {
                    DialogInterface.OnClickListener cameraListener = new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            doTakePhotoAction();
                        }
                    };
                    DialogInterface.OnClickListener albumListener = new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            doTakeAlbumAction();
                        }
                    };
                    DialogInterface.OnClickListener cancelListener = new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.dismiss();
                        }
                    };
                    new AlertDialog.Builder(getActivity())
                            .setTitle("Upload Image")
                            .setPositiveButton("From Album", albumListener)
                            .setNeutralButton("Cancel", cancelListener)
                            .setNegativeButton("From Camera", cameraListener)
                            .show();
                }
            }
        });

        GridView gridview = (GridView) view.findViewById(R.id.gridview);

        gridview.setAdapter(new ImageAdapter(getActivity())) ;
        return view;
    }

    private class ImageAdapter extends BaseAdapter {
        private Context mContext;

        public ImageAdapter(Context c) { mContext = c; }

        public int getCount() { return mThumbIds.length; }

        public Object getItem(int position) { return null; }

        public long getItemId(int position) { return 0; }

        public View getView(int position, View convertView, ViewGroup parent) {
            ImageView imageView;
            if (convertView == null) {
                imageView = new ImageView(mContext);
                imageView.setLayoutParams(new GridView.LayoutParams(400, 400));
                imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
                imageView.setPadding(0, 0, 0, 0);
            } else {
                imageView = (ImageView) convertView;
            }

            imageView.setImageResource(mThumbIds[position]);

            imageView.setOnClickListener(new ImageClickListener(mContext, mThumbIds[position]));
            return imageView;
        }

        // references to our images
        private Integer[] mThumbIds = {
                R.drawable.img1, R.drawable.img2,
                R.drawable.img3, R.drawable.img4,
                R.drawable.img5, R.drawable.img6,
                R.drawable.img7, R.drawable.img8,
                R.drawable.img9, R.drawable.img10,
                R.drawable.img11, R.drawable.img12,
                R.drawable.img1, R.drawable.img2,
                R.drawable.img3, R.drawable.img4,
                R.drawable.img5, R.drawable.img6,
                R.drawable.img7, R.drawable.img8,
                R.drawable.img9, R.drawable.img10,
                R.drawable.img11, R.drawable.img12,
                R.drawable.img1, R.drawable.img2,
                R.drawable.img3, R.drawable.img4,
                R.drawable.img5, R.drawable.img6,
                R.drawable.img7, R.drawable.img8,
                R.drawable.img9, R.drawable.img10,
                R.drawable.img11, R.drawable.img12,
                R.drawable.img1, R.drawable.img2,
                R.drawable.img3, R.drawable.img4,
                R.drawable.img5, R.drawable.img6,
                R.drawable.img7, R.drawable.img8,
                R.drawable.img9, R.drawable.img10,
                R.drawable.img11, R.drawable.img12,
                R.drawable.ir
        };
    }
}