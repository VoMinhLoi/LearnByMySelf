package com.example.learnbymyself.Activity.Adapter;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.learnbymyself.Activity.Model.Clothe;
import com.example.learnbymyself.Activity.Model.Image;
import com.example.learnbymyself.R;

import java.util.List;

public class CartAdapter extends BaseAdapter {
    List<Clothe> clotheList;
    Activity activity;

    public CartAdapter(List<Clothe> clotheList, Activity activity) {
        this.clotheList = clotheList;
        this.activity = activity;
    }

    @Override
    public int getCount() {
        return clotheList.size();
    }

    @Override
    public Object getItem(int i) {
        return clotheList.get(i);
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        LayoutInflater layoutInflater = activity.getLayoutInflater();
        view = layoutInflater.inflate(R.layout.customor_item_cart, null);
        ImageView imageView = view.findViewById(R.id.customor_item_cart_image);
        TextView nameTV = view.findViewById(R.id.customor_item_cart_nameTV);
        TextView descriptionTV = view.findViewById(R.id.customor_item_cart_descriptionTV);
        TextView quantityTV = view.findViewById(R.id.customor_item_cart_quantityTV);
        TextView priceTV = view.findViewById(R.id.customor_item_cart_priceTV);
        Button increase = view.findViewById(R.id.customor_item_cart_increaseBT);
        Button descrease = view.findViewById(R.id.customor_item_cart_descreaseBT);
        Clothe clothe = clotheList.get(i);
        imageView.setImageResource(clothe.getImage());
        nameTV.setText(clothe.getClothesName());
        descriptionTV.setText(clothe.getDescription());
        quantityTV.setText(clothe.getQuantity());
        priceTV.setText(clothe.getPrice());
        increase.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int quantity = Integer.parseInt(quantityTV.getText().toString());
                quantity++;
                quantityTV.setText(String.valueOf(quantity));
            }
        });
        descrease.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int quantity = Integer.parseInt(quantityTV.getText().toString());
                if(quantity>1){
                    quantity--;
                    quantityTV.setText(String.valueOf(quantity));
                }
                else
                    Toast.makeText(activity, "Ít nhất là 1 sản phẩm", Toast.LENGTH_SHORT).show();
            }
        });
        return view;
    }
}
