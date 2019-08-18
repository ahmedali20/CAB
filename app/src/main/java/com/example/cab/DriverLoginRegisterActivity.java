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


public class DriverLoginRegisterActivity extends AppCompatActivity {

    private Button driverLoginButton, driverRegisterButton;
    private TextView driverStatus, driverRegisterLink;
    private EditText emailDriver, passwordDriver;

    private ProgressDialog loadingBar;

    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_driver_login_register);

        InitializeFireBase();

        InitializeFields();

        driverRegisterButton.setVisibility(View.INVISIBLE);
        driverRegisterButton.setEnabled(false);

        driverRegisterLink.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                driverLoginButton.setVisibility(View.INVISIBLE);
                driverRegisterLink.setVisibility(View.INVISIBLE);
                driverStatus.setText(R.string.driver_register);

                driverRegisterButton.setVisibility(View.VISIBLE);
                driverRegisterButton.setEnabled(true);
            }
        });

        driverRegisterButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String email = emailDriver.getText().toString();
                String password = passwordDriver.getText().toString();

                RegisterDriver(email, password);
            }
        });
    }

    private void InitializeFireBase() {

        mAuth = FirebaseAuth.getInstance();
    }


    private void InitializeFields() {

        driverLoginButton = findViewById(R.id.login_driver_btn);
        driverRegisterButton = findViewById(R.id.register_driver_btn);
        driverStatus = findViewById(R.id.title_driver);
        driverRegisterLink = findViewById(R.id.create_driver_account);
        emailDriver = findViewById(R.id.driver_email);
        passwordDriver = findViewById(R.id.driver_password);
        loadingBar = new ProgressDialog(this);
    }

    private void RegisterDriver(String email, String password) {

        if (TextUtils.isEmpty(email)) {

            Toast.makeText(this, "please enter your email", Toast.LENGTH_SHORT).show();
        }

        if (TextUtils.isEmpty(password)) {

            Toast.makeText(this, "please enter your password", Toast.LENGTH_SHORT).show();
        } else {

            loadingBar.setTitle(R.string.driver_registration);
            loadingBar.setMessage("please wait, while we are register your data");
            loadingBar.show();

            mAuth.createUserWithEmailAndPassword(email, password)
                    .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {

                            if (task.isSuccessful()) {

                                Toast.makeText(DriverLoginRegisterActivity.this, "Driver Register Successfully...", Toast.LENGTH_SHORT).show();
                                loadingBar.dismiss();
                            } else {

                                Toast.makeText(DriverLoginRegisterActivity.this, "Registration UnSuccessfully,please try again...", Toast.LENGTH_SHORT).show();
                                loadingBar.dismiss();
                            }
                        }
                    });
        }
    }


}