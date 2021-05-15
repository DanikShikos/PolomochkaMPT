package com.example.polomkampt;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class MailInputActivity extends AppCompatActivity {
    Button btnNext,btnBack;
    EditText etMail;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_mailinput);

        btnNext = (Button) findViewById(R.id.btNext);
        btnBack = (Button) findViewById(R.id.btBackMail);
        etMail = findViewById(R.id.etMail);

        View.OnClickListener oclBtnNext = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isValidEmail(etMail.getText())){
                    Intent intent = new Intent(MailInputActivity.this, NeispravnostActivity.class);
                    intent.putExtra("mail",etMail.getText().toString());
                    startActivity(intent);
                }
                else Toast.makeText(MailInputActivity.this,"Введите почту",Toast.LENGTH_SHORT).show();

            }
        };
        btnNext.setOnClickListener(oclBtnNext);

        View.OnClickListener oclBtnBack = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MailInputActivity.this, MainActivity.class);
                startActivity(intent);
            }
        };
        btnBack.setOnClickListener(oclBtnBack);
    }

    public final static boolean isValidEmail(CharSequence target) {
        return !TextUtils.isEmpty(target) && android.util.Patterns.EMAIL_ADDRESS.matcher(target).matches();
    }
}
