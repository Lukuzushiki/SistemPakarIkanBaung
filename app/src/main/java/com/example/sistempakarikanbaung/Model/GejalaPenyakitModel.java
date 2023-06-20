package com.example.sistempakarikanbaung.Model;

public class GejalaPenyakitModel {
    String id, gejala, id_penyakit;

    public GejalaPenyakitModel() {
    }

    public GejalaPenyakitModel(String id, String gejala, String id_penyakit) {
        this.id = id;
        this.gejala = gejala;
        this.id_penyakit = id_penyakit;
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

    public String getId_penyakit() {
        return id_penyakit;
    }

    public void setId_penyakit(String id_penyakit) {
        this.id_penyakit = id_penyakit;
    }
}
