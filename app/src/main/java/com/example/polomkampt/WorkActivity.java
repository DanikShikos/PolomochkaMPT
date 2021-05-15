package com.example.polomkampt;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class WorkActivity extends AppCompatActivity {
    Button btnZayvleniya,btnSotrudniki,btnLichKab,btnExit,btnRegSotr;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_work);

        init();


    }

    private void init(){

        Intent i = getIntent();
        boolean admin = i.getBooleanExtra("admin", true);

        btnZayvleniya = (Button)findViewById(R.id.btZayavleniya);
        btnSotrudniki = (Button)findViewById(R.id.btSotrudniki);
        btnLichKab = (Button)findViewById(R.id.btLichKab);
        btnExit = (Button)findViewById(R.id.btExit);
        btnRegSotr = (Button)findViewById(R.id.btRegSotr);

        if (admin)
        btnRegSotr.setVisibility(View.VISIBLE);
        else btnRegSotr.setVisibility(View.INVISIBLE);

        View.OnClickListener oclbtnZayvleniya = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(WorkActivity.this, ZayavleniyaActivity.class);
                startActivity(intent);
            }
        };
        btnZayvleniya.setOnClickListener(oclbtnZayvleniya);

        View.OnClickListener oclbtnSotrudniki = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(WorkActivity.this, SotrudnikiActivity.class);
                startActivity(intent);
            }
        };
        btnSotrudniki.setOnClickListener(oclbtnSotrudniki);

        View.OnClickListener oclbtnLichKab = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(WorkActivity.this, LichKabActivity.class);
                startActivity(intent);
            }
        };
        btnLichKab.setOnClickListener(oclbtnLichKab);

        View.OnClickListener oclbtnExit = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(WorkActivity.this, MainActivity.class);
                startActivity(intent);
            }
        };
        btnExit.setOnClickListener(oclbtnExit);

        View.OnClickListener oclbtnRegSotr = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(WorkActivity.this, RegistrationActivity.class);
                startActivity(intent);
            }
        };
        btnRegSotr.setOnClickListener(oclbtnRegSotr);
    }
}
