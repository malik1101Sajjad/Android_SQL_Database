package com.example.sqldatabase;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class customAdapter extends RecyclerView.Adapter<customAdapter.ViewHolder> {
    Activity activity;
    Context context;
    ArrayList<ModelClass> arrayList1;

    public customAdapter(Activity activity,Context context, ArrayList<ModelClass> arrayList) {
        this.activity=activity;
        this.context = context;
        this.arrayList1 = arrayList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v= LayoutInflater.from(context).inflate(R.layout.my_row,parent,false);
        ViewHolder viewHolder= new ViewHolder(v);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, @SuppressLint("RecyclerView") int position) {


        holder.iD.setText(arrayList1.get(position).id);
        holder.Title.setText(arrayList1.get(position).tile);
        holder.Author.setText(arrayList1.get(position).author);
        holder.pages.setText(arrayList1.get(position).page);

        holder.layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent= new Intent(context, update.class);
                intent.putExtra("id",String.valueOf(arrayList1.get(position).id));
                intent.putExtra("title",String.valueOf(arrayList1.get(position).tile));
                intent.putExtra("author",String.valueOf(arrayList1.get(position).author));
                intent.putExtra("pages",String.valueOf(arrayList1.get(position).page));
                activity.startActivityForResult(intent,1);
            }
        });

    }

    @Override
    public int getItemCount() {
        return arrayList1.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView iD,Title,Author,pages;
        LinearLayout layout;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            iD=itemView.findViewById(R.id.ID_Row);
            Title=itemView.findViewById(R.id.Title_Row);
            Author=itemView.findViewById(R.id.author_Row);
            pages=itemView.findViewById(R.id.page_Row);
            layout=itemView.findViewById(R.id.mainLayout);
        }
    }
}
