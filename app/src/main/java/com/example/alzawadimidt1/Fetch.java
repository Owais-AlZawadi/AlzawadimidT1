package com.example.alzawadimidt1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class Fetch extends AppCompatActivity {

    EditText idDel;
    int delnum;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fetch);

        DatabaseHelper db = new DatabaseHelper(this);
        TextView date = (TextView) findViewById(R.id.date);
        idDel = (EditText)findViewById(R.id.ID);
        Bundle bundle = getIntent().getExtras();
        if(bundle != null)
            date.setText(bundle.get("Date").toString());

        Button view = (Button)findViewById(R.id.view);
        view.setOnClickListener(e-> {
            Intent intent = new Intent(Fetch.this, SQLlist.class);
            startActivity(intent);
        });

        Button delete = (Button)findViewById(R.id.delete);
        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String textdel = idDel.getText().toString();
                if(db.Delete(textdel)){
                    delnum++;
                    Toast.makeText(Fetch.this, delnum+"Records Deleted",
                            Toast.LENGTH_LONG).show();
                }
                else{
                    Toast.makeText(Fetch.this, delnum+"Records not exist",
                            Toast.LENGTH_LONG).show();
                }
            }
        });

        Button fetch2 = (Button)findViewById(R.id.fetch2);
        fetch2.setOnClickListener(e-> {
            Intent intent = new Intent(Fetch.this, Insert.class);
            intent.putExtra("Date", MainActivity.getS());
            startActivity(intent);
        });
    }
}