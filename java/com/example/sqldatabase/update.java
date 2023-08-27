package com.example.sqldatabase;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


public class update extends AppCompatActivity {


    String id_s, title_s, author_s, pages_s;
    EditText upId,upTitle,upAuthor,upPage;
    Button btn_update_up,btn_delete;
    
    @SuppressLint("MissingInflatedId")
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update);

        upId=findViewById(R.id.id_2);
        upTitle=findViewById(R.id.title_2);
        upAuthor=findViewById(R.id.author_2);
        upPage=findViewById(R.id.page_2);
        btn_update_up=findViewById(R.id.update_btn_up);
        btn_delete=findViewById(R.id.btn_delete);

        ActionBar b= getSupportActionBar();
        if (b !=null){
            b.setTitle("Update&Delete Data");
        }

        ////
        btn_delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                DeleteData();
            }
        });
        ////
        btn_update_up.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DbHelper dbh=new DbHelper(update.this);
                dbh.updateData(upId.getText().toString(),upTitle.getText().toString(),upAuthor.getText().toString(),upPage.getText().toString());
                Toast.makeText(update.this, "update Successfully", Toast.LENGTH_SHORT).show();
            }
        });
        //////
        getIntentData();
    }
    void getIntentData(){
        if (getIntent().hasExtra("id")
                && getIntent().hasExtra("title") && getIntent().hasExtra("author") && getIntent().hasExtra("pages")){


            id_s=getIntent().getStringExtra("id");
            title_s=getIntent().getStringExtra("title");
            author_s=getIntent().getStringExtra("author");
            pages_s=getIntent().getStringExtra("pages");

            upId.setText(id_s);
            upTitle.setText(title_s);
            upAuthor.setText(author_s);
            upPage.setText(pages_s);
        }
        else
        {
            Toast.makeText(this, "No Data", Toast.LENGTH_SHORT).show();
        }
    }
    void DeleteData(){
        AlertDialog.Builder builder= new AlertDialog.Builder(this)
                .setTitle("Delete"+" "+title_s+" "+"?")
                .setMessage("Are you sure you want to Delete ?");
        builder.setPositiveButton("yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        DbHelper dbHelper=new DbHelper(update.this);
                        dbHelper.deleteData(id_s);
                        Toast.makeText(update.this, "Delete Successfully", Toast.LENGTH_SHORT).show();
                    }
                });
        builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                Toast.makeText(update.this, "Not Delete", Toast.LENGTH_SHORT).show();
            }
        });
        builder.show().create();

    }

}
