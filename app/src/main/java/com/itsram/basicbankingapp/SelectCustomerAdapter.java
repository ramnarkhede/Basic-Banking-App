package com.itsram.basicbankingapp;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class SelectCustomerAdapter extends RecyclerView.Adapter<SelectCustomerAdapter.viewHolder> {
  private Context context;
  private List<Model>list;
  String senderID;

    public SelectCustomerAdapter(Context context, List<Model> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public SelectCustomerAdapter.viewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.select_customer_item,parent,false);
        return new viewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull SelectCustomerAdapter.viewHolder holder, int position) {
            holder.name.setText(list.get(position).getName());
            holder.balance.setText(list.get(position).getBalance());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class viewHolder extends RecyclerView.ViewHolder {
      TextView name,balance;
        public viewHolder(@NonNull final View itemView) {
            super(itemView);

            name=itemView.findViewById(R.id.nameSelect);
            balance=itemView.findViewById(R.id.balanceSelect);


            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int position=getAdapterPosition();
                    Model model=list.get(position);
                    Intent intent=new Intent(itemView.getContext(), Transaction.class);
                    intent.putExtra("SenderId",senderID);
                    intent.putExtra("ReceiverId",model.getId());
                    itemView.getContext().startActivity(intent);
                }
            });
        }
    }
}
