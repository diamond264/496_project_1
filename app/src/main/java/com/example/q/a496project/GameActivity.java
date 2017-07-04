package com.example.q.a496project;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

public class GameActivity extends AppCompatActivity {
    private static final int DIE = 1001;
    private static final int BAT = 1002;
    ArrayList<Card> Deck = new ArrayList<>();
    ArrayList<Integer> card_images = new ArrayList<>();
    int turn = 0;
    User computer;
    User user;

    public void InitialUsers() {
        user = new User();
        user.garnet = 19;
        user.wonLastGame = 1;
        user.current_card = null;
        user.current_bat = 1;

        computer = new User();
        computer.garnet = 19;
        computer.wonLastGame = 0;
        computer.current_card = null;
        computer.current_bat = 1;
    }

    public void InitializeDeck() {
        card_images.add(R.drawable.card_1);
        card_images.add(R.drawable.card_2);
        card_images.add(R.drawable.card_3);
        card_images.add(R.drawable.card_4);
        card_images.add(R.drawable.card_5);
        card_images.add(R.drawable.card_6);
        card_images.add(R.drawable.card_7);
        card_images.add(R.drawable.card_8);
        card_images.add(R.drawable.card_9);
        card_images.add(R.drawable.card_10);

        for (int i=0;i<10;i++) {
            Card c = new Card();
            c.num = i;
            c.img = card_images.get(i);
            Deck.add(c);
            Deck.add(c);
        }
        ShuffleDeck();
    }

    public void ShuffleDeck() {
        if (!Deck.isEmpty())
            Collections.shuffle(Deck);
    }

