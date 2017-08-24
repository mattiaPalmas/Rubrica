package com.exerciseapp.mattiapalmas.rubrica;

import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class RubricaActivity extends AppCompatActivity {

    public static DatabaseHelper dbHelper;
    Button newContact;
    private RecyclerView recycleView;
    private RecyclerView.Adapter adapter;
    private List<ContactModel> listItems;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rubrica);
        newContact = (Button) findViewById(R.id.newContactButton);
        dbHelper = new DatabaseHelper(this);

        recycleView = (RecyclerView) findViewById(R.id.recycleView);
        recycleView.setHasFixedSize(true);
        recycleView.setLayoutManager(new LinearLayoutManager(this));

        listItems = new ArrayList<>();

        insertContactsIntoViews();
        }


    /**
     * open new activity where you can add a contact.
     * @param view
     */
    public void addContact(View view){
                Intent intent = new Intent(this,NewContact.class);
                startActivity(intent);
    }

    @Override
    protected void onResume() {
        insertContactsIntoViews();
        super.onResume();
    }


    /**
     * Read from database and insert data into recycleview.
     */
    public void insertContactsIntoViews(){

        if (dbHelper != null){
            Cursor res = dbHelper.getAllData();

            if (res.getCount() == 0){
                Toast.makeText(this, "No contacts yet", Toast.LENGTH_LONG).show();
            }
            else{
                while (res.moveToNext()){
                    res.getString(1);
                    listItems.add(new ContactModel(res.getString(1),res.getString(2),res.getString(3)));
                }
                adapter = new AdaptorRecycleView(listItems,getApplicationContext());
                recycleView.setAdapter(adapter);
            }
        }
}
}
