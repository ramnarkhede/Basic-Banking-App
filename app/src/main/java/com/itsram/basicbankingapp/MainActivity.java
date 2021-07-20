package com.itsram.basicbankingapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    private Button viewCustomers,viewTransactions;
    DatabaseHelper db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        viewCustomers = findViewById(R.id.viewCustomers);
        viewTransactions = findViewById(R.id.viewTransaction);
        db = new DatabaseHelper(this);

        viewCustomers.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, CustomerActivity.class);
                startActivity(intent);
            }
        });
        viewTransactions.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, TransactionHistory.class);
                startActivity(intent);
            }
        });

        getSupportActionBar().hide();
    }

    @Override
    public void onBackPressed() {
        finishAffinity();
    }
}
