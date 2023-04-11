package com.example.learnbymyself.Activity.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.learnbymyself.Activity.Model.Clothe;
import com.example.learnbymyself.Activity.Model.Image;
import com.example.learnbymyself.R;

public class DetailActivity extends AppCompatActivity {
    Clothe clothe;
    ImageView imageView;
    TextView nameTV, descriptionTV, priceTV, quantityTV;
    Button descreaseBT, increaseBT, insertToCartBT;
    public void AnhXa(){
        imageView = findViewById(R.id.Detail_image);
        nameTV = findViewById(R.id.Detail_nameProductTV);
        descriptionTV = findViewById(R.id.Detail_descriptionTV);
        priceTV = findViewById(R.id.Detail_priceTV);
        quantityTV = findViewById(R.id.Detail_quantityTV);
        increaseBT = findViewById(R.id.Detail_increaseBT);
        descreaseBT = findViewById(R.id.Detail_descreaseBT);
    }
    public void GetData(){
        Intent intent = getIntent();
        clothe = (Clothe) intent.getSerializableExtra("Item");
        nameTV.setText(clothe.getClothesName());
        imageView.setImageResource(clothe.getImage());
        descriptionTV.setText(clothe.getDescription());
        priceTV.setText(clothe.getPrice());
        quantityTV.setText(clothe.getQuantity());
    }
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        AnhXa();
        GetData();
        Increase();
        Descrease();
    }
    public void Increase(){
        increaseBT.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int quantity = Integer.parseInt(quantityTV.getText().toString());
                quantity++;
                quantityTV.setText("" + quantity);
            }
        });
    }
    public void Descrease(){
        descreaseBT.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int quantity = Integer.parseInt(quantityTV.getText().toString());
                if(quantity == 1){
                    Toast.makeText(DetailActivity.this, "Mua ít nhất 1 sản phẩm", Toast.LENGTH_SHORT).show();
                }
                else{
                    quantity--;
                    quantityTV.setText("" + quantity);
                }
            }
        });
    }
}
