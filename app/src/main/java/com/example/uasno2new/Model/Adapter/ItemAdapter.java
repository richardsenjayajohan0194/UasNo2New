package com.example.uasno2new.Model.Adapter;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.uasno2new.ItemDetail;
import com.example.uasno2new.Model.ItemList;
import com.example.uasno2new.R;

import java.util.ArrayList;

public class ItemAdapter extends RecyclerView.Adapter<ItemAdapter.myViewHolder>{

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
//                  Toast.makeText(view.getContext(), "Selected Items: " + item.get_nameItemAndSize(), Toast.LENGTH_LONG).show();
                intent.putExtra("ImageSource", listItem.get(position).get_imageSource());
                intent.putExtra("NameAndSize", String.valueOf(listItem.get(position).get_nameItemAndSize()));
                intent.putExtra("Price", String.valueOf(listItem.get(position).get_price()));
                view.getContext().startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return listItem.size();
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
