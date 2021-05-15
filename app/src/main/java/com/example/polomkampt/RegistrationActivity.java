package com.example.polomkampt;

import android.app.Activity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
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
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;


public class RegistrationActivity extends AppCompatActivity {
    private EditText etMail,etPassword,etFIO,etPhone;
    private FirebaseAuth mAuth;

    private DatabaseReference db;
    private String Sotrudnik_KEY = "Sotrudnik";
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_registration);
        init();
    }

    private void init (){
         etMail = findViewById(R.id.etUpMail);
         etPassword = findViewById(R.id.etUpPas);
         etFIO = findViewById(R.id.etFIO);
         etPhone = findViewById(R.id.etPhone);

         mAuth = FirebaseAuth.getInstance();

         db = FirebaseDatabase.getInstance().getReference();
    }

    @Override
    protected void onStart() {
        super.onStart();
        FirebaseUser currentUser = mAuth.getCurrentUser();
        if (currentUser != null) {
            Toast.makeText(this,"Пользователь в системе", Toast.LENGTH_SHORT).show();
        }
        else {
            Toast.makeText(this,"Пользователь не в системе", Toast.LENGTH_SHORT).show();
        }
    }

    public void  onClickReg(View view) {
        String id = db.push().getKey();
        String Mail = etMail.getText().toString();
        String FIO = etFIO.getText().toString();
        String Phone = etPhone.getText().toString();
        String Status = "Не активен";

        if (!TextUtils.isEmpty(etMail.getText().toString())
                &&!TextUtils.isEmpty(etPassword.getText().toString())
                &&!TextUtils.isEmpty(etMail.getText().toString())
                &&!TextUtils.isEmpty(etFIO.getText().toString())
                &&!TextUtils.isEmpty(etPhone.getText().toString())){


            mAuth.createUserWithEmailAndPassword(etMail.getText().toString(),
                    etPassword.getText().toString()).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if (task.isSuccessful()){
                        Toast.makeText(getApplicationContext(),"Сотрудник зарегистрирован!", Toast.LENGTH_SHORT).show();
                        Sotrudnik newSotrudnik = new Sotrudnik(id,FIO,Mail,Phone,Status);
                        db.child("Sotrudnik").child(id).setValue(newSotrudnik);

                    }
                    else {
                        Toast.makeText(getApplicationContext(),
                                "Сотрудник с такими данными существует", Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }
        else {
            Toast.makeText(getApplicationContext(),"Неверные данные", Toast.LENGTH_SHORT).show();
        }
    }
}
