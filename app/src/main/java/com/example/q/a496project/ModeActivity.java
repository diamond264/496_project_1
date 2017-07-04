package com.example.q.a496project;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class ModeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mode);

        final Intent intent = new Intent(ModeActivity.this, GameActivity.class);

        final TextView msg = (TextView) findViewById(R.id.modeselect_message);
        msg.setText("");

        Button button1 = (Button) findViewById(R.id.mode1Button);
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                msg.setText("일주일 전에 시작했어요");
            }
        });
        Button button2 = (Button) findViewById(R.id.mode2Button);
        button2.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View v) {
                msg.setText("거기, 포커한판 칠래?");
            }
        });
        Button button3 = (Button) findViewById(R.id.mode3Button);
        button3.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View v) {
                msg.setText("날 이길 순 없을걸");
            }
        });
        Button button4 = (Button) findViewById(R.id.mode4Button);
        button4.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View v) {
                msg.setText("해보던가. 고!");
            }
        });

        Button startButton = (Button) findViewById(R.id.mode_startButton);
        startButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                EditText editTextName = (EditText) findViewById(R.id.name);
                final String name = editTextName.getText().toString();
                intent.putExtra("name", name);
                if ("".equals(intent.getStringExtra("name"))) {
                    AlertDialog.Builder alert1 = new AlertDialog.Builder(ModeActivity.this);
                    alert1.setMessage("이름을 입력해야지!");
                    alert1.setPositiveButton("맞네", null);
                    AlertDialog dial1 = alert1.create();
                    dial1.setTitle("저기..");
                    dial1.show();
                } else if ("".equals(msg.getText())) {
                    AlertDialog.Builder alert2 = new AlertDialog.Builder(ModeActivity.this);
                    alert2.setMessage("난이도 안정할래??");
                    alert2.setPositiveButton("그르게..", null);
                    AlertDialog dial2 = alert2.create();
                    dial2.setTitle("이 친구 참..");
                    dial2.show();
                } else {
                    if (msg.getText().equals("일주일 전에 시작했어요")) {
                        intent.putExtra("cpu_name", "동네 꼬마");
                    } else if (msg.getText().equals("거기, 포커한판 칠래?")) {
                        intent.putExtra("cpu_name", "골목 대장");
                    } else if (msg.getText().equals("날 이길 순 없을걸")) {
                        intent.putExtra("cpu_name", "타짜");
                    } else if (msg.getText().equals("해보던가. 고!")) {
                        intent.putExtra("cpu_name", "알파고");
                    }
                    startActivity(intent);
                }
            }
        });
    }
}
