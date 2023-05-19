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

//import com.bumptech.glide.Glide;
import com.bumptech.glide.Glide;
import com.example.learnbymyself.Activity.Model.Clothe;
import com.example.learnbymyself.R;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.List;

public class CartAdapter extends BaseAdapter {
    List<Clothe> clotheList;
    Activity activity;
    Integer totalFloat = 0;

    public Integer getTotalFloat() {
        return totalFloat;
    }public CartAdapter(List<Clothe> clotheList) {
        this.clotheList = clotheList;
    }
    public void setActivity(Activity activity){
        this.activity = activity;
    }
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
    public class ViewHolder{
        ImageView imageView;
        TextView nameTV,descriptionTV, priceTV, quantityTV;
        Button increaseBT, descreaseBT;
    }
    @Override

    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewHolder viewHolder;
        if (view == null) {
            viewHolder = new ViewHolder();
            LayoutInflater layoutInflater = activity.getLayoutInflater();
            view = layoutInflater.inflate(R.layout.customor_item_cart, null);
            viewHolder.imageView = view.findViewById(R.id.customor_item_cart_image);
            viewHolder.nameTV = view.findViewById(R.id.customor_item_cart_nameTV);
            viewHolder.descriptionTV = view.findViewById(R.id.customor_item_cart_descriptionTV);
            viewHolder.quantityTV = view.findViewById(R.id.customor_item_cart_quantityTV);
            viewHolder.priceTV = view.findViewById(R.id.customor_item_cart_priceTV);
            viewHolder.increaseBT = view.findViewById(R.id.customor_item_cart_increaseBT);
            viewHolder.descreaseBT = view.findViewById(R.id.customor_item_cart_descreaseBT);
            view.setTag(viewHolder);
        }
        else{
            viewHolder = (ViewHolder) view.getTag();
        }


        Clothe clothe = clotheList.get(i);
        Glide.with(viewHolder.imageView.getContext()).load(clothe.getImage()).into(viewHolder.imageView);
        viewHolder.nameTV.setText(clothe.getClothesName());
        viewHolder.descriptionTV.setText(clothe.getDescription());
        viewHolder.quantityTV.setText(clothe.getQuantity());
        viewHolder.priceTV.setText(clothe.getPrice());
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        System.out.println(i);
        String path = "Cart/" + i + "/quantity";
        DatabaseReference myRef = database.getReference(path);
        viewHolder.increaseBT.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                int quantity = Integer.parseInt(viewHolder.quantityTV.getText().toString());
                quantity++;
                viewHolder.quantityTV.setText(String.valueOf(quantity));

                myRef.setValue(String.valueOf(quantity));
                System.out.println(quantity);
            }
        });
        viewHolder.descreaseBT.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int quantity = Integer.parseInt(viewHolder.quantityTV.getText().toString());
                if(quantity>1){
                    quantity--;
                    viewHolder.quantityTV.setText(String.valueOf(quantity));
                    myRef.setValue(String.valueOf(quantity));
                }
                else
                    Toast.makeText(activity, "Ít nhất là 1 sản phẩm", Toast.LENGTH_SHORT).show();
            }
        });
        return view;
    }
}
