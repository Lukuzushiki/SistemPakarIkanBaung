package com.example.sistempakarikanbaung.Model;

public class ObatModel {
    String id, obat, id_penyakit, jenis_pengobatan;

    public ObatModel() {
    }

    public ObatModel(String id, String obat, String id_penyakit, String jenis_pengobatan) {
        this.id = id;
        this.obat = obat;
        this.id_penyakit = id_penyakit;
        this.jenis_pengobatan = jenis_pengobatan;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getObat() {
        return obat;
    }

    public void setObat(String obat) {
        this.obat = obat;
    }

    public String getId_penyakit() {
        return id_penyakit;
    }

    public void setId_penyakit(String id_penyakit) {
        this.id_penyakit = id_penyakit;
    }

    public String getJenis_pengobatan() {
        return jenis_pengobatan;
    }

    public void setJenis_pengobatan(String jenis_pengobatan) {
        this.jenis_pengobatan = jenis_pengobatan;
    }
}
