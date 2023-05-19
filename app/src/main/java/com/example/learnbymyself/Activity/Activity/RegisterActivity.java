package com.example.learnbymyself.Activity.Activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.learnbymyself.Activity.Model.User;
import com.example.learnbymyself.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class RegisterActivity extends AppCompatActivity {
    EditText userNameTVAX, passTVAX, confirmTVAX;
    Button registerBTAX , loginBTAX;
    String userName, pass, confirmPass;
//    RG: Register, LG: Login
    FirebaseAuth mAuth;
    long size = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        AnhXa();
        GetSizeOfFireBase();
        ClickRegisterButton();
        ClickLoginButton();
    }

    public void AnhXa(){
        userNameTVAX = findViewById(R.id.ReGister_userName);
        passTVAX = findViewById(R.id.ReGister_pass);
        confirmTVAX = findViewById(R.id.ReGister_confirmPass);
        registerBTAX = findViewById(R.id.ReGister_registerButton);
        loginBTAX = findViewById(R.id.ReGister_loginButton);
    }
    public void GetData(){
        userName = userNameTVAX.getText().toString();
        pass = passTVAX.getText().toString();
        confirmPass = confirmTVAX.getText().toString();
    }
    public void GetSizeOfFireBase(){
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference databaseReference = database.getReference("User");
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                size = snapshot.getChildrenCount();
                System.out.println(size);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
    public void ClickRegisterButton(){
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
                                    InsertToFireBase();
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
    }
    public void ClickLoginButton(){
        loginBTAX.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ConvertFromRGToLG();
            }
        });
    }
    public void InsertToFireBase(){
        User user = new User(String.valueOf(size + 1), userName, pass, "", "", "","");
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference("User/"+size);
        myRef.setValue(user);
    }
    public void ConvertFromRGToLG(){
        Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);
        startActivity(intent);
    }
}