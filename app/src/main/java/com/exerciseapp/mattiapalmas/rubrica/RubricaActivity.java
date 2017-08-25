package com.exerciseapp.mattiapalmas.rubrica;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
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
    private Button deleteContact;
    private boolean isDeleteClicked;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rubrica);

        newContact = (Button) findViewById(R.id.newContactButton);
        dbHelper = new DatabaseHelper(this);
        deleteContact = (Button) findViewById(R.id.deleteContact);
        isDeleteClicked = false;

        recycleView = (RecyclerView) findViewById(R.id.recycleView);
        recycleView.setHasFixedSize(true);
        recycleView.setLayoutManager(new LinearLayoutManager(this));

        listItems = new ArrayList<>();

        insertContactsIntoViews();

        }

    @Override
    protected void onResume() {
        insertContactsIntoViews();
        super.onResume();
    }

    /**
     * open new activity where you can add a contact.
     * @param view
     */
    public void addContact(View view){
        Intent intent = new Intent(this,NewContact.class);
        startActivity(intent);

        if (NewContact.isNewContact){
            Cursor res = dbHelper.getLastAddedData();
            System.out.println(res);
            listItems.add(new ContactModel(res.getString(0),res.getString(1),res.getString(2)));
        }
    }



    /**
     * Read from database and insert data into recycleview.
     */
    public void insertContactsIntoViews(){

        // delete recycle by adding an empty list item
        listItems.clear();
        adapter = new AdaptorRecycleView(listItems,getApplicationContext());
        recycleView.setAdapter(adapter);


        if (dbHelper != null){
            Cursor res = dbHelper.getAllData();

            if (res.getCount() == 0){
                Toast.makeText(this, "No contacts yet", Toast.LENGTH_LONG).show();
            }
            else{
                while (res.moveToNext()){
                    listItems.add(new ContactModel(res.getString(1),res.getString(2),res.getString(3)));
                }


                //reload recycleview
                adapter = new AdaptorRecycleView(listItems,getApplicationContext());
                recycleView.setAdapter(adapter);
            }
        }
    }

    public void deleteContact(View view) {


        if (!isDeleteClicked){
            for (ContactModel contact : listItems) {
                contact.setDeleteBtnVisible(true);
            }
            deleteContact.setText("X");
            adapter = new AdaptorRecycleView(listItems,getApplicationContext());
            recycleView.setAdapter(adapter);
            adapter.notifyDataSetChanged();
            isDeleteClicked = true;
        }
        else{
            for (ContactModel contact : listItems) {
                contact.setDeleteBtnVisible(false);
            }
            deleteContact.setText("-");
            adapter = new AdaptorRecycleView(listItems,getApplicationContext());
            recycleView.setAdapter(adapter);
            adapter.notifyDataSetChanged();
            isDeleteClicked = false;
        }


    }
}
