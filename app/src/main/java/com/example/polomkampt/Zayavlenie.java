package com.example.polomkampt;

import java.sql.Time;

public class Zayavlenie {
    public String id, mail, NomKab, NomUstr, Opis, Status, time;
    public Zayavlenie() {

    }

    public Zayavlenie(String id, String mail, String nomKab, String nomUstr, String opis, String status, String time) {
        this.id = id;
        this.mail = mail;
        this.NomKab = nomKab;
        this.NomUstr = nomUstr;
        this.Opis = opis;
        this.Status = status;
        this.time = time;
    }
}
