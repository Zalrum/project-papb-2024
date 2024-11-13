package com.example.progresss;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
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
            // Ganti Activity ke Fragment untuk menampilkan DetailFragment
            DetailFragment detailFragment = DetailFragment.newInstance(item.getName(), item.getHarga(), item.getImageUrl());

            // Gantikan Fragment yang aktif dengan DetailFragment
            if (context instanceof MainActivity) { // Pastikan context adalah Activity yang memuat Fragment
                MainActivity activity = (MainActivity) context;
                activity.getSupportFragmentManager().beginTransaction()
                        .replace(R.id.fragment_container, detailFragment) // Gantikan fragment_container dengan ID fragment container Anda
                        .addToBackStack(null) // Menambahkan fragment ke back stack untuk bisa kembali
                        .commit();
            }
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
