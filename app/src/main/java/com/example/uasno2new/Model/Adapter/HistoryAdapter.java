package com.example.uasno2new.Model.Adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.uasno2new.Model.ItemHistory;
import com.example.uasno2new.R;

import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class HistoryAdapter extends RecyclerView.Adapter<HistoryAdapter.myViewHolder> {

    ArrayList<ItemHistory> arrHistory;

    public HistoryAdapter(ArrayList<ItemHistory> arrHistory) {
        this.arrHistory = arrHistory;
    }


    @NonNull
    @Override
    public HistoryAdapter.myViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View myv = inflater.inflate(R.layout.recycler_history,parent,false);
        return new myViewHolder(myv);
    }

    @Override
    public void onBindViewHolder(@NonNull myViewHolder holder, int position) {
        ItemHistory history = arrHistory.get(position);
        holder.image.setImageResource(history.get_Item().get_imageSource());
        holder.nameAndSize.setText(history.get_Item().get_nameItemAndSize());
        holder.price.setText("Rp " + String.format("%,d", history.get_Item().get_price()).replace(",", ".") + ",00");

        DateTimeFormatter formatterDate = DateTimeFormatter.ofPattern("dd MMM yyy");
        String formatDate = history.get_Date().format(formatterDate);
        holder.date.setText(formatDate);

        DateTimeFormatter formatterTime = DateTimeFormatter.ofPattern("hh:mm a");
        String formatTime = history.get_Time().format(formatterTime);
        holder.time.setText(formatTime);



    }

    @Override
    public int getItemCount() {
        return arrHistory.size();
    }

    public class myViewHolder extends RecyclerView.ViewHolder {

        private TextView date, time, price, nameAndSize;

        private ImageView image;
        public myViewHolder(@NonNull View historyView) {
            super(historyView);

            image = historyView.findViewById(R.id.imageItemSource);
            date = historyView.findViewById(R.id.txt_date);
            time = historyView.findViewById(R.id.txt_time);
            nameAndSize = historyView.findViewById(R.id.txt_ItemNameSource);
            price = historyView.findViewById(R.id.txt_priceSource);
        }
    }
}
