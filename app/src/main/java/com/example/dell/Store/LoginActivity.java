package com.example.dell.Store;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class LoginActivity extends AppCompatActivity {

    @BindView(R.id.email_edt)
    TextInputEditText emailEdt;
    @BindView(R.id.passwoord_edt)
    TextInputEditText passwoordEdt;
    @BindView(R.id.login_btn)
    Button loginBtn;
    @BindView(R.id.signup_btn)
    TextView signupBtn;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);
        mAuth = FirebaseAuth.getInstance();
        FirebaseUser currentUser = mAuth.getCurrentUser();
        if(currentUser !=null)
        { Intent homeintent = new Intent(LoginActivity.this, HomeActivity.class);
            startActivity(homeintent);
            finish();
        }
    }

    @OnClick({R.id.login_btn, R.id.signup_btn})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.login_btn:
                String l_email, l_password;
                l_email = emailEdt.getText().toString();
                l_password = passwoordEdt.getText().toString();
                mAuth.createUserWithEmailAndPassword(l_email, l_password)
                        .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if (task.isSuccessful()) {
                                    Log.e("RESPONSE_SUCCESS", "createUserWithEmail:success");
                                    Intent homeintent = new Intent(LoginActivity.this, HomeActivity.class);
                                    startActivity(homeintent);
                                    finish();

                                } else {
                                    Log.e("RESPONSE_ERROR", "createUserWithEmail:failure", task.getException());
                                }
                            }
                        });

                break;
            case R.id.signup_btn:
                Intent signup = new Intent(this, RegisterActivity.class);
                startActivity(signup);
                break;
        }
    }
}
