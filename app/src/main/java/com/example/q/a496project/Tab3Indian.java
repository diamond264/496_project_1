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
    private static final int DIE = 1001;
    private static final int BAT = 1002;
    ArrayList<Card> Deck = new ArrayList<>();
    ArrayList<Integer> card_images = new ArrayList<>();
    User computer;
    User user;

    public void InitialUsers() {
        user = new User();
        user.garnet = 19;
        user.wonLastGame = 0;
        user.current_card = null;
        user.current_bat = 1;

        computer = new User();
        computer.garnet = 19;
        computer.wonLastGame = 0;
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

        if (user.wonLastGame == 1) {
            user.current_card = c1;
            computer.current_card = c2;
        } else {
            user.current_card = c2;
            computer.current_card = c1;
        }
    }

    public void user_win() {
        user.win();
        computer.lose();
        user.garnet += user.current_bat + computer.current_bat - 1;
        user.current_bat = 1;
        computer.garnet--;
        computer.current_bat = 1;
    }

    public void user_lose() {
        computer.win();
        user.lose();
        computer.garnet += user.current_bat + computer.current_bat - 1;
        computer.current_bat = 1;
        user.garnet--;
        user.current_bat = 1;
    }

    public void Die_or_Bat() {
        /////onClickEvent 넣어주기
    }

    public void COM_decision() {
        ///// Random하게 처리
    }

    public void Fight() {
        if (user.wonLastGame == 1) {
            Die_or_Bat();
            if (user.decision == DIE) {
                user_lose();
            }
            else {
                // Wait until computer make decision
                COM_decision();
                if (computer.decision == DIE) {
                    user.win();
                }
                else {
                    if (computer.current_bat == user.current_bat) {
                        if (user.current_card.num >= computer.current_card.num) {
                            user_win();
                        } else {
                            user_lose();
                        }
                    }
                    else {
                        user.win();
                        Fight();
                    }
                }
            }
        }
        else {
            COM_decision();
            if (computer.decision == DIE) {
                user.win();
            }
            else {
                Die_or_Bat();
                if (user.decision == DIE) {
                    user_lose();
                }
                else {
                    if (computer.current_bat == user.current_bat) {
                        if (user.current_card.num >= computer.current_card.num) {
                            user_win();
                        } else {
                            user_lose();
                        }
                    } else {
                        user.lose();
                        Fight();
                    }
                }
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

        void bat_up() {
            if (this.current_bat < this.garnet && this.garnet > 0) {
                this.current_bat++;
                this.garnet--;
            }
        }

        void bat_down() {
            if (this.current_bat > 1) {
                this.current_bat--;
                this.garnet++;
            }
        }
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
