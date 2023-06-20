package com.example.sistempakarikanbaung.Model;

public class PenyakitModel {
    String id, penyakit;
    double probabilitas_a;

    public PenyakitModel() {
    }

    public PenyakitModel(String id, String penyakit, double probabilitas_a) {
        this.id = id;
        this.penyakit = penyakit;
        this.probabilitas_a = probabilitas_a;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPenyakit() {
        return penyakit;
    }

    public void setPenyakit(String penyakit) {
        this.penyakit = penyakit;
    }

    public double getProbabilitas_a() {
        return probabilitas_a;
    }

    public void setProbabilitas_a(double probabilitas_a) {
        this.probabilitas_a = probabilitas_a;
    }
}
