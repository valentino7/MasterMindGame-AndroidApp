package com.example.antho.mastermind.adapter;


import java.util.Vector;

public class MasterMind {
    private int colore1;
    private int colore2;
    private int colore3;
    private int colore4;
    private int colore5;
    private int white,black;

    public MasterMind(int colore1,int colore2,int colore3,int colore4, int colore5,Integer[] v){
        this.colore1 = colore1;
        this.colore2 = colore2;
        this.colore3 = colore3;
        this.colore4 = colore4;
        this.colore5 = colore5;
        this.black = v[0];
        this.white = v[1];
    }
    public MasterMind(int colore1,int colore2,int colore3,int colore4, int colore5) {
        this.colore1 = colore1;
        this.colore2 = colore2;
        this.colore3 = colore3;
        this.colore4 = colore4;
        this.colore5 = colore5;
    }

    public int getColore1() {
        return colore1;
    }

    public void setColore1(int colore1) {
        this.colore1 = colore1;
    }

    public int getColore2() {
        return colore2;
    }

    public void setColore2(int colore2) {
        this.colore2 = colore2;
    }

    public int getColore3() {
        return colore3;
    }

    public void setColore3(int colore3) {
        this.colore3 = colore3;
    }

    public int getColore4() {
        return colore4;
    }

    public void setColore4(int colore4) {
        this.colore4 = colore4;
    }

    public int getColore5() {
        return colore5;
    }

    public void setColore5(int colore5) {
        this.colore5 = colore5;
    }

    public int getBlack() {
        return black;
    }

    public void setBlack(int black) {
        this.black = black;
    }

    public int getWhite() {
        return white;
    }

    public void setWhite(int white) {
        this.white = white;
    }
}
