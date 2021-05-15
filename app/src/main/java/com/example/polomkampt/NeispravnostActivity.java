package com.example.polomkampt;

import android.app.Activity;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.sql.Time;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.Calendar;

public class NeispravnostActivity extends AppCompatActivity {
    private Button btnInform, btnBack;
    private EditText etNomKab, etNomUstr, etOpis;
    private DatabaseReference db;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_neispravnost);

        init();
    }

    private void init(){
        btnInform = (Button) findViewById(R.id.btInform);
        btnBack = (Button) findViewById(R.id.btBack);

        etNomKab = (EditText) findViewById(R.id.etNomKab);
        etNomUstr = (EditText) findViewById(R.id.etNomUstr);
        etOpis = (EditText) findViewById(R.id.etOpis);

        db = FirebaseDatabase.getInstance().getReference();

        View.OnClickListener oclBtnInform = new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.O)
            @Override
            public void onClick(View v) {
                if (!TextUtils.isEmpty(etNomKab.getText()) && !TextUtils.isEmpty(etNomKab.getText()) && !TextUtils.isEmpty(etNomKab.getText())){
                    String id = db.push().getKey();
                    String NomKab = etNomKab.getText().toString();
                    String NomUstr = etNomUstr.getText().toString();
                    String Opis = etOpis.getText().toString();

                    Intent i = getIntent();
                    String mail = i.getStringExtra("mail");

                    String Status = "Распределение";

                    String Time = new SimpleDateFormat("MM.dd.yyyy HH:mm:ss").format(Calendar.getInstance().getTime());

                    Zayavlenie newZayavlenie = new Zayavlenie(id,mail,NomKab,NomUstr,Opis,Status,Time);

                    db.child("Zayavlenie").child(id).setValue(newZayavlenie);
                    Toast.makeText(NeispravnostActivity.this,"Спасибо за сообщение! Вас оповестят при исправлении", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(NeispravnostActivity.this, MainActivity.class);
                    startActivity(intent);
                }
                else {
                    Toast.makeText(NeispravnostActivity.this,"Некоторые поля незаполнены", Toast.LENGTH_LONG).show();
                }
            }
        };
        btnInform.setOnClickListener(oclBtnInform);

        View.OnClickListener oclBtnBack = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(NeispravnostActivity.this, MailInputActivity.class);
                startActivity(intent);
            }
        };
        btnBack.setOnClickListener(oclBtnBack);


    }
}
