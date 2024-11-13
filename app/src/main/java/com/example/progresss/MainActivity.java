package com.example.progresss;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;


// MainActivity.java
public class MainActivity extends AppCompatActivity {
    private EditText searchInput;
    private RecyclerView recyclerView;
    private TextView noResultText;
    private AuctionItemAdapter auctionItemAdapter;
    private List<AuctionItem> auctionItemList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        searchInput = findViewById(R.id.search_input);
        Button searchButton = findViewById(R.id.search_button);
        recyclerView = findViewById(R.id.recycler_view);
        noResultText = findViewById(R.id.no_result_text);
        this.auctionItemAdapter = new AuctionItemAdapter(this, auctionItemList);
        recyclerView.setAdapter(this.auctionItemAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        searchButton.setOnClickListener(v -> searchAuctionItems());

    }
    //Implementasi Client Side
    private void searchAuctionItems() {
        String query = searchInput.getText().toString();
        String url = "http://192.168.18.190/lelangApp/item_lelang.php?keyword=" + query;


        // Menggunakan Volley untuk melakukan request
        RequestQueue queue = Volley.newRequestQueue(this);
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                response -> {
                    Log.d("Response", response);
                    auctionItemList.clear();
                    // Menggunakan GSON untuk konversi JSON ke objek Java
                    Gson gson = new Gson();
                    Type auctionItemListType = new TypeToken<List<AuctionItem>>() {}.getType();
                    auctionItemList.addAll(gson.fromJson(response, auctionItemListType));
                    if (auctionItemList.isEmpty()) {
                        noResultText.setVisibility(View.VISIBLE);
                    } else {
                        noResultText.setVisibility(View.GONE);
                    }
                    auctionItemAdapter.notifyDataSetChanged();
                },
                error -> {
                    // Tangani error di sini
                    Log.e("Error", error.toString());
                    noResultText.setVisibility(View.VISIBLE);
                });
        queue.add(stringRequest);
    }
}
