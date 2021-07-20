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

public class CustomerActivity extends AppCompatActivity {
    private RecyclerView viewCustomerRV;
    List<Model>list;
    private DatabaseHelper db;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customer);
        viewCustomerRV=findViewById(R.id.viewCustomersRV);
        viewCustomerRV.setHasFixedSize(true);

        list=new ArrayList<>();
        db=new DatabaseHelper(CustomerActivity.this);
        RecyclerView.LayoutManager layoutManager=new LinearLayoutManager(this);
        viewCustomerRV.setLayoutManager(layoutManager);

        getSupportActionBar().setTitle("Customers");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        showData();

    }

    private void showData(){
        list.clear();
        Cursor cursor=new DatabaseHelper(this).viewAllCustomers();

        while (cursor.moveToNext()){
            Model model=new Model(cursor.getString(0),cursor.getString(1),cursor.getString(4));
            this.list.add(model);
        }
        CustomerAdapter adapter=new CustomerAdapter(list);
        viewCustomerRV.setAdapter(adapter);
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
