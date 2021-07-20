package com.itsram.basicbankingapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class Transaction extends AppCompatActivity {

    private EditText amountEdit;
    private Button submit;
    private TextView nameToTransfer;
    String senderId,receiverId,senderName,receiverName;
    Double senderBalance,receiverBalance;
    String amount;
    String date;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_transaction);

        amountEdit=findViewById(R.id.enterAmount);
        submit=findViewById(R.id.submitBtn);
        nameToTransfer=findViewById(R.id.nameToTransfer);



        senderId=getIntent().getExtras().getString("SenderId");
        receiverId=getIntent().getExtras().getString("ReceiverId");

        getSupportActionBar().setTitle("Transaction");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        senderBalanceCheck(senderId);
        receiverBalanceCheck(receiverId);

        nameToTransfer.setText("Paying "+receiverName);
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                transferMoney();
            }
        });
    }
    private void senderBalanceCheck(String senderId) {
        Cursor cursor = new DatabaseHelper(this).viewCustomerFromID(senderId);
        while (cursor.moveToNext()) {
            senderBalance = Double.parseDouble(cursor.getString(4));
            senderName=cursor.getString(1);


        }

    }
    private void receiverBalanceCheck(String receiverId) {
        Cursor cursor = new DatabaseHelper(this).viewCustomerFromID(receiverId);
        while (cursor.moveToNext()) {

         receiverBalance = Double.parseDouble(cursor.getString(4));
            receiverName=cursor.getString(1);


        }
    }


    private void transferMoney()
    {
        amount=amountEdit.getText().toString();
       Calendar calendar=Calendar.getInstance();
        SimpleDateFormat dateFormat=new SimpleDateFormat("dd-MMMM, hh:mm");
        date=dateFormat.format(calendar.getTime());

        int transactionId=(int)(Math.random()*(50000000-10000000+1)+10000000);
        if (amount.isEmpty())
        {
            AlertDialog alertDialog=new AlertDialog.Builder(Transaction.this).create();
            alertDialog.setTitle("Error");
            alertDialog.setMessage("Please Enter Amount..");
            alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "OK",
                    new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            dialogInterface.dismiss();
                        }
                    });
            alertDialog.show();
        }
        else if (Double.parseDouble(amount)>senderBalance){
            AlertDialog alertDialog = new AlertDialog.Builder(Transaction.this).create();
            alertDialog.setTitle("Insufficient Balance");
            alertDialog.setMessage("You Don't Have Enough Money.");
            alertDialog.setCancelable(true);
            alertDialog.setCanceledOnTouchOutside(true);
            alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "OK",
                    new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.dismiss();
                               closeActivity();
                        }
                    });
            alertDialog.show();

            Toast.makeText(this,"transaction  Failed",Toast.LENGTH_LONG).show();

            new DatabaseHelper(this).insertTransaction(transactionId,date,senderName,receiverName,amount,"Failed");

           closeActivity();
        }else {
            senderBalance = senderBalance - Double.parseDouble(amount);
            new DatabaseHelper(this).updateAmount(senderId , senderBalance);
            receiverBalance = receiverBalance + Double.parseDouble(amount);
            new DatabaseHelper(this).updateAmount(receiverId , receiverBalance);

            Toast.makeText(this,"transaction Done Successfully",Toast.LENGTH_SHORT).show();


            boolean result = new DatabaseHelper(this).insertTransaction(transactionId,date,senderName,receiverName,amount,"Successful");
            if (result == true){
                Toast.makeText(this, "SuccessFull", Toast.LENGTH_LONG).show();

            }
            closeActivity();


        }

        }

        public void closeActivity(){
            Intent intent=new Intent(Transaction.this,MainActivity.class);
            startActivity(intent);
            finish();
        }
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        if(item.getItemId()==android.R.id.home){
            finish();
        }

        return super.onOptionsItemSelected(item);
    }


}
