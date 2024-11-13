package com.example.progresss;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
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

public class MainFragment extends Fragment {
    private EditText searchInput;
    private RecyclerView recyclerView;
    private TextView noResultText;
    private AuctionItemAdapter auctionItemAdapter;
    private List<AuctionItem> auctionItemList = new ArrayList<>();

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_main, container, false);

        searchInput = view.findViewById(R.id.search_input);
        Button searchButton = view.findViewById(R.id.search_button);
        recyclerView = view.findViewById(R.id.recycler_view);
        noResultText = view.findViewById(R.id.no_result_text);

        auctionItemAdapter = new AuctionItemAdapter(getContext(), auctionItemList);
        recyclerView.setAdapter(auctionItemAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        searchButton.setOnClickListener(v -> searchAuctionItems());

        return view;
    }

    private void searchAuctionItems() {
        String query = searchInput.getText().toString();
        String url = "http://192.168.18.190/lelangApp/item_lelang.php?keyword=" + query;

        // Menggunakan Volley untuk melakukan request
        RequestQueue queue = Volley.newRequestQueue(getContext());
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                response -> {
                    Log.d("Response", response);
                    auctionItemList.clear();
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
                    Log.e("Error", error.toString());
                    noResultText.setVisibility(View.VISIBLE);
                });
        queue.add(stringRequest);
    }
}
