package com.example.sqlite;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainHome extends AppCompatActivity {

    RecyclerView recyclerview;
    ImageButton click;
    DatabaseHelper2 myDB;
    ArrayList<String> Id,Title,Date,Note;

    RecyclerView recyclerView;

    CustomAdapter customAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_home);

        recyclerView = findViewById(R.id.recyclerView);
        click = findViewById(R.id.addnote);
        click.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), note_test.class));
//                startActivity(new Intent(getApplicationContext(), EditNote.class));
            }
        });

//        empty_imageview = findViewById(R.id.empty_imageview);
//        no_data = findViewById(R.id.no_data);
//        add_button.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent intent = new Intent(MainActivity.this, AddActivity.class);
//                startActivity(intent);
//            }
//        });

        myDB = new DatabaseHelper2(MainHome.this);
        Id = new ArrayList<>();
        Title = new ArrayList<>();
        Date = new ArrayList<>();
        Note = new ArrayList<>();

        storeDataInArrays();
        getData();
//        customAdapter = new CustomAdapter(MainHome.this,this, Id,Title,Date,Note,new CustomAdapter.DeleteItemClicklistner() {
//            @Override
//            public void onItemDelete(int position, String id) {
////                database.getDatabase(getApplicationContext()).getDao().deleteData(id);
//                myDB.deleteOneRow(id);
////                getData();
//            }
//
//        });
//        recyclerView.setAdapter(customAdapter);
//        recyclerView.setLayoutManager(new LinearLayoutManager(MainHome.this));

    }

    private void getData() {

//        myDB = new DatabaseHelper2(MainHome.this);
//        Id = new ArrayList<>();
//        Title = new ArrayList<>();
//        Date = new ArrayList<>();
//        Note = new ArrayList<>();
        customAdapter = new CustomAdapter(MainHome.this,this, Id,Title,Date,Note,new CustomAdapter.DeleteItemClicklistner() {
            @Override
            public void onItemDelete(int position, String id) {
//                database.getDatabase(getApplicationContext()).getDao().deleteData(id);

                myDB.deleteOneRow(id);
//                recyclerView.setAdapter(null);
                Toast.makeText(MainHome.this, "Note Deleted", Toast.LENGTH_SHORT).show();
                myDB = new DatabaseHelper2(MainHome.this);
                Id = new ArrayList<>();
                Title = new ArrayList<>();
                Date = new ArrayList<>();
                Note = new ArrayList<>();
                storeDataInArrays();
                getData();


            }

        });
        recyclerView.setAdapter(customAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(MainHome.this));
    }



    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == 1){
            recreate();
        }
    }

    void storeDataInArrays(){
        Cursor cursor = myDB.readAllData();
        if(cursor.getCount() == 0){

        }else{
            while (cursor.moveToNext()){
                Id.add(cursor.getString(0));
                Title.add(cursor.getString(1));
                Date.add(cursor.getString(2));
                Note.add(cursor.getString(3));
            }

        }


    }


}

