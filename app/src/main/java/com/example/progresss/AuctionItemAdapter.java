package com.example.progresss;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.List;

public class AuctionItemAdapter extends RecyclerView.Adapter<AuctionItemAdapter.AuctionItemViewHolder> {

    private List<AuctionItem> auctionItemList;
    private Context context;


    public AuctionItemAdapter(Context context, List<AuctionItem> auctionItemList) {
        this.context = context;
        this.auctionItemList = auctionItemList;
    }

    @NonNull
    @Override
    public AuctionItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_lelang, parent, false);
        return new AuctionItemViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AuctionItemViewHolder holder, int position) {
        AuctionItem item = auctionItemList.get(position);
        holder.nameTextView.setText(item.getName());
        holder.priceTextView.setText(item.getHarga());
        holder.statusTextView.setText("Live Now!");

        // Memuat gambar dari URL menggunakan Glide
        Glide.with(holder.itemView.getContext())
                .load(item.getImageUrl())
                .placeholder(R.drawable.hiace)
                .into(holder.itemImageView);

        // Tambahkan listener untuk menangani klik item
        holder.itemView.setOnClickListener(v -> {
            Intent intent = new Intent(context, DetailActivity.class);
            intent.putExtra("itemName", item.getName());
            intent.putExtra("itemPrice", item.getHarga());
            intent.putExtra("itemImageUrl", item.getImageUrl());
            context.startActivity(intent);
        });
    }

    @Override
    public int getItemCount() {
        return auctionItemList.size();
    }

    public static class AuctionItemViewHolder extends RecyclerView.ViewHolder {
        TextView nameTextView, priceTextView, statusTextView;
        ImageView itemImageView;

        public AuctionItemViewHolder(@NonNull View itemView) {
            super(itemView);
            nameTextView = itemView.findViewById(R.id.tvNama);
            priceTextView = itemView.findViewById(R.id.tvHarga);
            statusTextView = itemView.findViewById(R.id.recLang);
            itemImageView = itemView.findViewById(R.id.IvImage);
        }
    }
}
