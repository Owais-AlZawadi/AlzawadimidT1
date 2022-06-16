package com.example.alzawadimidt1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class Insert extends AppCompatActivity {
    int count=1;
    EditText idET,fnameET, snameET, nidET, mphoneET, lphoneET;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_insert);

        TextView date = (TextView) findViewById(R.id.date);
        idET = (EditText)findViewById(R.id.ID);
        fnameET = (EditText)findViewById(R.id.FName);
        snameET = (EditText)findViewById(R.id.SName);
        nidET = (EditText)findViewById(R.id.NID);
        mphoneET = (EditText)findViewById(R.id.MPhone);
        lphoneET = (EditText)findViewById(R.id.LPhone);

        Button insert = (Button)findViewById(R.id.insert);

        Bundle bundle = getIntent().getExtras();
        if(bundle != null)
            date.setText(bundle.get("Date").toString());

        Button insert1 = (Button)findViewById(R.id.insert1);
        Button insert3 = (Button)findViewById(R.id.insert3);
        insert1.setOnClickListener(e-> {
            Intent intent = new Intent(Insert.this, MainActivity.class);
            intent.putExtra("Date", MainActivity.getS());
            startActivity(intent);
        });

        insert3.setOnClickListener(e-> {
            Intent intent = new Intent(Insert.this, Fetch.class);
            intent.putExtra("Date", MainActivity.getS());
            startActivity(intent);
        });

        final DatabaseHelper MyDB = new DatabaseHelper(this);

        insert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    String id_val = idET.getText().toString();
                    String fname_val = fnameET.getText().toString();
                    String sname_val = snameET.getText().toString();
                    String nid_val = nidET.getText().toString();
                    String mphone_val = mphoneET.getText().toString();
                    String lphone_val = lphoneET.getText().toString();

                    if(id_val.isEmpty()||fname_val.isEmpty()||sname_val.isEmpty()||nid_val.isEmpty()||mphone_val.isEmpty()||lphone_val.isEmpty())
                        Toast.makeText(Insert.this, "please fill the fields", Toast.LENGTH_LONG).show();
                    else{
                        MyDB.addData(id_val, fname_val, sname_val, nid_val, mphone_val,lphone_val);
                        Log.d("Owais","after adding value");
                        Toast.makeText(Insert.this, "Successful Add", Toast.LENGTH_LONG).show();
                    }

                }
                catch (Exception e){
                    count=1;
                    Toast.makeText(Insert.this,"Unsuccessful Add please insert all necessary data",Toast.LENGTH_LONG).show();
                }
            }

        });
    }
}