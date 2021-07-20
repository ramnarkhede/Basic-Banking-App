package com.itsram.basicbankingapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.database.Cursor;
import android.os.Bundle;
import android.view.MenuItem;

import java.util.ArrayList;
import java.util.List;

public class TransactionHistory extends AppCompatActivity {
    private RecyclerView viewTransactionRV;
    List<Model> list;
    private DatabaseHelper db;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_transaction_history);
        viewTransactionRV=findViewById(R.id.transactionRV);
        viewTransactionRV.setHasFixedSize(true);

        list=new ArrayList<>();
        db=new DatabaseHelper(TransactionHistory.this);
        RecyclerView.LayoutManager layoutManager=new LinearLayoutManager(this);
        viewTransactionRV.setLayoutManager(layoutManager);

        getSupportActionBar().setTitle("Transaction History");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        showData();
    }
    private void showData(){
        list.clear();
        Cursor cursor=new DatabaseHelper(this).viewAllTransaction();

        while (cursor.moveToNext()){
            Model model=new Model(cursor.getInt(0),cursor.getString(1),cursor.getString(2),cursor.getString(3),cursor.getString(4),cursor.getString(5));
            this.list.add(model);
        }
        TransactionAdapter adapter= new TransactionAdapter(TransactionHistory.this,list);
        viewTransactionRV.setAdapter(adapter);
        adapter.notifyDataSetChanged();

    }


    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        if(item.getItemId()==android.R.id.home){
            finish();
        }

        return super.onOptionsItemSelected(item);
    }
}
