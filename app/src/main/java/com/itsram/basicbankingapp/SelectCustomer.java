package com.itsram.basicbankingapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.database.Cursor;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.LinearLayout;

import java.util.ArrayList;
import java.util.List;

public class SelectCustomer extends AppCompatActivity {

    public String senderId;
    private List<Model> list;
    private RecyclerView recyclerView;
    SelectCustomerAdapter adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_customer);

        recyclerView=findViewById(R.id.selectCustomersRV);
        recyclerView.setHasFixedSize(true);
        RecyclerView.LayoutManager layoutManager=new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        list=new ArrayList<>();

        senderId=getIntent().getExtras().getString("CustomerID");

        selectedCustomer(senderId);


        adapter.senderID=senderId;

        getSupportActionBar().setTitle("Select Customer");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


    }
    private void selectedCustomer(String senderId)
    {
        list.clear();
        Cursor cursor=new DatabaseHelper(this).viewCustomersToTransferMoney(senderId);
        while (cursor.moveToNext()){
            Model model=new Model(cursor.getString(0),cursor.getString(1),cursor.getString(4));
            this.list.add(model);
        }

        adapter=new SelectCustomerAdapter(SelectCustomer.this,list);
        recyclerView.setAdapter(adapter);
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
