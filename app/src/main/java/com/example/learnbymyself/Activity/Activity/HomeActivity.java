package com.example.learnbymyself.Activity.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ImageButton;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.viewpager2.widget.ViewPager2;

import com.example.learnbymyself.Activity.Adapter.ClothesAdapter;
import com.example.learnbymyself.Activity.Adapter.ImageAdapter;
import com.example.learnbymyself.Activity.Model.Clothe;
import com.example.learnbymyself.Activity.Model.Image;
import com.example.learnbymyself.R;
import com.google.android.material.navigation.NavigationView;

import java.util.ArrayList;
import java.util.List;

import me.relex.circleindicator.CircleIndicator3;

public class HomeActivity extends AppCompatActivity {
    private List<Clothe> clothesList;
    private ClothesAdapter aoQuanAdapter;
    private GridView gridView;
    private DrawerLayout drawerLayout;
    private ImageButton showNav;
    private NavigationView navigationView;
    private List<Image> imageList;
    private CircleIndicator3 circleIndicator3;
    private ViewPager2 viewPager2;
    private ImageAdapter imageAdapter;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        AnhXa();
        GetData();
        aoQuanAdapter = new ClothesAdapter(HomeActivity.this, clothesList);
        showNavigation();
        SlidingImage();
        gridView.setAdapter(aoQuanAdapter);
        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Clothe clothes = clothesList.get(i);
                Intent intent = new Intent(HomeActivity.this, DetailActivity.class);
                intent.putCharSequenceArrayListExtra("Item", clothes);
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
    }
    public void GetData(){
        clothesList = new ArrayList<>();
        clothesList.add(new Clothe("1", "Ao1", "199k", "Siêu đẹp",  R.drawable.aos));
        clothesList.add(new Clothe("2", "Ao2", "299k", "Siêu đẹp",  R.drawable.aos));
        clothesList.add(new Clothe("3", "Ao3", "399k", "Siêu đẹp",  R.drawable.aos));
        clothesList.add(new Clothe("4", "Ao4", "499k", "Siêu đẹp",  R.drawable.aos));
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