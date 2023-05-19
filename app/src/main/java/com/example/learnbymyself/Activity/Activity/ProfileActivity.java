package com.example.learnbymyself.Activity.Activity;

import android.os.Bundle;
import android.os.PersistableBundle;
import android.widget.ImageView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.example.learnbymyself.R;

public class ProfileActivity extends AppCompatActivity {
    ImageView avatarProfile;
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState, @Nullable PersistableBundle persistentState) {
        super.onCreate(savedInstanceState, persistentState);
        setContentView(R.layout.activity_profile);
        AnhXa();
//        Glide.with(this)
//                .load(R.drawable.img)
//                .circleCrop()
//                .into(avatarProfile);
    }
    public void AnhXa(){
        avatarProfile = findViewById(R.id.Profile_Avatar);
    }
}
