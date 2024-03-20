package com.example.phongthuy;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    Button btnLogin;
    Button btnRegister;
    EditText txtName,txtPwd;
    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference myRef = database.getReference("User");
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btnLogin = findViewById(R.id.btnLogin);
        btnRegister = findViewById(R.id.btnRegister);
        txtName = findViewById(R.id.txtUsername);
        txtPwd = findViewById(R.id.txtPwd);
        login();
        register();
    }

    private void register() {
        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this,RegisterActivity.class);
                startActivity(i);
            }
        });
    }

    private void login() {
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = txtName.getText().toString().trim();
                String pwd = txtPwd.getText().toString().trim();
                List<User> lst = new ArrayList<>();
                myRef.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        for(DataSnapshot dataSnapshot:snapshot.getChildren()){
                            User user =dataSnapshot.getValue(User.class);
                            lst.add(user);
                            if(user.getUsername().equals(name) && user.getPassword().equals(pwd)){
                                Toast.makeText(MainActivity.this, "Login sucessfully", Toast.LENGTH_SHORT).show();
                                Intent i = new Intent(MainActivity.this,ProductActivity.class);
                                startActivity(i);
                                break;
                            }else {
                                Toast.makeText(MainActivity.this, "username or password incorrect", Toast.LENGTH_SHORT).show();
                                txtName.setText("");
                                txtPwd.setText("");
                            }
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });
            }
        });
    }
}