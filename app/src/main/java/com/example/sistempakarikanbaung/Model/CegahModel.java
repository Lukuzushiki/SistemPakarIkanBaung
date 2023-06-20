package com.example.sistempakarikanbaung.Model;

public class CegahModel {
    String id, cara_mencegah, id_penyakit;

    public CegahModel() {
    }

    public CegahModel(String id, String cara_mencegah, String id_penyakit) {
        this.id = id;
        this.cara_mencegah = cara_mencegah;
        this.id_penyakit = id_penyakit;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCara_mencegah() {
        return cara_mencegah;
    }

    public void setCara_mencegah(String cara_mencegah) {
        this.cara_mencegah = cara_mencegah;
    }

    public String getId_penyakit() {
        return id_penyakit;
    }

    public void setId_penyakit(String id_penyakit) {
        this.id_penyakit = id_penyakit;
    }
}
