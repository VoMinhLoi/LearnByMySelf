package com.example.learnbymyself.Activity.Activity;

import static android.content.ContentValues.TAG;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.learnbymyself.Activity.Adapter.CartAdapter;
import com.example.learnbymyself.Activity.Model.Clothe;
import com.example.learnbymyself.Activity.Model.User;
import com.example.learnbymyself.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class LoginActivity extends AppCompatActivity {
    Button loginBTAX, registerBT;
    EditText userNameTVAX, passTVAX;
    FirebaseAuth mAuth;
    String userName, pass;
    SharedPreferences sharedPreferences;
    CheckBox saveCB;
    @Override

    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        AnhXa();
        InitialPass();
        loginBTAX.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                GetData();
                if(userName.isEmpty()){
                    userNameTVAX.setError("Trống");
                    userNameTVAX.requestFocus();
                }
                else
                if(pass.isEmpty()){
                    passTVAX.setError("Trống");
                    passTVAX.requestFocus();
                }
                else{
                    mAuth = FirebaseAuth.getInstance();
                    mAuth.signInWithEmailAndPassword(userName, pass).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if(task.isSuccessful()){

                                Intent intent = new Intent(LoginActivity.this, HomeActivity.class);
                                User user = new User(userName, pass);
                                intent.putExtra("User", user);
                                RememberPass();
                                startActivity(intent);
                            }
                            else{
                                Log.w(TAG,"", task.getException());
                                Toast.makeText(LoginActivity.this, task.getException().toString(), Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
                }
            }
        });
        registerBT.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ConvertFromLGToRG();
            }
        });
    }
    public void AnhXa(){
        userNameTVAX = findViewById(R.id.LoGin_userName);
        passTVAX = findViewById(R.id.LoGin_pass);
        loginBTAX = findViewById(R.id.LoGin_button);
        registerBT = findViewById(R.id.LoGin_registerButton);
        saveCB = findViewById(R.id.LoGin_CB_savePass);
    }
    public void GetData(){
        userName = userNameTVAX.getText().toString();
        pass = passTVAX.getText().toString();
    }
    public void ConvertFromLGToRG(){
        Intent intent = new Intent(LoginActivity.this, RegisterActivity.class);
        startActivity(intent);
    }
    public void RememberPass(){
        SharedPreferences.Editor editor = sharedPreferences.edit();
        if(saveCB.isChecked()){
            editor.putString("userName", userName);
            editor.putString("pass", pass);
            editor.putBoolean("save", true);
            editor.commit();
        }
        else {
            editor.remove("userName");
            editor.remove("pass");
            editor.remove("save");
            editor.commit();

        }
    }
    public void InitialPass(){
        sharedPreferences = getSharedPreferences("dataLogin", MODE_PRIVATE);
        userNameTVAX.setText(sharedPreferences.getString("userName",""));
        passTVAX.setText(sharedPreferences.getString("pass",""));
        saveCB.setChecked(sharedPreferences.getBoolean("save",false));
    }
}
