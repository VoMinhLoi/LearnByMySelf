package com.example.learnbymyself.Activity.Activity;

import static com.example.learnbymyself.R.layout.customor_navigation_header_no_user;
import static com.example.learnbymyself.R.layout.customor_navigation_header_have_user;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.viewpager2.widget.ViewPager2;

import com.example.learnbymyself.Activity.Adapter.ClothesAdapter;
import com.example.learnbymyself.Activity.Adapter.ImageAdapter;
import com.example.learnbymyself.Activity.Model.Clothe;
import com.example.learnbymyself.Activity.Model.Image;
import com.example.learnbymyself.Activity.Model.User;
import com.example.learnbymyself.R;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

import me.relex.circleindicator.CircleIndicator3;

public class HomeActivity extends AppCompatActivity {
    private GridView gridView;
    private DrawerLayout drawerLayout;
    private ImageButton showNav;
    private NavigationView navigationView;
    private List<Image> imageList;
    private CircleIndicator3 circleIndicator3;
    private ViewPager2 viewPager2;
    private ImageAdapter imageAdapter;
    private ImageButton cartBT;
    View headerNoUserView, headerHaveUserView;
    TextView quantityCartTV;
    TextView nameNavigationTV;
    Button navigation_loginBT;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        AnhXa();
//        GetDataFromLogin();
        GetDataFromLoginFireBase();
        GetQuantityFromCartOfFirebase();
        GetImageSource();
        GetClotheFromFirebase();
        showNavigation();
        SlidingImage();
        headerNoUserView = navigationView.inflateHeaderView(customor_navigation_header_no_user);
//        GetDataFromLoginFireBase();
//        navigationView.inflateHeaderView(customor_navigation_header_no_user);
        navigationView.getHeaderView(0).findViewById(R.id.customor_navigation_header_no_user_loginBT).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(HomeActivity.this, LoginActivity.class);
                startActivity(intent);
            }
        });
        navigationView.getHeaderView(0).findViewById(R.id.customor_navigation_header_no_user_registerButton).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(HomeActivity.this, RegisterActivity.class);
                startActivity(intent);
            }
        });
        cartBT.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(HomeActivity.this, CartActivity.class);
                startActivity(intent);
            }
        });
    }
    public void AnhXa(){
        gridView = findViewById(R.id.Home_newClothesGV);
        drawerLayout = findViewById(R.id.drawerLayout);
        showNav = findViewById(R.id.Home_showingNavigationBT);
        viewPager2 = findViewById(R.id.Home_imagesViewPager);
        circleIndicator3 = findViewById(R.id.Home_circleIndicator);
        cartBT = findViewById(R.id.Home_showingCart);
        navigationView = findViewById(R.id.Home_menuNavigation);
        quantityCartTV = findViewById(R.id.Home_Cart_Quantity);
//        navigation_loginBT = navigationView.getHeaderView(0).findViewById(R.id.customor_navigation_header_no_user_loginBT);
//        nameNavigationTV = navigationView.getHeaderView(0).findViewById(R.id.customor_navigation_header_have_user_nameTV);
    }
    public void GetDataFromLogin(){
        Intent intent = getIntent();
        User user = (User) intent.getSerializableExtra("User");
        if(user != null){
            System.out.println("Nhận dữ liệu thành công");
            System.out.println(user.getUserName());
            System.out.println(user.getPass());
            System.out.println(user.getClass());
        }
        else
            System.out.println("Nhận dữ liệu thất bại");
    }
    public void GetQuantityFromCartOfFirebase(){
        FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
        DatabaseReference myRef = firebaseDatabase.getReference("Cart");
        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if(snapshot.getChildrenCount() > 0){
                    String string = String.valueOf(snapshot.getChildrenCount());
                    quantityCartTV.setText("(" + string + ")");
                }
                else{
                    quantityCartTV.setVisibility(View.GONE);
                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
    public void GetDataFromLoginFireBase(){
        FirebaseAuth mAuth = FirebaseAuth.getInstance();
        System.out.println(mAuth.getCurrentUser().getEmail());
        String email = mAuth.getCurrentUser().getEmail();
        Menu menu = navigationView.getMenu();
        menu.clear();
        if(email == ""){
            navigationView.inflateMenu(R.drawable.customor_navigation_menu_no_user);
        }
        else {
            navigationView.removeHeaderView(headerNoUserView);
            headerHaveUserView = navigationView.inflateHeaderView(customor_navigation_header_have_user);
            navigationView.inflateMenu(R.drawable.customor_navigation_menu_have_user);
//            nameNavigationTV.setText(email);
            navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
                @Override
                public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                    if(item.toString().equals("Đăng xuất")){
                        navigationView.removeHeaderView(headerHaveUserView);
                        navigationView.inflateHeaderView(customor_navigation_header_no_user);
                        Menu menu = navigationView.getMenu();
                        menu.clear();
                        navigationView.inflateMenu(R.drawable.customor_navigation_menu_no_user);
                        menu.removeItem(item.getItemId());
                    }
                    return false;
                }
            });
        }
    }
    public void GetImageSource(){

        imageList = new ArrayList<>();
        imageList.add(new Image(R.drawable.slider1));
        imageList.add(new Image(R.drawable.slider2));
        imageList.add(new Image(R.drawable.slider3));
        imageList.add(new Image(R.drawable.slider4));

        imageList.add(new Image(R.drawable.slider1));
        imageList.add(new Image(R.drawable.slider2));
        imageList.add(new Image(R.drawable.slider3));
        imageList.add(new Image(R.drawable.slider4));
    }
    public List<Clothe> GetClothe(){
        List<Clothe> clotheList = new ArrayList<>();
        clotheList.add(new Clothe("2", "Ao2", "200.000", "Áo quần được sản xuất dưới công nghệ hiện đại, chất liệu siêu bền bỉ, chống thấm nước, không bay màu, vải thoáng mát, dễ chịu, dễ dàng vệ sinh.", "2", "https://firebasestorage.googleapis.com/v0/b/learnbymyself-3e10d.appspot.com/o/img%2FquanOngFreeStyle.jpg?alt=media&token=20cc6d6d-71cf-44c4-aa7b-7fae62686dc3"));
        clotheList.add(new Clothe("1", "Ao1", "100.000", "Áo quần được sản xuất dưới công nghệ hiện đại, chất liệu siêu bền bỉ, chống thấm nước, không bay màu, vải thoáng mát, dễ chịu, dễ dàng vệ sinh.", "1", "https://firebasestorage.googleapis.com/v0/b/learnbymyself-3e10d.appspot.com/o/img%2FaoBaLo.jpg?alt=media&token=e7025b8b-9623-434c-9923-e3018d731672"));
        clotheList.add(new Clothe("3", "Ao3", "300.000", "Áo quần được sản xuất dưới công nghệ hiện đại, chất liệu siêu bền bỉ, chống thấm nước, không bay màu, vải thoáng mát, dễ chịu, dễ dàng vệ sinh.", "3", "https://firebasestorage.googleapis.com/v0/b/learnbymyself-3e10d.appspot.com/o/img%2FaoBaLo.jpg?alt=media&token=e7025b8b-9623-434c-9923-e3018d731672"));
        clotheList.add(new Clothe("4", "Ao4", "400.000", "Áo quần được sản xuất dưới công nghệ hiện đại, chất liệu siêu bền bỉ, chống thấm nước, không bay màu, vải thoáng mát, dễ chịu, dễ dàng vệ sinh.", "4", "https://firebasestorage.googleapis.com/v0/b/learnbymyself-3e10d.appspot.com/o/img%2FquanOngFreeStyle.jpg?alt=media&token=20cc6d6d-71cf-44c4-aa7b-7fae62686dc3"));
        clotheList.add(new Clothe("5", "Ao5", "500.000", "Áo quần được sản xuất dưới công nghệ hiện đại, chất liệu siêu bền bỉ, chống thấm nước, không bay màu, vải thoáng mát, dễ chịu, dễ dàng vệ sinh.", "4", "https://firebasestorage.googleapis.com/v0/b/learnbymyself-3e10d.appspot.com/o/img%2FquanOngFreeStyle.jpg?alt=media&token=20cc6d6d-71cf-44c4-aa7b-7fae62686dc3"));
        return clotheList;
    }
    private void setClotheAdapter(List<Clothe> listClothe ){
        ClothesAdapter aoQuanAdapter = new ClothesAdapter(HomeActivity.this, listClothe);
        gridView.setAdapter(aoQuanAdapter);
        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Clothe clothe = listClothe.get(i);
                Intent intent = new Intent(HomeActivity.this, DetailActivity.class);
                intent.putExtra("Item", clothe);
                startActivity(intent);
            }
        });
    }
    public void GetClotheFromFirebase(){
        List<Clothe> clotheList = new ArrayList<>();
        FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
        DatabaseReference myRef = firebaseDatabase.getReference("NewProduct");
        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                clotheList.clear();
                for(DataSnapshot data : snapshot.getChildren()){
                    Clothe temporary = data.getValue(Clothe.class);
                    clotheList.add(temporary);
//                    System.out.println(temporary.getClothesName());
//                    System.out.println(clotheList);
                }
                setClotheAdapter(clotheList);
//                System.out.println("Ket thuc");
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(HomeActivity.this,"Không tải được dữ liệu từ firebase"
                        +error.toString(),Toast.LENGTH_LONG).show();
                Log.d("MYTAG","onCancelled"+ error.toString());
            }
        });
    }
    public void showNavigation(){
        showNav.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                drawerLayout.openDrawer(GravityCompat.START);
            }
        });
    }
    public Handler handler = new Handler(Looper.getMainLooper());
    public Runnable runnable = new Runnable() {
        @Override
        public void run() {
            int currentPosition = viewPager2.getCurrentItem();
            if(currentPosition == imageList.size() - 1)
                viewPager2.setCurrentItem(0);
            else
                viewPager2.setCurrentItem(currentPosition + 1);
        }
    };
    public void SlidingImage(){
        imageAdapter = new ImageAdapter(imageList);
        viewPager2.setAdapter(imageAdapter);
        circleIndicator3.setViewPager(viewPager2);
        circleIndicator3.getChildAt(0).setBackgroundResource(R.drawable.circleindicator);
        circleIndicator3.getChildAt(1).setBackgroundResource(R.drawable.circleindicator);
        circleIndicator3.getChildAt(2).setBackgroundResource(R.drawable.circleindicator);
        circleIndicator3.getChildAt(3).setBackgroundResource(R.drawable.circleindicator);
        circleIndicator3.getChildAt(4).setBackgroundResource(R.drawable.circleindicator);
        circleIndicator3.getChildAt(5).setBackgroundResource(R.drawable.circleindicator);
        circleIndicator3.getChildAt(6).setBackgroundResource(R.drawable.circleindicator);
        circleIndicator3.getChildAt(7).setBackgroundResource(R.drawable.circleindicator);
        viewPager2.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageSelected(int position) {
                super.onPageSelected(position);
                // Remove runnable tránh runnable lặp chồng gây lag
                handler.removeCallbacks(runnable);
                handler.postDelayed(runnable, 5000);
            }
        });
    }
}