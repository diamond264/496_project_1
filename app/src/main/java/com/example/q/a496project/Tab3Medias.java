package com.example.q.a496project;

import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.MediaController;
import android.widget.VideoView;
import android.widget.BaseAdapter;

/**
 * Created by q on 2017-06-30.
 */

public class Tab3Medias extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.tab3_media, container, false);

        VideoView videoView = (VideoView) view.findViewById(R.id.videoView);
        Uri uri = Uri.parse("rtsp://v6.cache4.c.youtu.be/tWRT6LrZGWQ");
        videoView.setVideoURI(uri);
        final MediaController mediaController = new MediaController(getActivity());
        videoView.setMediaController(mediaController);
        videoView.start();
        ((ViewGroup)videoView.getParent()).removeView(videoView);
        return videoView;
    }
}
