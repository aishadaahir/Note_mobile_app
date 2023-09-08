package com.example.sqlite;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.MyViewHolder> {
    DatabaseHelper2 myDB;
    private Context context;
    private Activity activity;
    private ArrayList Id,Title,Date,Note;

    DeleteItemClicklistner deleteItemClicklistner;

    CustomAdapter(Activity activity, Context context, ArrayList Id, ArrayList Title, ArrayList Date,
                  ArrayList Note, DeleteItemClicklistner deleteItemClicklistner){
        this.activity = activity;
        this.context = context;
        this.Id = Id;
        this.Title = Title;
        this.Date = Date;
        this.Note = Note;
        this.deleteItemClicklistner = deleteItemClicklistner;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.notelayout, parent, false);
        return new MyViewHolder(view);
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    public void onBindViewHolder(@NonNull final MyViewHolder holder, final int position) {
//        holder.book_id_txt.setText(String.valueOf(book_id.get(position)));
        holder.Title.setText(String.valueOf(Title.get(position)));
        holder.Date.setText(String.valueOf(Date.get(position)));
        holder.Note.setText(String.valueOf(Note.get(position)));

        //Recyclerview onClickListener
        holder.update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, EditNote.class);
                intent.putExtra("ID", String.valueOf(Id.get(position)));
                intent.putExtra("Title", String.valueOf(Title.get(position)));
                intent.putExtra("Date", String.valueOf(Date.get(position)));
                intent.putExtra("Note", String.valueOf(Note.get(position)));
                activity.startActivityForResult(intent, 1);
            }
        });
        holder.delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                deleteItemClicklistner.onItemDelete(position, list.get(position).getKey());
//                myDB.deleteOneRow(id);
                Intent intent = new Intent(context, EditNote.class);
                intent.putExtra("ID", String.valueOf(Id.get(position)));
                String id = String.valueOf(Id.get(position));
//                Toast.makeText(MainHome.this, "All Field is required ....", Toast.LENGTH_SHORT).show();

                deleteItemClicklistner.onItemDelete(position, id);



            }
        });



    }

    @Override
    public int getItemCount() {
        return Id.size();
    }

    public interface DeleteItemClicklistner {
        void onItemDelete(int position, String id);
    }

    class MyViewHolder extends RecyclerView.ViewHolder {

        TextView Title, Date,Note;
        Button update, delete;

        MyViewHolder(@NonNull View itemView) {
            super(itemView);
            Title = itemView.findViewById(R.id.titel);
            Date = itemView.findViewById(R.id.date);
            Note = itemView.findViewById(R.id.Note);
            update = itemView.findViewById(R.id.updateId);
            delete = itemView.findViewById(R.id.deleteId);
            //Animate Recyclerview
//            Animation translate_anim = AnimationUtils.loadAnimation(context, R.anim.translate_anim);
//            mainLayout.setAnimation(translate_anim);
        }



    }

}