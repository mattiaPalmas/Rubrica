package com.exerciseapp.mattiapalmas.rubrica;

import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class RubricaActivity extends AppCompatActivity {

    Button newContact;
    private DatabaseHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rubrica);
        newContact = (Button) findViewById(R.id.newContactButton);

        dbHelper = new DatabaseHelper(this);
        Cursor res = dbHelper.getAllData();

        StringBuffer buffer = new StringBuffer();

        while(res.moveToNext()){
            buffer.append("id :" + res.getString(0)+ "\n");
            buffer.append("name :" + res.getString(1)+ "\n");
            buffer.append("last name :" + res.getString(2)+ "\n");
            buffer.append("phone :" + res.getString(3)+ "\n\n");
        }
    }


    public void addContact(View view){

                Intent intent = new Intent(this,NewContact.class);
                startActivity(intent);

    }
}
