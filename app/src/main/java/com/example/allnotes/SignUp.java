package com.example.allnotes;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class SignUp extends AppCompatActivity {

    private EditText msignupemail, msignuppassword;
    private Button msignup;
    private TextView mgotologin;

    private FirebaseAuth firebaseAuth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

//        getSupportActionBar().hide();

        msignupemail = findViewById(R.id.signupemail);
        msignuppassword = findViewById(R.id.signuppassword);
        msignup = findViewById(R.id.signup);
        mgotologin = findViewById(R.id.gotologin);

        firebaseAuth = FirebaseAuth.getInstance();

        mgotologin.setOnClickListener(view -> {
            Intent intent = new Intent(SignUp.this, MainActivity.class);
            startActivity(intent);
        });

        msignup.setOnClickListener(view -> {
            String mail = msignupemail.getText().toString().trim();
            String password = msignuppassword.getText().toString().trim();

            if (mail.isEmpty() || password.isEmpty()) {
                Toast.makeText(getApplicationContext(), "Điền vào tất cả các miền", Toast.LENGTH_SHORT).show();
            } else if (password.length() < 7) {
                Toast.makeText(getApplicationContext(), "Mật khẩu phải chứa hơn 7 ký tự", Toast.LENGTH_SHORT).show();
            } else {
                /// registered the user to firebase
                firebaseAuth.createUserWithEmailAndPassword(mail, password).addOnCompleteListener(task -> {
                    if (task.isSuccessful()) {
                        Toast.makeText(getApplicationContext(), "Đăng ký thành công!", Toast.LENGTH_SHORT).show();
                        sendEmailVerification();
                    } else {
                        Toast.makeText(getApplicationContext(), "Đăng ký không thành công!", Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });
    }

    //send email verification
    private void sendEmailVerification() {
        FirebaseUser firebaseUser = FirebaseAuth.getInstance().getCurrentUser();
        if (firebaseUser != null) {
            firebaseUser.sendEmailVerification().addOnCompleteListener(task -> {
                Toast.makeText(getApplicationContext(), "Đã gửi email xác nhận! Hãy xác nhận trước khi đăng nhập!", Toast.LENGTH_SHORT).show();
                firebaseAuth.signOut();
                finish();
                startActivity(new Intent(SignUp.this, MainActivity.class));
            });
        } else {
            Toast.makeText(getApplicationContext(), "Gửi email xác nhận không thành công!", Toast.LENGTH_SHORT).show();
        }
    }
}