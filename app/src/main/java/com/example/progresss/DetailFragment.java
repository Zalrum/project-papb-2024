package com.example.progresss;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;

import com.bumptech.glide.Glide;

public class DetailFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        // Inflate layout untuk Fragment
        View view = inflater.inflate(R.layout.fragment_detail, container, false);

        // Menghubungkan komponen UI dengan view
        Toolbar toolbar = view.findViewById(R.id.toolbar);
        toolbar.setTitle("Detail Item");

        // Menambahkan tombol back pada toolbar
        toolbar.setNavigationIcon(R.drawable.baseline_arrow_back_24); // Ganti dengan ikon back yang Anda inginkan
        toolbar.setNavigationOnClickListener(v -> {
            // Menangani tombol back, kembali ke MainFragment
            if (getActivity() != null) {
                getActivity().getSupportFragmentManager().popBackStack(); // Kembali ke fragment sebelumnya
            }
        });

        TextView itemName = view.findViewById(R.id.detailItemName);
        TextView itemPrice = view.findViewById(R.id.detailItemPrice);
        ImageView itemImage = view.findViewById(R.id.ivItemImage);

        // Mengambil data dari arguments
        if (getArguments() != null) {
            String name = getArguments().getString("itemName");
            String price = getArguments().getString("itemPrice");
            String imageUrl = getArguments().getString("itemImageUrl");

            // Menampilkan data di view
            itemName.setText(name);
            itemPrice.setText(price);
            Glide.with(this).load(imageUrl).into(itemImage);
        }

        return view;
    }

    // Metode ini dapat digunakan untuk membuat instance DetailFragment dan mengirimkan data
    public static DetailFragment newInstance(String name, String price, String imageUrl) {
        DetailFragment fragment = new DetailFragment();
        Bundle args = new Bundle();
        args.putString("itemName", name);
        args.putString("itemPrice", price);
        args.putString("itemImageUrl", imageUrl);
        fragment.setArguments(args);
        return fragment;
    }
}
