package com.example.q.a496project;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Collections;

/**
 * Created by q on 2017-06-30.
 */

public class Tab3Indian extends Fragment {
    ArrayList<Card> Deck = new ArrayList<>();
    ArrayList<Integer> card_images = new ArrayList<>();
    User computer;
    User user;

    public void InitialUsers() {
        user = new User();
        user.garnet = 15;
        user.won = 0;
        user.current_card = null;

        computer = new User();
        computer.garnet = 15;
        computer.won = 0;
        computer.current_card = null;
    }

    public void InitializeDeck() {
        for (int i=1;i<=10;i++) {
            Card c = new Card();
            c.num = i;
            c.img = card_images.get(i);
            Deck.add(c);
            Deck.add(c);
        }
    }

    public void ShuffleDeck() {
        if (!Deck.isEmpty())
            Collections.shuffle(Deck);
    }

    public void DistributeCard() {
        Card c1 = Deck.remove(0);
        Card c2 = Deck.remove(0);

        user.prev_card = user.current_card;
        computer.prev_card = computer.current_card;

        if (user.won == 1) {
            user.current_card = c1;
            computer.current_card = c2;
        } else {
            user.current_card = c2;
            computer.current_card = c1;
        }
    }

    public class User {
        int garnet;
        int won;
        Card current_card;
        Card prev_card
    }

    public class Card {
        int img;
        int num;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.tab3_indian, container, false);
        return view;
    }
}
