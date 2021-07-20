package com.itsram.basicbankingapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DatabaseHelper extends SQLiteOpenHelper {
    private String TABLE_CLIENT = "client";
    private String TABLE_TRANSACTION = "transDetails";

    public DatabaseHelper(@Nullable Context context) {
        super(context, "Customer.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table " + TABLE_CLIENT + " (ID INTEGER PRIMARY KEY,NAME TEXT,EMAIL VARCHAR,ACCOUNT_NO VARCHAR,BALANCE VARCHAR)");
        db.execSQL("create table " + TABLE_TRANSACTION + "(TRANSACTION_ID INTEGER PRIMARY KEY,DATE_TRANSACTION VARCHAR,SENDER_NAME TEXT,RECEIVER_NAME TEXT,AMOUNT VARCHAR,STATUS TEXT)");


        db.execSQL("insert into client values(1,'Rohit Rathod','rohitrathod12@gmail.com','245713466125','10000.0')");
        db.execSQL("insert into client values(2,'Pravin Tekade','pravin123@gmail.com','255713445465','15000.0')");
        db.execSQL("insert into client values(3,'Arvind Saini','arvindsaini@gmail.com','278713456125','10600.0')");
        db.execSQL("insert into client values(4,'Rakesh Sharma','rakeshsharma.23@gmail.com','295713415465','50000.0')");
        db.execSQL("insert into client values(5,'Simran Patil','patilsimran45@gmail.com','247615543345','10540.0')");
        db.execSQL("insert into client values(6,'Shweta Raut','shwetaraut34@gmail.com','54612755462','64000.0')");
        db.execSQL("insert into client values(7,'Sarika Rathod','sarikarathod23@gmail.com','12453466125','100000.0')");
        db.execSQL("insert into client values(8,'Pranav Ghode','ghodepranav56@gmail.com','3546715746465','1000.0')");
        db.execSQL("insert into client values(9,'Samruddhi Mahajan','mahajansamu12@gmail.com','255246515465','25000.0')");
        db.execSQL("insert into client values(10,'Sam Khan','samsam123@gmail.com','124513456125','106000.0')");
        db.execSQL("insert into client values(11,'Govind Raj','govnd.23@gmail.com','2457641354235','50000.0')");
        db.execSQL("insert into client values(12,'Shruti Wanare','shruti234@gmail.com','234675224155','10640.0')");
        db.execSQL("insert into client values(13,'Vivak Dhone','vivak13.vk@gmail.com','23455145623452','74000.0')");


    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {

        db.execSQL("DROP TABLE IF EXISTS " + TABLE_CLIENT);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_TRANSACTION);
        onCreate(db);
    }

    //view all customers
    public Cursor viewAllCustomers() {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("select * from client", null);
        return cursor;

    }

    //View All Transaction History
    public Cursor viewAllTransaction(){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("select * from transDetails", null);
        return cursor;
    }

    //view Single Customer From his ID
    public Cursor viewCustomerFromID(String id) {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("select * from client where id = " + id, null);
        return cursor;
    }

    //view All customer except selected id

    public Cursor viewCustomersToTransferMoney(String id) {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("select * from client except select * from client where id = " + id, null);
        return cursor;
    }

    public void updateAmount(String id, Double amount) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("update client set balance = " + amount + " where id = " + id);
    }

    public boolean insertTransaction(int transaction_Id, String date, String senderName, String receiverName, String amount,String status) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();

        contentValues.put("TRANSACTION_ID", transaction_Id);
        contentValues.put("DATE_TRANSACTION", date);
        contentValues.put("SENDER_NAME", senderName);
        contentValues.put("RECEIVER_NAME", receiverName);
        contentValues.put("AMOUNT", amount);
        contentValues.put("STATUS",status);
        Long result=db.insert(TABLE_TRANSACTION,null,contentValues);
        if (result==-1)
        {
            return false;
        }else {
            return true;
        }

    }

}
