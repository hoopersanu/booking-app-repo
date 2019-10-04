package com.example.login;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class StoreAdapter extends RecyclerView.Adapter<StoreAdapter.myHolder> {
    private List<StoreListItem> listItems;
    private Context context;

    public StoreAdapter(List<StoreListItem> listItems, Context context) {
        this.listItems = listItems;
        this.context = context;
    }

    @NonNull
    @Override
    public myHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v= LayoutInflater.from(parent.getContext())
                .inflate(R.layout.store_item_list,parent,false);
        return new myHolder(v);

    }

    @Override
    public void onBindViewHolder(@NonNull myHolder holder, int position) {

        StoreListItem listItem = listItems.get(position);
        holder.textView1.setText(listItem.getTextViewTitle());
    }

    @Override
    public int getItemCount() {
        return listItems.size();
    }

    public class myHolder extends RecyclerView.ViewHolder {
        public TextView textView1;
        public myHolder(@NonNull View itemView) {
            super(itemView);
            textView1 = (TextView) itemView.findViewById(R.id.serviceTitle);
        }
    }
}
