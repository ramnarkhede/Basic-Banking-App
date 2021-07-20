package com.itsram.basicbankingapp;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class CustomerAdapter extends RecyclerView.Adapter<CustomerAdapter.view1holder> {

   private List<Model>list;

    public CustomerAdapter(List<Model> list) {

        this.list = list;
    }

    @NonNull
    @Override
    public CustomerAdapter.view1holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.customer_item,parent,false);
        return new view1holder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CustomerAdapter.view1holder holder, int position) {
holder.name.setText(list.get(position).getName());
holder.balance.setText(list.get(position).getBalance());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class view1holder extends RecyclerView.ViewHolder {
        TextView name,balance;
        public view1holder(@NonNull final View itemView) {
            super(itemView);

            name=itemView.findViewById(R.id.name);
            balance=itemView.findViewById(R.id.balance);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int position=getAdapterPosition();
                    Model model=list.get(position);

                    Intent intent=new Intent(itemView.getContext(),CustomerDetails.class);
                    intent.putExtra("CustomerID",model.getId());
                    String str=model.getId();
                    Log.i("message",str);
                    itemView.getContext().startActivity(intent);
                }
            });

        }
    }
}
