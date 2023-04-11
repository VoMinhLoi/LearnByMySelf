package com.example.learnbymyself.Activity.Activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.learnbymyself.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class RegisterActivity extends AppCompatActivity {
    EditText userNameTVAX, passTVAX, confirmTVAX;
    Button registerBTAX , loginBTAX;
    String userName, pass, confirmPass;
//    RG: Register, LG: Login
    FirebaseAuth mAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        AnhXa();
        registerBTAX.setOnClickListener(new View.OnClickListener() {
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
                    else
                        if(confirmPass.equals("")){
                            confirmTVAX.setError("Trống");
                            confirmTVAX.requestFocus();

                        }
                        else{
                            System.out.println(pass + " /" +confirmPass);
                            if(!pass.equals(confirmPass)){
                                confirmTVAX.setError("Mật khẩu không trùng khớp");
                                confirmTVAX.requestFocus();
                            }
                            else{
                                mAuth = FirebaseAuth.getInstance();
                                mAuth.createUserWithEmailAndPassword(userName, pass).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                                    @Override
                                    public void onComplete(@NonNull Task<AuthResult> task) {
                                        if(task.isSuccessful()){
                                            Toast.makeText(RegisterActivity.this, "Tạo tài khoản thành công", Toast.LENGTH_SHORT).show();
                                            ConvertFromRGToLG();
                                        }
                                        else{
                                            Toast.makeText(RegisterActivity.this, task.getException().toString(), Toast.LENGTH_SHORT).show();
                                        }
                                    }
                                });
                            }
                        }
            }
        });
        loginBTAX.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ConvertFromRGToLG();
            }
        });
    }

    public void AnhXa(){
        userNameTVAX = findViewById(R.id.RG_userName);
        passTVAX = findViewById(R.id.RG_pass);
        confirmTVAX = findViewById(R.id.RG_confirmPass);
        registerBTAX = findViewById(R.id.RG_button);
        loginBTAX = findViewById(R.id.LG_button_RG);
    }
    public void GetData(){
        userName = userNameTVAX.getText().toString();
        pass = passTVAX.getText().toString();
        confirmPass = confirmTVAX.getText().toString();
    }
    public void ConvertFromRGToLG(){
        Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);
        startActivity(intent);
    }
}