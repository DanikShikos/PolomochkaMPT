package com.example.polomkampt;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Insets;
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

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class SotrudnikiActivity extends AppCompatActivity {
    //переменные
    //элемент списка
    private ListView lwSotrudniki;
    //адаптер
    private ArrayAdapter<String> adapter;
    //заполняемый список для адаптера
    private List<String> listData;
    //временный список на основе класса элемента
    private List<Sotrudnik> listTemp;
    //база данных
    private DatabaseReference db;
    //ключ представления
    private String Sotrudnik_KEY = "Sotrudnik";
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_sotrudniki);

        init();
        getDataFromDB();
        setItemOnClick();
    }
    //присвоение переменным элементов формы
    private void init(){
        lwSotrudniki = findViewById(R.id.lwSotrudniki);
        listData = new ArrayList<>();
        listTemp = new ArrayList<>();
        //создание адпатера на основе списка
        adapter = new ArrayAdapter<>(this,android.R.layout.simple_list_item_1,listData);
        //присвоение адаптера списку формы
        lwSotrudniki.setAdapter(adapter);
        //ссылка на представление в базе
        db = FirebaseDatabase.getInstance().getReference(Sotrudnik_KEY);
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
                    Sotrudnik sotrudnik = ds.getValue(Sotrudnik.class);
                    assert sotrudnik != null;
                    listData.add(sotrudnik.FIO + " " + sotrudnik.phone + " " + sotrudnik.status);
                    listTemp.add(sotrudnik);
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
        lwSotrudniki.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                //смена статуса сотрудника
                Sotrudnik sotrudnik = listTemp.get(position);
                DatabaseReference dbRef = FirebaseDatabase.getInstance().getReference();
                dbRef.child("Sotrudnik").child(sotrudnik.id).child("status").setValue("Назначен");
                //смена статуса заявления
                Intent g = getIntent();
                //время назначения
                String timeNaznach = new SimpleDateFormat("MM.dd.yyyy HH:mm:ss").format(Calendar.getInstance().getTime());
                //Формирование инициалов
                String[] FIO = sotrudnik.FIO.split(" ");
                String Initials = FIO[0] + " " +FIO[1].charAt(0) + ". "+FIO[2].charAt(0) + ".";
                dbRef.child("Zayavlenie").child(g.getStringExtra("id")).child("Status")
                        .setValue("Назначен "+ timeNaznach + "\n"+ Initials + " " + sotrudnik.mail);
                //переход на новую форму
                Intent i = new Intent(SotrudnikiActivity.this,ZayavleniyaActivity.class);
                //запуск перехода
                startActivity(i);
            }
        });
    }
}
