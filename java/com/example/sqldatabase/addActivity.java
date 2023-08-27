package com.example.sqldatabase;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class addActivity extends AppCompatActivity {


    EditText edTitle,edAuthor,edPage;
    Button btn_Add;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);
        DbHelper dbHelper= new DbHelper(addActivity.this);

        edTitle=findViewById(R.id.title);
        edAuthor=findViewById(R.id.author);
        edPage=findViewById(R.id.page);


        btn_Add=findViewById(R.id.btn_add);



        ActionBar b= getSupportActionBar();
        if (b !=null){
            b.setTitle("Add Data");
        }

        btn_Add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dbHelper.addData(edTitle.getText().toString().trim(),edAuthor.getText().toString().trim(),edPage.getText().toString().trim());
                Toast.makeText(addActivity.this, "Add Data Successfully", Toast.LENGTH_SHORT).show();

            }
        });

    }
}