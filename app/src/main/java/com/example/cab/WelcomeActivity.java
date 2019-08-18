package com.example.cab;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

public class WelcomeActivity extends AppCompatActivity {

    private Button driverButton, customerButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);

        InitializeFields();

        driverButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendDriverToLoginRegisterActivity();
            }
        });

        customerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendCustomerToLoginRegisterActivity();
            }
        });
    }

    private void InitializeFields() {

        driverButton = findViewById(R.id.driver_welcome_btn);
        customerButton = findViewById(R.id.customer_welcome_btn);
    }

    private void sendDriverToLoginRegisterActivity() {


        Intent welcomeDriverIntent = new Intent(WelcomeActivity.this, DriverLoginRegisterActivity.class);
        startActivity(welcomeDriverIntent);
    }

    private void sendCustomerToLoginRegisterActivity() {

        Intent welcomeCustomerIntent = new Intent(WelcomeActivity.this, CustomerLoginRegisterActivity.class);
        startActivity(welcomeCustomerIntent);
    }
}
