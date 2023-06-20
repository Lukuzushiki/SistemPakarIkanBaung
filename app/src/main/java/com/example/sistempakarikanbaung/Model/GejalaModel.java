package com.example.sistempakarikanbaung.Model;

import android.os.Parcel;
import android.os.Parcelable;

public class GejalaModel {
    String id;
    String gejala;
    double probabilitas_b, probabilitas_p01, probabilitas_p02, probabilitas_p03, probabilitas_p04, probabilitas_p05;

    public GejalaModel() {
    }

    public GejalaModel(String id, String gejala, double probabilitas_b, double probabilitas_p01, double probabilitas_p02, double probabilitas_p03, double probabilitas_p04, double probabilitas_p05) {
        this.id = id;
        this.gejala = gejala;
        this.probabilitas_b = probabilitas_b;
        this.probabilitas_p01 = probabilitas_p01;
        this.probabilitas_p02 = probabilitas_p02;
        this.probabilitas_p03 = probabilitas_p03;
        this.probabilitas_p04 = probabilitas_p04;
        this.probabilitas_p05 = probabilitas_p05;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getGejala() {
        return gejala;
    }

    public void setGejala(String gejala) {
        this.gejala = gejala;
    }

    public double getProbabilitas_b() {
        return probabilitas_b;
    }

    public void setProbabilitas_b(double probabilitas_b) {
        this.probabilitas_b = probabilitas_b;
    }

    public double getProbabilitas_p01() {
        return probabilitas_p01;
    }

    public void setProbabilitas_p01(double probabilitas_p01) {
        this.probabilitas_p01 = probabilitas_p01;
    }

    public double getProbabilitas_p02() {
        return probabilitas_p02;
    }

    public void setProbabilitas_p02(double probabilitas_p02) {
        this.probabilitas_p02 = probabilitas_p02;
    }

    public double getProbabilitas_p03() {
        return probabilitas_p03;
    }

    public void setProbabilitas_p03(double probabilitas_p03) {
        this.probabilitas_p03 = probabilitas_p03;
    }

    public double getProbabilitas_p04() {
        return probabilitas_p04;
    }

    public void setProbabilitas_p04(double probabilitas_p04) {
        this.probabilitas_p04 = probabilitas_p04;
    }

    public double getProbabilitas_p05() {
        return probabilitas_p05;
    }

    public void setProbabilitas_p05(double probabilitas_p05) {
        this.probabilitas_p05 = probabilitas_p05;
    }
}
