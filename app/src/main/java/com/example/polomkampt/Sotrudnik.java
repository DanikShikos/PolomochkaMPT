package com.example.polomkampt;

public class Sotrudnik {
    public String id, FIO, mail,phone,status;

    public Sotrudnik() {
    }

    public Sotrudnik(String id, String FIO, String mail, String phone, String status) {
        this.id = id;
        this.FIO = FIO;
        this.mail = mail;
        this.status = status;
        this.phone = phone;
    }
}
