package com.example.login;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class PhotographerListAdapter extends RecyclerView.Adapter<PhotographerListAdapter.viewHolder> {


    private List<PhotographerListItems> listItems;
    private Context context;

    public PhotographerListAdapter(List<PhotographerListItems> listItems, Context context) {
        this.listItems = listItems;
        this.context = context;
    }

    @NonNull
    @Override
    public viewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View v= LayoutInflater.from(parent.getContext())
                .inflate(R.layout.list_view_photographer,parent,false);
        return new viewHolder(v);

    }

    @Override
    public void onBindViewHolder(@NonNull viewHolder holder, int position) {
        PhotographerListItems listItem = listItems.get(position);
        holder.textView1.setText(listItem.getTextViewTitle());

    }

    @Override
    public int getItemCount() {
        return listItems.size();
    }

    public class viewHolder extends RecyclerView.ViewHolder {
        public TextView textView1;
        public viewHolder(@NonNull View itemView) {
            super(itemView);
            textView1 = (TextView) itemView.findViewById(R.id.textViewTitle);

        }
    }
}
