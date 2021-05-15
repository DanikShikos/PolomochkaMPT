package com.example.polomkampt;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class ZayavlenieShowActivity extends AppCompatActivity {
    private TextView tvNomZayav,tvMail, tvNomKab,tvNomUstr,tvTime,tvOpis,tvStatus;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_zayavlenie_show);
        init();
        getIntentMain();
    }

    private void init(){
        tvNomZayav = findViewById(R.id.tvNomZayavl);
        tvMail = findViewById(R.id.tvMailZ);
        tvNomKab = findViewById(R.id.tvNomKabZ);
        tvNomUstr = findViewById(R.id.tvNomUstrZ);
        tvTime = findViewById(R.id.tvTimeZ);
        tvOpis = findViewById(R.id.tvOpisZ);
        tvStatus = findViewById(R.id.tvStatusZ);
    }
    //взять переданные о заявлении
    private void getIntentMain(){
        Intent i = getIntent();
        if (i != null) {
            tvMail.append(i.getStringExtra("mail"));
            tvNomKab.append(i.getStringExtra("nomkab"));
            tvNomUstr.append(i.getStringExtra("nomustr"));
            tvOpis.append(i.getStringExtra("opis"));
            tvTime.append(i.getStringExtra("time"));
            tvStatus.append(i.getStringExtra("status"));
            tvNomZayav.append(i.getStringExtra("id"));

        }
    }

    public void OnClickNaznach(View view){
        Intent g = getIntent();
        Intent i = new Intent(ZayavlenieShowActivity.this,SotrudnikiActivity.class);
        i.putExtra("id",g.getStringExtra("id"));
        startActivity(i);
    }

    public void OnClickBack(View view){
        Intent i = new Intent(ZayavlenieShowActivity.this,ZayavleniyaActivity.class);
        startActivity(i);
    }

}
