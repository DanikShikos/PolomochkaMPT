package com.example.polomkampt;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import org.w3c.dom.Text;

public class AuthActivity extends AppCompatActivity {

    private Button btnAuth,btnBack;
    private EditText etMail,etPas;
    private FirebaseAuth mAuth;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_auth);

        init();

    }

    private void init(){
        btnAuth = (Button) findViewById(R.id.btAuth);
        btnBack = (Button) findViewById(R.id.btBackAuth);
        etMail = findViewById(R.id.etInMail);
        etPas = findViewById(R.id.etInPas);

        View.OnClickListener oclBtnAuth = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!TextUtils.isEmpty(etMail.getText()) && !TextUtils.isEmpty(etPas.getText())){
                    mAuth.signInWithEmailAndPassword(etMail.getText().toString(),
                            etPas.getText().toString()).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()){
                                Toast.makeText(getApplicationContext(),"Добро пожаловать!", Toast.LENGTH_SHORT).show();
                                Intent intent = new Intent(AuthActivity.this, WorkActivity.class);
                                if (etMail.getText().toString().equals("s-laka@mail.ru") )
                                    intent.putExtra("admin", true);
                                else
                                    intent.putExtra("admin", false);
                                startActivity(intent);
                            }
                            else {
                                Toast.makeText(getApplicationContext(),"Введены неверные данные!", Toast.LENGTH_SHORT).show();
                            }
                        }
                    });


                }
                else {
                    Toast.makeText(getApplicationContext(),"Введите корректные данные", Toast.LENGTH_SHORT).show();
                }
            }
        };
        btnAuth.setOnClickListener(oclBtnAuth);

        View.OnClickListener oclBtnBack = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AuthActivity.this, MainActivity.class);
                startActivity(intent);
            }
        };
        btnBack.setOnClickListener(oclBtnBack);

        mAuth = FirebaseAuth.getInstance();
    }


}
