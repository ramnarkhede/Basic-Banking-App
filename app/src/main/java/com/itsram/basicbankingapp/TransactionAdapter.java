package com.itsram.basicbankingapp;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class TransactionAdapter extends RecyclerView.Adapter<TransactionAdapter.viewHolder> {
    private Context context;
    private List<Model>list;

    public TransactionAdapter(Context context, List<Model> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public TransactionAdapter.viewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.transaction_item,parent,false);
      return new viewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TransactionAdapter.viewHolder holder, int position) {
        String status=list.get(position).getStatus();

    holder.transactionID.setText("TransactionID :"+String.valueOf(list.get(position).getTransactionId()));
    holder.senderReceiver.setText(list.get(position).getSenderName()+"   \u2B95   "+list.get(position).getReceiverName());
    holder.date.setText(list.get(position).getDate());
    holder.amount.setText("₹ "+list.get(position).getAmount()+".00");
    holder.status.setText(list.get(position).getStatus());

        if (status.equals("Failed")){
            holder.status.setTextColor(Color.parseColor("#FFD32F2F"));
        }

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class viewHolder extends RecyclerView.ViewHolder {
        TextView transactionID,date,senderReceiver,amount,status;
        public viewHolder(@NonNull View itemView) {
            super(itemView);
            transactionID=itemView.findViewById(R.id.transactionID);
            date=itemView.findViewById(R.id.dateItem);
            senderReceiver=itemView.findViewById(R.id.senderReceiver);
            amount=itemView.findViewById(R.id.amountSent);
            status=itemView.findViewById(R.id.status);


        }
    }
}
