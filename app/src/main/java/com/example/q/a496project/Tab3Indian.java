package com.example.q.a496project;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Collections;

/**
 * Created by q on 2017-06-30.
 */

public class Tab3Indian extends Fragment {
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.tab3_main, container, false);
        Button startButton = (Button) view.findViewById(R.id.Intro_startButton);
        startButton.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), ModeActivity.class);
                startActivity(intent);
            }
        });

        Button endButton = (Button) view.findViewById(R.id.Intro_endButton);
        endButton.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder alert = new AlertDialog.Builder(getActivity());
                alert.setMessage("한 판 더하고 가지 그래??");

                alert.setPositiveButton("싫어!", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        getActivity().finish();
                    }
                });
                alert.setNegativeButton("그럴까??", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.cancel();
                    }
                });

                AlertDialog dial = alert.create();
//                dial.setIcon(Color.rgb(208, 253, 45));
                dial.setTitle("잠깐!!");
                dial.show();
            }
        });

        return view;
    }
}
