package com.example.q.a496project;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.ExifInterface;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.text.Layout;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ImageView;
import android.os.Parcelable;

import com.bumptech.glide.Glide;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import static android.R.attr.data;
import static android.R.attr.galleryItemBackground;
import static android.R.attr.layout_gravity;
import static android.app.Activity.RESULT_OK;

/**
 * Created by q on 2017-06-30.
 */

public class Tab2Images extends Fragment {

    private ArrayList<String> getPathOfAllImages()
    {
        ArrayList<String> result = new ArrayList<>();
        Uri uri = android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI;
        String[] projection = { MediaStore.MediaColumns.DATA, MediaStore.MediaColumns.DISPLAY_NAME };

        Cursor cursor = getActivity().getContentResolver().query(uri, projection, null, null, MediaStore.MediaColumns.DATE_ADDED + " desc");
        int columnIndex = cursor.getColumnIndexOrThrow(MediaStore.MediaColumns.DATA);
        int columnDisplayname = cursor.getColumnIndexOrThrow(MediaStore.MediaColumns.DISPLAY_NAME);

        int lastIndex;
        while (cursor.moveToNext())
        {
            String absolutePathOfImage = cursor.getString(columnIndex);
            String nameOfFile = cursor.getString(columnDisplayname);
            lastIndex = absolutePathOfImage.lastIndexOf(nameOfFile);
            lastIndex = lastIndex >= 0 ? lastIndex : nameOfFile.length() - 1;

            if (!TextUtils.isEmpty(absolutePathOfImage))
            {
                result.add(absolutePathOfImage);
            }
        }

        for (String string : result)
        {
            Log.i("PhotoSelectActivity.java | getPathOfAllImages", "|" + string + "|");
        }
        return result;
    }

    GridView gridview;
    ArrayList<File> galleryId = new ArrayList<>();
    private static final int PICK_FROM_CAMERA = 1;
    private static final int PICK_FROM_GALLERY = 2;

    public void doTakePhotoAction() {
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        intent.putExtra(MediaStore.EXTRA_OUTPUT,
                MediaStore.Images.Media.EXTERNAL_CONTENT_URI.toString());
        intent.putExtra("return-data", true);
        startActivityForResult(intent, PICK_FROM_CAMERA);
    }
    public void doTakeAlbumAction() {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        intent.putExtra("return-data", true);
        startActivityForResult(Intent.createChooser(intent, "Complete"), PICK_FROM_GALLERY);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == PICK_FROM_GALLERY) {
            Uri uri = data.getData();

            if (uri != null) {
                File photo = new File(uri.getPath());
                galleryId.add(photo);
                gridview.setAdapter(new galleryAdapter(getActivity()));
            }
        }
        if (requestCode == PICK_FROM_CAMERA) {
            Uri uri = data.getData();

            if (uri != null) {
                File photo = new File(uri.getPath());
                galleryId.add(photo);
                gridview.setAdapter(new galleryAdapter(getActivity()));
            }
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        ArrayList<String> paths = getPathOfAllImages();
        for (int i=0; i<paths.size(); i++) {
            File imgfile = new File(paths.get(i));
            if (imgfile.exists()) galleryId.add(imgfile);
        }
        View view = inflater.inflate(R.layout.tab2_image, container, false);

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

        gridview = (GridView) view.findViewById(R.id.gridview);

        gridview.setAdapter(new galleryAdapter(getActivity())) ;
        return view;
    }

    private class galleryAdapter extends BaseAdapter {
        private Context mContext;
        LayoutInflater inflater;

        public galleryAdapter(Context c) {
            mContext = c;
            inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        }

        public int getCount() {
            return galleryId.size();
        }

        public Object getItem(int position) {
            return position;
        }

        public long getItemId(int position) {
            return position;
        }

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

            //imageView.setImageBitmap(galleryId.get(position));
            Glide.with(getActivity())
                    .load(galleryId.get(position))
                    .into(imageView);
            imageView.setOnClickListener(new ImageClickListener(mContext, galleryId.get(position)));
            return imageView;
        }
    }
}