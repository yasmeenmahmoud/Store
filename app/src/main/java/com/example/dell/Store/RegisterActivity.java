package com.example.dell.Store;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.Button;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class RegisterActivity extends AppCompatActivity {

    @BindView(R.id.email_edt)
    TextInputEditText emailEdt;
    @BindView(R.id.password_edt)
    TextInputEditText passwordEdt;
    @BindView(R.id.confirmpassword_edt)
    TextInputEditText confirmpasswordEdt;
    @BindView(R.id.signin_btn)
    Button signinBtn;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        ButterKnife.bind(this);
        // Initialize Firebase Auth
        mAuth = FirebaseAuth.getInstance();
    }

    @OnClick(R.id.signin_btn)
    public void onViewClicked() {

        String email, password, confirmpassword;
        email = emailEdt.getText().toString();
        password = passwordEdt.getText().toString();
        confirmpassword = confirmpasswordEdt.getText().toString();
        if (email.isEmpty() || email.equals("")) {
            emailEdt.setError("Error Email");
            return;

        }
        if (password.isEmpty() || password.equals("")) {
            passwordEdt.setError("Error password");
            return;

        }
        if (password.length() < 8) {
            passwordEdt.setError("Password should be at least 6 characters");
            return;

        }
        if (confirmpassword.isEmpty() || confirmpassword.equals("")) {
            confirmpasswordEdt.setError("Error");
            return;

        }
        if (!confirmpassword.equals(password)) {
            emailEdt.setError("password not match");
            return;

        }
        mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            Log.e("RESPONSE_SUCCESS", "createUserWithEmail:success");
                            Intent homeintent = new Intent(RegisterActivity.this, HomeActivity.class);
                            startActivity(homeintent);
                            finish();

                        } else {
                            Log.e("RESPONSE_ERROR", "createUserWithEmail:failure", task.getException());
                        }
                    }
                });

    }
}
