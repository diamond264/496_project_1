package com.example.q.a496project;

/**
 * Created by q on 2017-06-30.
 */

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.provider.ContactsContract;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListView;
import android.widget.ArrayAdapter;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

import static android.Manifest.permission.READ_CONTACTS;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Tab1Contacts extends Fragment {

    private static final int REQUEST_READ_CONTACTS = 0;

    private ArrayList<Contact> getContactList() {
        Uri uri = ContactsContract.CommonDataKinds.Phone.CONTENT_URI;
        String[] projection = {
                ContactsContract.CommonDataKinds.Phone.CONTACT_ID,
                ContactsContract.CommonDataKinds.Phone.NUMBER,
                ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME
        };
        String sortOrder = ContactsContract.Contacts.DISPLAY_NAME_PRIMARY + " asc";
        String condition = ContactsContract.Contacts.HAS_PHONE_NUMBER + "=1";
        String[] selectionArgs = null;

        Cursor contactCursor = getActivity().getContentResolver().query(
                uri,
                projection,
                condition,
                selectionArgs,
                sortOrder
        );

        ArrayList<Contact> contact_list = new ArrayList<>();

        while (contactCursor.moveToNext()) {
            Contact contact_ele = new Contact();
            contact_ele.id = contactCursor.getLong(0);
            contact_ele.phone_num = contactCursor.getString(1);
            contact_ele.name = contactCursor.getString(2);
            Log.d("Unity", "아이디 : " + contact_ele.id);
            Log.d("Unity", "이름 : " + contact_ele.name);
            Log.d("Unity", "폰번 : " + contact_ele.phone_num);
            contact_list.add(contact_ele);
        }
        contactCursor.close();

        return contact_list;
    }

    private class Contact {
        long id = 0;
        String phone_num = "Default";
        String name = "Default";
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.tab1_contact, null);

        ArrayList<Contact> ContactArrList;
        ContactArrList = getContactList();

        CustomAdapter adapter = new CustomAdapter(this.getActivity(), R.layout.contact_layout, ContactArrList);

        ListView listview = (ListView) view.findViewById(R.id.list_view) ;
        listview.setAdapter(adapter) ;

        return view;
    }

    private class CustomAdapter extends ArrayAdapter<Contact> {
        private ArrayList<Contact> items;

        public CustomAdapter(Context context, int textViewResourceId, ArrayList<Contact> objects) {
            super(context, textViewResourceId, objects);
            this.items = objects;
        }

        public View getView(int position, View convertView, ViewGroup parent) {
            View v = convertView;
            if (v == null) {
                LayoutInflater vi = (LayoutInflater) getActivity().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                v = vi.inflate(R.layout.contact_layout, null);
            }

            TextView textView1 = (TextView)v.findViewById(R.id.textView1);
            textView1.setText(items.get(position).name);
            TextView textView2 = (TextView)v.findViewById(R.id.textView2);
            textView2.setText(items.get(position).phone_num);

            final String phone_num = items.get(position).phone_num;
            Button button1 = (Button)v.findViewById(R.id.button1);
            button1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String tel = "tel:" + phone_num;
                    startActivity(new Intent("android.intent.action.CALL", Uri.parse(tel)));
                }
            });
            Button button2 = (Button)v.findViewById(R.id.button2);
            button2.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String tel = "tel:" + phone_num;
                    startActivity(new Intent("android.intent.action.DIAL", Uri.parse(tel)));
                }
            });

            return v;
        }
    }
}
