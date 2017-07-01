package com.example.q.a496project;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;

import static android.R.attr.data;

/**
 * Created by q on 2017-06-30.
 */

public class Tab2Images extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.tab2_image, container, false);

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

        // create a new ImageView for each item referenced by the Adapter
        public View getView(int position, View convertView, ViewGroup parent) {
            ImageView imageView;
            if (convertView == null) {
                // if it's not recycled, initialize some attributes
                imageView = new ImageView(mContext);
                imageView.setLayoutParams(new GridView.LayoutParams(400, 400));
                imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
                imageView.setPadding(0, 0, 0, 0);
            } else {
                imageView = (ImageView) convertView;
            }

            imageView.setImageResource(mThumbIds[position]);
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
                R.drawable.ir
        };
    }
}