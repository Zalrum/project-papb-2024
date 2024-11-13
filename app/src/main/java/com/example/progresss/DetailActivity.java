package com.example.progresss;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.appcompat.widget.Toolbar;

import androidx.appcompat.app.AppCompatActivity;
import com.bumptech.glide.Glide;

public class DetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true); // Menampilkan ikon back
            getSupportActionBar().setTitle("Detail Item");
        }

        TextView itemName = findViewById(R.id.detailItemName);
        TextView itemPrice = findViewById(R.id.detailItemPrice);
        ImageView itemImage = findViewById(R.id.ivItemImage);

        // Mengambil data dari Intent
        String name = getIntent().getStringExtra("itemName");
        String price = getIntent().getStringExtra("itemPrice");
        String imageUrl = getIntent().getStringExtra("itemImageUrl");

        // Menampilkan data di view
        itemName.setText(name);
        itemPrice.setText(price);
        Glide.with(this).load(imageUrl).into(itemImage);

    }

    @Override
    public boolean onSupportNavigateUp() {
        // Menutup activity ini dan kembali ke activity sebelumnya
        finish();
        return true;
    }
}
