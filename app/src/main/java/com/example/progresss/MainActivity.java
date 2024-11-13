package com.example.progresss;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Menambahkan MainFragment ke MainActivity
        if (savedInstanceState == null) { // Untuk memastikan Fragment hanya ditambahkan sekali
            MainFragment mainFragment = new MainFragment();
            FragmentManager fragmentManager = getSupportFragmentManager();
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            fragmentTransaction.add(R.id.fragment_container, mainFragment); // 'fragment_container' adalah ID dari FrameLayout dalam activity_main.xml
            fragmentTransaction.commit();
        }


    }
}
