package com.example.polomkampt;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    Button btnInform,btnSignIn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnInform = (Button) findViewById(R.id.bInform);
        btnSignIn = (Button) findViewById(R.id.bSignIn);

        View.OnClickListener oclBtnInform = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, MailInputActivity.class);
                startActivity(intent);
            }
        };
        btnInform.setOnClickListener(oclBtnInform);

        View.OnClickListener oclBtnSignIn = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, AuthActivity.class);
                startActivity(intent);
            }
        };
        btnSignIn.setOnClickListener(oclBtnSignIn);
    }
}