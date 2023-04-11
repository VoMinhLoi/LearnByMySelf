package com.example.learnbymyself.Activity.Activity;

import static android.content.ContentValues.TAG;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

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
    @Override

    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        AnhXa();
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
                                startActivity(intent);
                            }
                            else{
                                Log.w(TAG,"", task.getException());
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
    }
    public void GetData(){
        userName = userNameTVAX.getText().toString();
        pass = passTVAX.getText().toString();
    }
    public void ConvertFromLGToRG(){
        Intent intent = new Intent(LoginActivity.this, RegisterActivity.class);
        startActivity(intent);
    }
}
