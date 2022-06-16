package com.example.alzawadimidt1;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ListActivity;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class SQLlist extends ListActivity {

    DatabaseHelper db = new DatabaseHelper(this);
    ArrayList<String> arrayList = new ArrayList<>();
    Cursor cur;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        cur = db.getListContents();
        while (cur.moveToNext()) {
            arrayList.add("\nID: " + cur.getString(0) + "\nFirst Name: " + cur.getString(1) + "\nLast Name: "
                    + cur.getString(2) + "\nNatoinal ID: " + cur.getString(3) + "\nMobile Phone: " + cur.getString(4) + "\nLandline Phone: " + cur.getString(5));
            setListAdapter(new ArrayAdapter<String>(SQLlist.this, R.layout.activity_sqllist, R.id.itemlist2, arrayList));
        }
    }

    protected void onListItemClick (ListView l, View v, int position, long id){
        super.onListItemClick(l,v,position,id);
        int row = 0;
        cur = db.getListContents();

        while(row<=position){
            cur.moveToNext();
            row++;
        }
    }
}