package com.example.phongthuy;

import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.time.LocalDate;
import java.util.Date;


public class RegisterActivity extends AppCompatActivity {
    EditText userName,password,name;
    Button btnSignUp;
    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference myRef = database.getReference("User");
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        name = findViewById(R.id.txtName);
        userName = findViewById(R.id.txtUsername);
        password = findViewById(R.id.txtPwd);
        btnSignUp = findViewById(R.id.btnSignUpTodb);
        signUp();
    }

    private void signUp() {
        btnSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name1 = name.getText().toString().trim();
                String username = userName.getText().toString().trim();
                String pwd =  password.getText().toString().trim();
                if(name1 != "" && username !="" && pwd != ""){
                    String userId =myRef.push().getKey();
                    LocalDate date = LocalDate.now();
                    int day = date.getDayOfMonth();
                    int month = date.getMonthValue();
                    int year = date.getYear();
                    String currentDay = String.valueOf(day+"-"+month+"-"+year);
                    User user = new User(name1,username,pwd,currentDay);
                    myRef.child(userId).setValue(user);
                    Toast.makeText(RegisterActivity.this, "Sign up sucessfully", Toast.LENGTH_SHORT).show();
                    finish();
                }
                else {
                    Toast.makeText(RegisterActivity.this, "Invalid username or password", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
