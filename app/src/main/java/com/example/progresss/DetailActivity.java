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
        setContentView(R.layout.activity_detail); // Layout untuk Activity

        if (savedInstanceState == null) {
            // Menambahkan DetailFragment ke dalam Activity
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.fragment_container, new DetailFragment())
                    .commit();
        }
    }
}