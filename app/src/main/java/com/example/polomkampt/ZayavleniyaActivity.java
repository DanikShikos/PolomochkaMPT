package com.example.polomkampt;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class ZayavleniyaActivity extends AppCompatActivity {
    //переменные
    //элемент списка
    private ListView lwZayavlenie;
    //адаптер
    private ArrayAdapter<String> adapter;
    //заполняемый список для адаптера
    private List<String> listData;
    //временный список на основе класса элемента
    private List<Zayavlenie> listTemp;
    //база данных
    private DatabaseReference db;
    //ключ представления
    private String Zayavlenie_KEY = "Zayavlenie";
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_zayvaleniya);

        init();
        getDataFromDB();
        setItemOnClick();
    }
    //присвоение переменным элементов формы
    private void init(){
        lwZayavlenie = findViewById(R.id.lwZayavleniya);
        listData = new ArrayList<>();
        listTemp = new ArrayList<>();
        //создание адпатера на основе списка
        adapter = new ArrayAdapter<>(this,android.R.layout.simple_list_item_1,listData);
        //присвоение адаптера списку формы
        lwZayavlenie.setAdapter(adapter);
        //ссылка на представление в базе
        db = FirebaseDatabase.getInstance().getReference(Zayavlenie_KEY);
    }

    //заполнение списка данными из БД
    private void getDataFromDB(){
        //установка прослушивания изменения значений
        ValueEventListener vListener = new ValueEventListener() {
            //при изменении данных передает копию базы
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                //очистка списков если не пусты
                if (listData.size()>0) listData.clear();
                if (listTemp.size()>0) listTemp.clear();
                //заполнение списков всеми элементами из текущего снэпшота
                for (DataSnapshot ds : dataSnapshot.getChildren()){
                    Zayavlenie zayavlenie = ds.getValue(Zayavlenie.class);
                    assert zayavlenie != null;
                    listData.add("Статус: "+zayavlenie.Status + "\n"+ "Описание: "+zayavlenie.Opis+"\n"+"Время подачи:" +zayavlenie.time );
                    listTemp.add(zayavlenie);
                }
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        };
        //присвоение базе данных прослушивания изменения значений
        db.addValueEventListener(vListener);
    }
    //передача данных и открытие формы элемента при нажатии
    private void setItemOnClick(){
        lwZayavlenie.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Zayavlenie zayavlenie = listTemp.get(position);
                Intent i = new Intent(ZayavleniyaActivity.this,ZayavlenieShowActivity.class);
                i.putExtra("id",zayavlenie.id);
                i.putExtra("mail",zayavlenie.mail);
                i.putExtra("nomkab",zayavlenie.NomKab);
                i.putExtra("nomustr",zayavlenie.NomUstr);
                i.putExtra("opis",zayavlenie.Opis);
                i.putExtra("status",zayavlenie.Status);
                i.putExtra("time",zayavlenie.time);
                startActivity(i);
            }
        });
    }

}
