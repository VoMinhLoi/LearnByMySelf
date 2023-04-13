package com.example.learnbymyself.Activity.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ListView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.learnbymyself.Activity.Adapter.CartAdapter;
import com.example.learnbymyself.Activity.Model.Clothe;
import com.example.learnbymyself.R;

import java.util.ArrayList;
import java.util.List;

public class CartActivity extends AppCompatActivity {
    List<Clothe> clotheList;
    CartAdapter cartAdapter;
    ListView listView;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);
        AnhXa();
        GetData();
        listView.setAdapter(cartAdapter);
    }
    public void AnhXa(){
        listView = findViewById(R.id.activity_cart_list);

    }
    public void GetData(){
        clotheList = new ArrayList<>();
        Intent intent = getIntent();
        Clothe clothe = (Clothe) intent.getSerializableExtra("DetailItem");
        clotheList.add(clothe);
        cartAdapter = new CartAdapter(clotheList, CartActivity.this);
    }
}
