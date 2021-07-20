package com.itsram.basicbankingapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class CustomerDetails extends AppCompatActivity {
    private TextView name,email,accountNo,balance;
    private Button transferBtn;
    private String id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customer_details);

        name=findViewById(R.id.nameDetail);
        email=findViewById(R.id.emailDetail);
        accountNo=findViewById(R.id.accountNoDetail);
        balance=findViewById(R.id.balanceDetail);
        transferBtn=findViewById(R.id.transferBtn);

        id=getIntent().getExtras().getString("CustomerID");


        showData(id);


        transferBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(CustomerDetails.this,SelectCustomer.class);
                intent.putExtra("CustomerID",id);
                startActivity(intent);

            }
        });


        getSupportActionBar().setTitle("Customer Detail");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

    }


    private void showData(String id){
        Cursor cursor=new DatabaseHelper(this).viewCustomerFromID(id);
        while(cursor.moveToNext()){
            name.setText("Name : "+cursor.getString(1));
            email.setText("Email : "+cursor.getString(2));
            accountNo.setText("Account No : "+cursor.getString(3));
            balance.setText("Available Balance : ₹ "+cursor.getString(4));
        }


    }


    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        if(item.getItemId()==android.R.id.home){
            finish();
        }

        return super.onOptionsItemSelected(item);
    }
}
