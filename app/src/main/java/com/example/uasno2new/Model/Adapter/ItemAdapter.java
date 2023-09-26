package com.example.uasno2new.Model.Adapter;

import android.content.Intent;
import android.os.Parcelable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.uasno2new.ItemDetail;
import com.example.uasno2new.Model.ItemList;
import com.example.uasno2new.R;

import java.util.ArrayList;

interface onClickItem{
    void ClickItem(View view, int position);
}

public class ItemAdapter extends RecyclerView.Adapter<ItemAdapter.myViewHolder> implements onClickItem {



    ArrayList<ItemList> listItem;

    public ItemAdapter(ArrayList<ItemList> listItem) {
        this.listItem = listItem;
    }

    @NonNull
    @Override
    public myViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View myview = inflater.inflate(R.layout.recycler_item,parent,false);
        return new myViewHolder(myview);
    }

    @Override
    public void onBindViewHolder(@NonNull myViewHolder holder, int position) {
        ItemList item = listItem.get(position);
        holder.imageItem.setImageResource(item.get_imageSource());
        holder.txtItemName.setText(item.get_nameItemAndSize());
        holder.txtItemPrice.setText("Rp " + String.format("%,d", item.get_price()).replace(",", ".") + ",00");


        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(view.getContext(), ItemDetail.class);
                intent.putExtra("item", (Parcelable) listItem.get(position));
                view.getContext().startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return listItem.size();
    }

    @Override
    public void ClickItem(View view, int position) {
        Intent intent = new Intent(view.getContext(), ItemDetail.class);
        intent.putExtra("item", (Parcelable) listItem.get(position));
        view.getContext().startActivity(intent);
    }

    public static class myViewHolder extends RecyclerView.ViewHolder {
        private TextView txtItemName,txtItemPrice;

        private ImageView imageItem;

        public myViewHolder(@NonNull View itemView) {
            super(itemView);
            imageItem = itemView.findViewById(R.id.imageItem);
            txtItemName = itemView.findViewById(R.id.txtItemName);
            txtItemPrice = itemView.findViewById(R.id.txtItemPrice);
        }
    }
}
