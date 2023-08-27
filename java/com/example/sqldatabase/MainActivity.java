package com.example.sqldatabase;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    RecyclerView recyclerView;
    FloatingActionButton floatingActionButton;
    DbHelper Helper;
    ArrayList<ModelClass> arrayList = new ArrayList<>();
    customAdapter adapter;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        recyclerView = findViewById(R.id.R_v);
        floatingActionButton = findViewById(R.id.btnF);


        adapter = new customAdapter(MainActivity.this, this,arrayList);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter.notifyDataSetChanged();
        recyclerView.setAdapter(adapter);

        
         // floatingButton show inserted data layout
        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, addActivity.class);
                startActivity(intent);
            }
        });
        Helper =new  DbHelper(this);
        displayData();
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode==1){
            recreate();
        }
    }

    // Fetch data
    void displayData() {
        Cursor cursor = Helper.readAllData();
        if (cursor.getCount() == 0) {
            Toast.makeText(this, "Data Not available", Toast.LENGTH_SHORT).show();
        } else {
            while (cursor.moveToNext()) {
                arrayList.add(new ModelClass(cursor.getString(0), cursor.getString(1), cursor.getString(2), cursor.getString(3)));




            }
        }
    }
}