package com.example.learnbymyself.Activity.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.learnbymyself.Activity.Model.Clothe;
import com.example.learnbymyself.Activity.Model.Image;
import com.example.learnbymyself.R;

public class DetailActivity extends AppCompatActivity {
    Clothe clothe;
    ImageView imageView;
    TextView nameTV, descriptionTV, priceTV, quantityTV;
    Button insertToCartBT;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        GetData();
    }
    public void GetData(){
        Intent intent = getIntent();
        clothe = (Clothe) intent.getCharSequenceArrayListExtra("Item");

    }
}
