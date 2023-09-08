package com.example.sqlite;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class note_test extends AppCompatActivity {

    DatabaseHelper2 myDB;
    ImageView getData;

    EditText title, note;
    TextView date;
    ImageButton save;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_note_test);

        getData = findViewById(R.id.backnote);
        title = findViewById(R.id.title2);
        date = findViewById(R.id.dateText);
        note = findViewById(R.id.notetext);
        save = findViewById(R.id.save);
        myDB = new DatabaseHelper2(this);
        //create a date string.
        String date_n = new SimpleDateFormat("MMM dd, yyyy", Locale.getDefault()).format(new Date());
//get hold of textview.
        TextView date  = (TextView) findViewById(R.id.dateText);
//set it as current date.
        date.setText(date_n);

        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String Title_txt = title.getText().toString().trim();
                String Date_txt = date.getText().toString().trim();
                String Note_txt = note.getText().toString().trim();
                if(title.length()!= 0 || date.length()!= 0 || note.length()!= 0 ){
//                    Toast.makeText(note_test.this, "Note Saved", Toast.LENGTH_LONG).show();
                    AddData(Title_txt,Date_txt,Note_txt);
                    title.setText("");
                    note.setText("");
                    startActivity(new Intent(getApplicationContext(), MainHome.class));
                }else{
                    Toast.makeText(note_test.this, "You must put something in the text fields!", Toast.LENGTH_LONG).show();
                }
            }
        });

        getData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                startActivity(new Intent(getApplicationContext(), display.class));
                startActivity(new Intent(getApplicationContext(), MainHome.class));
            }
        });
    }
    public void AddData(String item1,String item2,String item3) {

        boolean insertData = myDB.addData(item1,item2,item3);

        if(insertData==true){
            Toast.makeText(this, "Data Successfully Inserted!", Toast.LENGTH_LONG).show();
        }else{
            Toast.makeText(this, "Something went wrong :(.", Toast.LENGTH_LONG).show();
        }
    }

//    private void saveData() {
//
//        String title_txt = title.getText().toString().trim();
//        String date_txt = date.getText().toString().trim();
//        String note_txt = note.getText().toString().trim();
//
//
//        noteModel model = new noteModel();
//        model.setTitle(title_txt);
//        model.setDate(date_txt);
//        model.setNote(note_txt);
//        database.getDatabase(getApplicationContext()).getDao().insertAllData(model);
//
//        title.setText("");
//        note.setText("");
////        Toast.makeText(this, "Data Successfully Saved", Toast.LENGTH_SHORT).show();
//        startActivity(new Intent(getApplicationContext(), MainHome.class));
//
//    }
}




