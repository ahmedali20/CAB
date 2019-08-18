package com.example.cab;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class CustomerLoginRegisterActivity extends AppCompatActivity {

    private Button customerLoginButton, customerRegisterButton;
    private TextView customerStatus, customerRegisterLink;
    private EditText emailCustomer, passwordCustomer;


    private ProgressDialog loadingBar;

    private FirebaseAuth mAuth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customer_login_register);

        InitializeFireBase();

        InitializeFields();

        customerRegisterButton.setVisibility(View.INVISIBLE);
        customerRegisterButton.setEnabled(false);

        customerRegisterLink.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                customerLoginButton.setVisibility(View.INVISIBLE);
                customerRegisterLink.setVisibility(View.INVISIBLE);
                customerStatus.setText(R.string.customer_register);

                customerRegisterButton.setVisibility(View.VISIBLE);
                customerRegisterButton.setEnabled(true);
            }
        });

        customerRegisterButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String email = emailCustomer.getText().toString();
                String password = passwordCustomer.getText().toString();

                RegisterCustomer(email, password);
            }
        });
    }

    private void InitializeFireBase() {

        mAuth = FirebaseAuth.getInstance();
    }

    private void InitializeFields() {

        customerLoginButton = findViewById(R.id.customer_login_btn);
        customerRegisterButton = findViewById(R.id.customer_register_btn);
        customerStatus = findViewById(R.id.customer_status);
        customerRegisterLink = findViewById(R.id.customer_register_link);
        emailCustomer = findViewById(R.id.customer_email);
        passwordCustomer = findViewById(R.id.customer_password);
        loadingBar = new ProgressDialog(this);

    }

    private void RegisterCustomer(String email, String password) {

        if (TextUtils.isEmpty(email)) {

            Toast.makeText(this, "please enter your email", Toast.LENGTH_SHORT).show();
        }

        if (TextUtils.isEmpty(password)) {

            Toast.makeText(this, "please enter your password", Toast.LENGTH_SHORT).show();
        } else {

            loadingBar.setTitle(R.string.customer_registration);
            loadingBar.setMessage("please wait, while we are register your data");
            loadingBar.show();

            mAuth.createUserWithEmailAndPassword(email, password)
                    .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {

                            if (task.isSuccessful()) {

                                Toast.makeText(CustomerLoginRegisterActivity.this, "Driver Register Successfully...", Toast.LENGTH_SHORT).show();
                                loadingBar.dismiss();
                            } else {

                                Toast.makeText(CustomerLoginRegisterActivity.this, "Registration UnSuccessfully,please try again...", Toast.LENGTH_SHORT).show();
                                loadingBar.dismiss();
                            }
                        }
                    });
        }
    }
}
