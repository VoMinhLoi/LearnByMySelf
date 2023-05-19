package com.example.learnbymyself.Activity.Activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.learnbymyself.Activity.Adapter.CartAdapter;
import com.example.learnbymyself.Activity.Model.Clothe;
import com.example.learnbymyself.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class CartActivity extends AppCompatActivity {
    List<Clothe> clotheList;
    CartAdapter cartAdapter;
    ListView listView;
    TextView totalPriceTV;
    Button payBT;
    FirebaseDatabase database;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);
        AnhXa();
//        InitialItem();
        GetDataFromFireBase();
    }
    public void AnhXa(){
        listView = findViewById(R.id.activity_cart_list);
        clotheList = new ArrayList<>();
        totalPriceTV = findViewById(R.id.activity_cart_totalPriceTV);
        payBT = findViewById(R.id.activity_cart_payBT);
    }
    public void setClotheList(List<Clothe> clotheList){
        cartAdapter = new CartAdapter(clotheList);
        cartAdapter.setActivity(CartActivity.this);
        listView.setAdapter(cartAdapter);
    }
    public void GetDataFromFireBase(){
        FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
        DatabaseReference databaseReference = firebaseDatabase.getReference("Cart");
        clotheList = new ArrayList<>();

        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                clotheList.clear();
                long totalPrice = 0;
                for(DataSnapshot data : snapshot.getChildren()){
                    Clothe temporary = data.getValue(Clothe.class);
                    clotheList.add(temporary);
                    long price = Long.parseLong(temporary.getPrice());
                    long quantity = Long.parseLong(temporary.getQuantity());
                    totalPrice += price * quantity;
                }
//                cartAdapter = new CartAdapter(clotheList);
//                setClotheList(clotheList);
                cartAdapter = new CartAdapter(clotheList);
                cartAdapter.setActivity(CartActivity.this);
                listView.setAdapter(cartAdapter);
                totalPriceTV.setText(String.valueOf(totalPrice));
                System.out.println(clotheList);
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
    public void GetData(){

        Intent intent = getIntent();
//        Clothe clothe = (Clothe) intent.getSerializableExtra("DetailItem");
//        clotheList.add(clothe);
//        RememberItemCart(clothe);

        clotheList.add(new Clothe("1", "Ao1", "100000", "Áo quần được sản xuất dưới công nghệ hiện đại, chất liệu siêu bền bỉ, chống thấm nước, không bay màu, vải thoáng mát, dễ chịu, dễ dàng vệ sinh.", "1",  "https://firebasestorage.googleapis.com/v0/b/db-qlqa.appspot.com/o/img%2Fteetrang_Para.png?alt=media&token=fb19cb29-7969-4a30-85c3-75407d0118df"));
        clotheList.add(new Clothe("2", "Ao2", "200000", "Áo quần được sản xuất dưới công nghệ hiện đại, chất liệu siêu bền bỉ, chống thấm nước, không bay màu, vải thoáng mát, dễ chịu, dễ dàng vệ sinh.", "1",  "https://firebasestorage.googleapis.com/v0/b/db-qlqa.appspot.com/o/img%2Fteetrang_Para.png?alt=media&token=fb19cb29-7969-4a30-85c3-75407d0118df"));
        clotheList.add(new Clothe("3", "Ao3", "300000", "Áo quần được sản xuất dưới công nghệ hiện đại, chất liệu siêu bền bỉ, chống thấm nước, không bay màu, vải thoáng mát, dễ chịu, dễ dàng vệ sinh.", "1",  "https://firebasestorage.googleapis.com/v0/b/db-qlqa.appspot.com/o/img%2Fteetrang_Para.png?alt=media&token=fb19cb29-7969-4a30-85c3-75407d0118df"));
        clotheList.add(new Clothe("4", "Ao4", "400000", "Áo quần được sản xuất dưới công nghệ hiện đại, chất liệu siêu bền bỉ, chống thấm nước, không bay màu, vải thoáng mát, dễ chịu, dễ dàng vệ sinh.", "1",  "https://firebasestorage.googleapis.com/v0/b/db-qlqa.appspot.com/o/img%2Fteetrang_Para.png?alt=media&token=fb19cb29-7969-4a30-85c3-75407d0118df"));
        clotheList.add(new Clothe("5", "Ao5", "400000", "Áo quần được sản xuất dưới công nghệ hiện đại, chất liệu siêu bền bỉ, chống thấm nước, không bay màu, vải thoáng mát, dễ chịu, dễ dàng vệ sinh.", "1",  "https://firebasestorage.googleapis.com/v0/b/db-qlqa.appspot.com/o/img%2Fteetrang_Para.png?alt=media&token=fb19cb29-7969-4a30-85c3-75407d0118df"));
        clotheList.add(new Clothe("5", "Ao5", "400000", "Áo quần được sản xuất dưới công nghệ hiện đại, chất liệu siêu bền bỉ, chống thấm nước, không bay màu, vải thoáng mát, dễ chịu, dễ dàng vệ sinh.", "1",  "https://firebasestorage.googleapis.com/v0/b/db-qlqa.appspot.com/o/img%2Fteetrang_Para.png?alt=media&token=fb19cb29-7969-4a30-85c3-75407d0118df"));
        cartAdapter = new CartAdapter(clotheList, CartActivity.this);
    }



}
