package com.example.antho.mastermind.database;


import java.text.DateFormat;

public class Partita {
    private Integer id;
    private Integer difficolta;
    private String data;
    private Integer risultsato;
    private Integer tentativi;

    public Partita(Integer difficolta,String data,Integer risultato, Integer tentativi){
        this.data = data;
        this.difficolta = difficolta;
        this.tentativi = tentativi;
        this.risultsato = risultato;
    }

    public Integer getTentativi() {
        return tentativi;
    }

    public void setTentativi(Integer tentativi) {
        this.tentativi = tentativi;
    }

    public Integer getRisultsato() {
        return risultsato;
    }

    public void setRisultsato(Integer risultsato) {
        this.risultsato = risultsato;
    }


    public Integer getDifficolta() {
        return difficolta;
    }

    public void setDifficolta(Integer difficolta) {
        this.difficolta = difficolta;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }
}
