package com.itsram.basicbankingapp;

public class Model {
String id,name,email,accountNo,balance;

String date,senderName,receiverName,amount,status;
int transactionId;

    public int getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(int transactionId) {
        this.transactionId = transactionId;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getSenderName() {
        return senderName;
    }

    public void setSenderName(String senderName) {
        this.senderName = senderName;
    }

    public String getReceiverName() {
        return receiverName;
    }

    public void setReceiverName(String receiverName) {
        this.receiverName = receiverName;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Model(int transactionId, String date, String senderName, String receiverName, String amount, String status) {
        this.transactionId = transactionId;
        this.date = date;
        this.senderName = senderName;
        this.receiverName = receiverName;
        this.amount = amount;
        this.status = status;
    }

    public Model(String id, String name, String email, String accountNo, String balance) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.accountNo = accountNo;
        this.balance = balance;
    }

    public Model(String id, String name, String balance) {
        this.id = id;
        this.name = name;
        this.balance = balance;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAccountNo() {
        return accountNo;
    }

    public void setAccountNo(String accountNo) {
        this.accountNo = accountNo;
    }

    public String getBalance() {
        return balance;
    }

    public void setBalance(String balance) {
        this.balance = balance;
    }
}