    public void DistributeCard() {
        Card c1 = Deck.remove(0);
        Card c2 = Deck.remove(0);
        TextView rest_cards = (TextView) findViewById(R.id.rest_cards);
        rest_cards.setText("남은카드:"+Integer.toString(Deck.size()));

        user.prev_card = user.current_card;
        computer.prev_card = computer.current_card;

        if (user.wonLastGame == 1) {
            user.current_card = c1;
            computer.current_card = c2;
        } else {
            user.current_card = c2;
            computer.current_card = c1;
        }
        if (Deck.isEmpty()) {
            InitializeDeck();
        }
        ImageView cards_set = (ImageView) findViewById(R.id.cards_set);
        ImageView cards_set2 = (ImageView) findViewById(R.id.cards_set2);
        if (user.wonLastGame == 1) {
            Animation user_anim = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.anim_trans_user_slow);
            cards_set.startAnimation(user_anim);
            Animation cpu_anim = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.anim_trans_cpu_fast);
            cards_set2.startAnimation(cpu_anim);
        } else {
            Animation cpu_anim = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.anim_trans_cpu_slow);
            cards_set.startAnimation(cpu_anim);
            Animation user_anim = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.anim_trans_user_fast);
            cards_set2.startAnimation(user_anim);
        }
        ImageView cpu_card = (ImageView) findViewById(R.id.cpu_card);
        cpu_card.setImageResource(computer.current_card.img);
    }

    public void finish() {
        if (user.wonLastGame == 1) {
            Toast.makeText(GameActivity.this, "win", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(GameActivity.this, "lose", Toast.LENGTH_SHORT).show();
        }
        GameActivity.this.finish();
    }


    public void user_win() {
        turn = 2;
        user.win();
        computer.lose();
        user.garnet += user.current_bat + computer.current_bat - 1;
        user.current_bat = 1;
        computer.garnet--;
        computer.current_bat = 1;
        ImageView user_card = (ImageView) findViewById(R.id.user_card);
        user_card.setImageResource(user.current_card.img);
    }

    public void user_lose() {
        turn = 2;
        computer.win();
        user.lose();
        computer.garnet += user.current_bat + computer.current_bat - 1;
        computer.current_bat = 1;
        user.garnet--;
        user.current_bat = 1;
        ImageView user_card = (ImageView) findViewById(R.id.user_card);
        user_card.setImageResource(user.current_card.img);
    }

    public void COM_decision() {
        Random random = new Random();
        int ran_value = random.nextInt(2);
        computer.decision = (ran_value == 1)?DIE:BAT;
        Toast.makeText(GameActivity.this, "Computer's decision" + String.valueOf(computer.decision), Toast.LENGTH_SHORT);
        if (computer.decision == BAT) {
            if (computer.current_bat + computer.garnet < user.current_bat) {
                user.win();
            }
            else {
                int sub = computer.current_bat + computer.garnet - user.current_bat;
                ran_value = random.nextInt(((sub + 1)+computer.current_bat)/2);
                computer.current_bat = user.current_bat + ran_value;
                computer.garnet = 40 - computer.current_bat - user.current_bat - user.garnet;
            }
        }
    }

    public class User {
        int garnet;
        int wonLastGame;
        Card current_card;
        int current_bat;
        Card prev_card;
        int decision;

        void win() {
            this.wonLastGame = 1;
        }

        void lose() {
            this.wonLastGame = 0;
        }
    }

    public class Card {
        int img;
        int num;
    }

    public void GameStart() {
        turn = 1;
        initiate();
    }

    public void initiate() {
        InitialUsers();
        InitializeDeck();
        ShuffleDeck();
        DistributeCard();

        TextView casino_cpu_count = (TextView) findViewById(R.id.cpu_batting_count);
        casino_cpu_count.setText("×"+Integer.toString(computer.current_bat));
        TextView casino_user_count = (TextView) findViewById(R.id.user_batting_count);
        casino_user_count.setText("×"+Integer.toString(user.current_bat));
        TextView cpu_count = (TextView) findViewById(R.id.cpu_count);
        cpu_count.setText("×"+Integer.toString(computer.garnet));
        TextView user_count = (TextView) findViewById(R.id.user_count);
        user_count.setText("×"+Integer.toString(user.garnet));
    }

    public void refresh() {
        TextView casino_cpu_count = (TextView) findViewById(R.id.cpu_batting_count);
        casino_cpu_count.setText("×"+Integer.toString(computer.current_bat));
        TextView casino_user_count = (TextView) findViewById(R.id.user_batting_count);
        casino_user_count.setText("×"+Integer.toString(user.current_bat));
        TextView cpu_count = (TextView) findViewById(R.id.cpu_count);
        cpu_count.setText("×"+Integer.toString(computer.garnet));
        TextView user_count = (TextView) findViewById(R.id.user_count);
        user_count.setText("×"+Integer.toString(user.garnet));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        Intent intent = getIntent();

        final Button startButton = (Button) findViewById(R.id.gamestart);
        startButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ImageView casino_cpu_ganet = (ImageView) findViewById(R.id.cpu_batting_ganet);
                casino_cpu_ganet.setVisibility(View.VISIBLE);
                ImageView casino_user_ganet = (ImageView) findViewById(R.id.user_batting_ganet);
                casino_user_ganet.setVisibility(View.VISIBLE);
                TextView casino_cpu_count = (TextView) findViewById(R.id.cpu_batting_count);
                casino_cpu_count.setVisibility(View.VISIBLE);
                TextView casino_user_count = (TextView) findViewById(R.id.user_batting_count);
                casino_user_count.setVisibility(View.VISIBLE);
                ImageView cpu_card = (ImageView) findViewById(R.id.cpu_card);
                cpu_card.setVisibility(View.VISIBLE);
                ImageView user_card = (ImageView) findViewById(R.id.user_card);
                user_card.setVisibility(View.VISIBLE);
                Button startButton = (Button) findViewById(R.id.gamestart);
                startButton.setVisibility(View.INVISIBLE);

                GameStart();
            }
        });

        Button nextButton = (Button) findViewById(R.id.next);
        nextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (turn == 2) {
                    ImageView user_card = (ImageView) findViewById(R.id.user_card);
                    user_card.setImageResource(R.drawable.card_back);
                    DistributeCard();
                    if ((computer.current_bat + computer.garnet) < 0) {
                        finish();
                    }
                    turn = 1;
                }
            }
        });

        Button go = (Button) findViewById(R.id.go);
        go.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (turn == 1) {
                    if (user.current_bat < computer.current_bat) {
                        AlertDialog.Builder alert = new AlertDialog.Builder(GameActivity.this);
                        alert.setMessage("배팅은 상대방 칩보다 같거나 많아야 합니다.");
                        alert.setPositiveButton("닫기", null);
                        AlertDialog dial1 = alert.create();
                        dial1.setTitle("경고");
                        dial1.show();
                    } else {
                        COM_decision();
                        if (computer.decision == DIE) {
                            user_win();
                        } else {
                            if (computer.current_bat == user.current_bat) {
                                if (user.current_card.num >= computer.current_card.num) {
                                    user_win();
                                } else {
                                    user_lose();
                                }
                            }
                        }
                        refresh();
                    }
                }
            }
        });
        Button die = (Button) findViewById(R.id.die);
        die.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (turn == 1) {
                    user_lose();
                    refresh();
                }
            }
        });
        ImageButton up = (ImageButton) findViewById(R.id.up_batting);
        up.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (turn == 1) {
                    if (user.garnet > 0) {
                        user.garnet--;
                        user.current_bat++;
                        refresh();
                    }
                }
            }
        });
        ImageButton down = (ImageButton) findViewById(R.id.down_batting);
        down.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (turn == 1) {
                    if (user.current_bat > computer.current_bat) {
                        user.garnet++;
                        user.current_bat--;
                        refresh();
                    }
                }
            }
        });

        // Name setting
        TextView cpu_name = (TextView) findViewById(R.id.cpu_name);
        String cpuName = intent.getStringExtra("cpu_name");
        cpu_name.setText(cpuName);
        TextView user_name = (TextView) findViewById(R.id.user_name);
        String userName = intent.getStringExtra("name");
        user_name.setText(userName);

        // Card, ganet, count setting
        ImageView casino_cpu_ganet = (ImageView) findViewById(R.id.cpu_batting_ganet);
        casino_cpu_ganet.setVisibility(View.INVISIBLE);
        ImageView casino_user_ganet = (ImageView) findViewById(R.id.user_batting_ganet);
        casino_user_ganet.setVisibility(View.INVISIBLE);
        TextView casino_cpu_count = (TextView) findViewById(R.id.cpu_batting_count);
        casino_cpu_count.setVisibility(View.INVISIBLE);
        TextView casino_user_count = (TextView) findViewById(R.id.user_batting_count);
        casino_user_count.setVisibility(View.INVISIBLE);
        ImageView cpu_card = (ImageView) findViewById(R.id.cpu_card);
        cpu_card.setVisibility(View.INVISIBLE);
        ImageView user_card = (ImageView) findViewById(R.id.user_card);
        user_card.setVisibility(View.INVISIBLE);
    }
}
