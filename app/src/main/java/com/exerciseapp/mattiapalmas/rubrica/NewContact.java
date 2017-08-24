package com.exerciseapp.mattiapalmas.rubrica;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class NewContact extends AppCompatActivity {

    public static boolean isNewContact;
    EditText nameText,surnameText, phoneText;
    Button addDataButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.new_contact_activity);

        nameText = (EditText) findViewById(R.id.nameText);
        surnameText = (EditText) findViewById(R.id.surnameText);
        phoneText = (EditText) findViewById(R.id.phoneData);
        addDataButton = (Button) findViewById(R.id.addDataButton);
        isNewContact=false;
        addData();

    }

    public void addData(){
        addDataButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d("test",nameText.getText().toString());
                Log.d("test",surnameText.getText().toString());
                Log.d("test",phoneText.getText().toString());
                boolean isInsered = RubricaActivity.dbHelper.insertData(
                        nameText.getText().toString(),
                        surnameText.getText().toString(),
                        phoneText.getText().toString());

                if (isInsered){
                    Toast.makeText(NewContact.this, "data have been inserted", Toast.LENGTH_LONG).show();
                    isNewContact=true;
                }
                else {
                    Toast.makeText(NewContact.this, "Data not insert", Toast.LENGTH_LONG).show();
                }
            }
        });
    }

}
