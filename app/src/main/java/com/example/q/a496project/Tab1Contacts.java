package com.example.q.a496project;

/**
 * Created by q on 2017-06-30.
 */

import android.database.Cursor;
import android.net.Uri;
import android.provider.ContactsContract;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.ArrayAdapter;
import static android.Manifest.permission.READ_CONTACTS;

import java.util.ArrayList;

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
        ArrayList<String> LIST_CONTACT = new ArrayList<>();

        ArrayList<Contact> ContactArrList;
        ContactArrList = getContactList();

        for(int i=0;i<ContactArrList.size();i++) {
            LIST_CONTACT.add(ContactArrList.get(i).phone_num);
        }

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this.getActivity(), android.R.layout.simple_list_item_1, LIST_CONTACT) ;

        ListView listview = (ListView) view.findViewById(R.id.list_view) ;
        ((ViewGroup)listview.getParent()).removeView(listview);
        listview.setAdapter(adapter) ;

        return listview;
    }
}
