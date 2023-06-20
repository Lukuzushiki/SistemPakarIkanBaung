package com.example.sistempakarikanbaung.Database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.sistempakarikanbaung.Model.CegahModel;
import com.example.sistempakarikanbaung.Model.GejalaPenyakitModel;
import com.example.sistempakarikanbaung.Model.ObatModel;
import com.example.sistempakarikanbaung.Model.PenyakitModel;
import com.example.sistempakarikanbaung.Model.GejalaModel;

import java.util.ArrayList;

public class DatabaseHandler extends SQLiteOpenHelper {
    // static variable
    private static final int DATABASE_VERSION = 1;

    // Database name
    private static final String DATABASE_NAME = "SistemPakar.db";

    // table name
    private static final String TABLE_GEJALA = "tbl_gejala_list";
    private static final String TABLE_PENYAKIT = "tbl_penyakit_list";
    private static final String TABLE_OBAT = "tbl_obat";
    private static final String TABLE_CEGAH = "tbl_cegah";
    private static final String TABLE_GEJALA_PENYAKIT = "tbl_gejala_penyakit";

    // column tables
    private static final String KEY_ID = "id";
    private static final String KEY_GEJALA = "gejala";
    private static final String KEY_PENYAKIT = "penyakit";
    private static final String KEY_OBAT = "obat";
    private static final String KEY_MENCEGAH = "cara_mencegah";
    private static final String KEY_ID_PENYAKIT = "id_penyakit";
    private static final String KEY_JENIS_OBAT = "jenis_obat";
    private static final String KEY_PROBABILITAS_A = "probabilitas_a";
    private static final String KEY_PROBABILITAS_B = "probabilitas_b";
    private static final String KEY_PROBABILITAS_P01 = "probabilitas_p1";
    private static final String KEY_PROBABILITAS_P02 = "probabilitas_p2";
    private static final String KEY_PROBABILITAS_P03 = "probabilitas_p3";
    private static final String KEY_PROBABILITAS_P04 = "probabilitas_p4";
    private static final String KEY_PROBABILITAS_P05 = "probabilitas_p5";

    private SQLiteDatabase db;
    private static DatabaseHandler instance;

    public DatabaseHandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    public static synchronized DatabaseHandler getInstance(Context context) {
        if (instance == null) {
            instance = new DatabaseHandler(context.getApplicationContext());
        }
        return instance;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        this.db = db;

        final String CREATE_GEJALA_TABLE = "CREATE TABLE " +
                TABLE_GEJALA + "(" +
                KEY_ID + " TEXT PRIMARY KEY, " +
                KEY_GEJALA + " TEXT, " +
                KEY_PROBABILITAS_B + " REAL, " +
                KEY_PROBABILITAS_P01 + " REAL, " +
                KEY_PROBABILITAS_P02 + " REAL, " +
                KEY_PROBABILITAS_P03 + " REAL, " +
                KEY_PROBABILITAS_P04 + " REAL, " +
                KEY_PROBABILITAS_P05 + " REAL " + ")";

        final String CREATE_PENYAKIT_TABLE = "CREATE TABLE " + TABLE_PENYAKIT + "(" +
                KEY_ID + " TEXT PRIMARY KEY, " +
                KEY_PENYAKIT + " TEXT, " +
                KEY_PROBABILITAS_A + " REAL " + ")";

        final String CREATE_OBAT_TABLE = "CREATE TABLE " + TABLE_OBAT + "(" +
                KEY_ID + " TEXT PRIMARY KEY, " +
                KEY_OBAT + " TEXT, " +
                KEY_JENIS_OBAT + " TEXT, " +
                KEY_ID_PENYAKIT + " TEXT, " +
                "FOREIGN KEY(" + KEY_ID_PENYAKIT + ") REFERENCES " +
                TABLE_PENYAKIT + "(" + KEY_ID + ")" + "ON DELETE CASCADE" +
                ")";

        final String CREATE_CEGAH_TABLE = "CREATE TABLE " + TABLE_CEGAH + "(" +
                KEY_ID + " TEXT PRIMARY KEY, " +
                KEY_MENCEGAH + " TEXT, " +
                KEY_ID_PENYAKIT + " TEXT, " +
                "FOREIGN KEY(" + KEY_ID_PENYAKIT + ") REFERENCES " +
                TABLE_PENYAKIT + "(" + KEY_ID + ")" + "ON DELETE CASCADE" +
                ")";

        final String CREATE_TABLE_GEJALA_PENYAKIT = "CREATE TABLE " + TABLE_GEJALA_PENYAKIT + "(" +
                KEY_ID + " TEXT PRIMARY KEY, " +
                KEY_GEJALA + " TEXT, " +
                KEY_ID_PENYAKIT + " TEXT, " +
                "FOREIGN KEY(" + KEY_ID_PENYAKIT + ") REFERENCES " +
                TABLE_PENYAKIT + "(" + KEY_ID + ")" + "ON DELETE CASCADE" +
                ")";

        db.execSQL(CREATE_GEJALA_TABLE);
        db.execSQL(CREATE_PENYAKIT_TABLE);
        db.execSQL(CREATE_OBAT_TABLE);
        db.execSQL(CREATE_CEGAH_TABLE);
        db.execSQL(CREATE_TABLE_GEJALA_PENYAKIT);
        fillGejala();
        fillPenyakit();
        fillObat();
        fillMencegah();
        fillGejalaPenyakit();
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        this.db = db;

        db.execSQL("DROP TABLE IF EXISTS " + TABLE_GEJALA);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_OBAT);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_PENYAKIT);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_GEJALA_PENYAKIT);
        onCreate(db);
    }

    @Override
    public void onConfigure(SQLiteDatabase db) {
        super.onConfigure(db);
        db.setForeignKeyConstraintsEnabled(true);
    }

    private void fillGejala() {
        GejalaModel q1 = new GejalaModel("G01", "Ikan lemah bergerak lambat", 0.66, 0.67, 0.67, 0.73, 0.63, 0.0);
        addquestion(q1);

        GejalaModel q2 = new GejalaModel("G02", "Bernafas megap-megap di permukaan air", 0.71, 0.72, 0.67, 0.79, 0.70, 0.0);
        addquestion(q2);

        GejalaModel q3 = new GejalaModel("G03", "Warna insang pucat", 0.31, 0.85, 0.80, 0.0, 0.0, 0.0);
        addquestion(q3);

        GejalaModel q4 = new GejalaModel("G04", "Terdapat bercak merah", 0.31, 0.85, 0.80, 0.0, 0.0, 0.0);
        addquestion(q4);

        GejalaModel q5 = new GejalaModel("G05", "Kerusakan pada insang, kulit, sirip", 0.20, 0.76, 0.0, 0.0, 0.0, 0.0);
        addquestion(q5);

        GejalaModel q6 = new GejalaModel("G06", "Lendir berlebihan", 0.21, 0.82, 0.0, 0.0, 0.0, 0.0);
        addquestion(q6);

        GejalaModel q7 = new GejalaModel("G07", "Sirip dan ekor rontok", 0.20, 0.79, 0.0, 0.0, 0.0, 0.0);
        addquestion(q7);

        GejalaModel q8 = new GejalaModel("G08", "Pendarahan perut ikan menjadi kembung (Dropsy)", 0.32, 0.88, 0.80, 0.0, 0.0, 0.0);
        addquestion(q8);

        GejalaModel q9 = new GejalaModel("G09", "Terjadi perubahan warna tubuh", 0.68, 0.88, 0.87, 0.94, 0.0, 0.0);
        addquestion(q9);

        GejalaModel q10 = new GejalaModel("G10", "Adanya borok", 0.25, 0.88, 0.0, 0.0, 0.0, 0.60);
        addquestion(q10);

        GejalaModel q11 = new GejalaModel("G11", "Pendarahan pada kulit", 0.12, 0.0, 1.0, 0.0, 0.0, 0.0);
        addquestion(q11);

        GejalaModel q12 = new GejalaModel("G12", "Sungut patah", 0.12, 0.0, 1.0, 0.0, 0.0, 0.0);
        addquestion(q12);

        GejalaModel q13 = new GejalaModel("G13", "Mata menonjol", 0.12, 0.0, 1.0, 0.0, 0.0, 0.0);
        addquestion(q13);

        GejalaModel q14 = new GejalaModel("G14", "Kehilangan nafsu makan", 0.43, 0.0, 0.80, 0.90, 0.0, 0.0);
        addquestion(q14);

        GejalaModel q15 = new GejalaModel("G15", "Kemampuan berenang menurun", 0.09, 0.0, 0.73, 0.0, 0.0, 0.0);
        addquestion(q15);

        GejalaModel q16 = new GejalaModel("G16", "Ikan sering berada di dasar kolam", 0.10, 0.0, 0.87, 0.0, 0.0, 0.0);
        addquestion(q16);

        GejalaModel q17 = new GejalaModel("G17", "Ikan sering berada dekat saluran pembuangan", 0.11, 0.0, 0.93, 0.0, 0.0, 0.0);
        addquestion(q17);

        GejalaModel q18 = new GejalaModel("G18", "Kulit kasar", 0.10, 0.0, 0.87, 0.0, 0.0, 0.0);
        addquestion(q18);

        GejalaModel q19 = new GejalaModel("G19", "Ekses lendir", 0.10, 0.0, 0.87, 0.0, 0.0, 0.0);
        addquestion(q19);

        GejalaModel q20 = new GejalaModel("G20", "Ikan mati lemas sering ditemukan di dasar kolam", 0.10, 0.0, 0.87, 0.0, 0.0, 0.0);
        addquestion(q20);

        GejalaModel q21 = new GejalaModel("G21", "Terjadinya iritasi", 0.37, 0.0, 0.0, 0.98, 0.0, 0.0);
        addquestion(q21);

        GejalaModel q22 = new GejalaModel("G22", "Ikan menggosokkan tubuhnya ke pinggir kolam", 0.38, 0.0, 0.0, 1.0, 0.0, 0.0);
        addquestion(q22);

        GejalaModel q23 = new GejalaModel("G23", "Ikan terlihat meloncat-loncat ke permukaan air", 0.34, 0.0, 0.0, 0.92, 0.0, 0.0);
        addquestion(q23);

        GejalaModel q24 = new GejalaModel("G24", "Tidak merespon terhadap rangsangan", 0.36, 0.0, 0.0, 0.96, 0.0, 0.0);
        addquestion(q24);

        GejalaModel q25 = new GejalaModel("G25", "Tubuh ditutupi lendir tebal", 0.38, 0.0, 0.0, 1.0, 0.0, 0.0);
        addquestion(q25);

        GejalaModel q26 = new GejalaModel("G26", "Bintik putih terlihat lebih banayk di kepala, kulit, sirip", 0.38, 0.0, 0.0, 1.0, 0.0, 0.0);
        addquestion(q26);

        GejalaModel q27 = new GejalaModel("G27", "Ikan berdiam diri di tepi kolam", 0.36, 0.0, 0.0, 0.96, 0.0, 0.0);
        addquestion(q27);

        GejalaModel q28 = new GejalaModel("G28", "Di kulit terlihat benang halus", 0.20, 0.0, 0.0, 0.0, 0.85, 0.4);
        addquestion(q28);

        GejalaModel q29 = new GejalaModel("G29", "Telur ikan terlihat seperti diselimuti kapur", 0.21, 0.0, 0.0, 0.0, 1.0, 0.0);
        addquestion(q29);

        GejalaModel q30 = new GejalaModel("G30", "Miselia(Kumpulan Hifa) berwarna putih kecoklatan", 0.21, 0.0, 0.0, 0.0, 1.0, 0.0);
        addquestion(q30);

        GejalaModel q31 = new GejalaModel("G31", "Kehilangan sebagian tubuh posteriornya", 0.04, 0.0, 0.0, 0.0, 0.0, 1.0);
        addquestion(q31);
    }

    private void addquestion(GejalaModel gejalaModel) {
        ContentValues cv = new ContentValues();
        cv.put(KEY_ID, gejalaModel.getId());
        cv.put(KEY_GEJALA, gejalaModel.getGejala());
        cv.put(KEY_PROBABILITAS_B, gejalaModel.getProbabilitas_b());
        cv.put(KEY_PROBABILITAS_P01, gejalaModel.getProbabilitas_p01());
        cv.put(KEY_PROBABILITAS_P02, gejalaModel.getProbabilitas_p02());
        cv.put(KEY_PROBABILITAS_P03, gejalaModel.getProbabilitas_p03());
        cv.put(KEY_PROBABILITAS_P04, gejalaModel.getProbabilitas_p04());
        cv.put(KEY_PROBABILITAS_P05, gejalaModel.getProbabilitas_p05());
        db.insert(TABLE_GEJALA, null, cv);
    }

    private void fillPenyakit() {
        PenyakitModel p1 = new PenyakitModel("P01", "Pseudomonas sp.", 0.26);
        addPenyakit(p1);

        PenyakitModel p2 = new PenyakitModel("P02", "Aeromonas hydrophylia", 0.12);
        addPenyakit(p2);

        PenyakitModel p3 = new PenyakitModel("P03", "Ichthyophthirius multifiliis", 0.38);
        addPenyakit(p3);

        PenyakitModel p4 = new PenyakitModel("P04", "Saprolegnia sp.", 0.21);
        addPenyakit(p4);

        PenyakitModel p5 = new PenyakitModel("P05", "Achlya sp.", 0.04);
        addPenyakit(p5);
    }

    private void addPenyakit(PenyakitModel penyakitModel) {
        ContentValues cv = new ContentValues();
        cv.put(KEY_ID, penyakitModel.getId());
        cv.put(KEY_PENYAKIT, penyakitModel.getPenyakit());
        cv.put(KEY_PROBABILITAS_A, penyakitModel.getProbabilitas_a());
        db.insert(TABLE_PENYAKIT, null, cv);
    }

    private void addObat(ObatModel obatModel) {
        ContentValues cv = new ContentValues();
        cv.put(KEY_ID, obatModel.getId());
        cv.put(KEY_OBAT, obatModel.getObat());
        cv.put(KEY_JENIS_OBAT, obatModel.getJenis_pengobatan());
        cv.put(KEY_ID_PENYAKIT, obatModel.getId_penyakit());
        db.insert(TABLE_OBAT, null, cv);
    }

    private void fillObat() {
        ObatModel o1 = new ObatModel("OB01", "Perendaman dalam larutan Kalium Permanganat(PK) sebanyak 10-20 ppm(0,01-0,02 mg/g) selama 30-60 menit", "P01", "disenfektan");
        addObat(o1);

        ObatModel o2 = new ObatModel("OB02", "Perendaman dalam larutan Nitrofuraun sebanyak 5-10 ppm(0,005-0,01 mg/g) selama 12-14 Jam", "P01", "disenfektan");
        addObat(o2);

        ObatModel o3 = new ObatModel("OB03", "Perendaman dalam Oksitetrasikilin sebanyak 5ppm(0,005 mg/g) selama 24 jam", "P01", "disenfektan");
        addObat(o3);

        ObatModel o4 = new ObatModel("OB04", "Campur 1-2 gram Chloromycetion kedalam 1 kg makanan", "P01", "disenfektan");
        addObat(o4);

        ObatModel o5 = new ObatModel("OB05", "Daun babandotan (Menyebarkan daun babandotan segar kedalam kolam secara merata atau dapat diberikan dosis 30kg daun untuk 100 kg ikan selama 5-7 hari)", "P02", "herbal");
        addObat(o5);

        ObatModel o6 = new ObatModel("OB06", "Daun jambu biji(4-5 gram daun jambu biji dicacah halus kemudian dicampur dengan 1 liter air, lalu campur dengan pakan atau dapat dilakukan dengan mencacah 1-2 gram daun jambu biji kemudia dicampur dengan 5 liter air. gunakan air tersebut untuk merendam ikan sakit selama 48 Jam", "P02", "herbal");
        addObat(o6);

        ObatModel o7 = new ObatModel("OB07", "Daun Jombang(Gunakan 0,3 - 0,6 gram daun jombang, cacah hingga halus, lalu campur dengan 1 liter air. Gunakan air tersebut untuk merendam ikan yang sakit)", "P02", "herbal");
        addObat(o7);

        ObatModel o8 = new ObatModel("OB08", "Daun Kelor(Cacah hingga halus 5 gram daun kelor, lalu campur dengan 100 ml air kemudian saring. Hasil air saringan tersebut campurkan dengan air untuk merendam ikan yang sakit)", "P02", "herbal");
        addObat(o8);

        ObatModel o9 = new ObatModel("OB09", "Daun ketapang(Mencacah 60 gram daun ketapang hingga halus, lalu campurkan kedalam 1 liter air. Gunakan air tersebut untuk merendam ikan yang sakit)", "P02", "herbal");
        addObat(o9);

        ObatModel o10 = new ObatModel("OB10", "Perendaman dalam larutan garam dapur pada konsentrasi 500-10.000 mg/liter selama 24 jam, dilakukan pengulangan setiap 2 hari", "P03", "disenfektan");
        addObat(o10);

        ObatModel o11 = new ObatModel("OB11", "Perendaman dalam larutan kalium permangantae (PK) pada dosis 4 mg/liter selama 12 jam dilakukan pengulangan setiap 2 hari", "P03", "disenfektan");
        addObat(o11);

        ObatModel o12 = new ObatModel("OB12", "Daun Pepaya(2 gram daun pepaya dicacah hingga halus kemudia dilarutkan dalam 100 ml air untuk merendam ikan yang sakit selama 1 jam. Batang dan daun pepaya juga dapat digunakan sebagai pakan dengan dosis 15 Kg untuk 100 Kg bobot ikan)", "P03", "herbal");
        addObat(o12);

        ObatModel o13 = new ObatModel("OB13", "Bawang putih(Menghaluskan 25 mg bawang putih kemudian dicampur dengan 1 liter air untuk perendaman ikan sakit)", "P03", "herbal");
        addObat(o13);

        ObatModel o14 = new ObatModel("OB14", "Daun sirih (Mencampurkan 2 gram ekstrak daun sirih kedalam 60 ml air. direndam selama 12 jam)", "P03", "herbal");
        addObat(o14);

        ObatModel o15 = new ObatModel("OB15", "Kalium permangnate(PK) pada dosis 1 gram.100 liter air selama 90 menit", "P04", "disenfektan");
        addObat(o15);

        ObatModel o16 = new ObatModel("OB16", "Garam dapur pada kosentrasi 1-10 gr/liter selama 10-60 menit", "P04", "disenfektan");
        addObat(o16);

        ObatModel o17 = new ObatModel("OB17", "Daun randa nunut(Mencampur 5-10 lembar remasan daun segar dengan air, kemudia peras airnya dan laurtkan kedalam 30 liter air. Gunakan larutan tersebut untuk merendam ikan yang sakit selama 60 menit)", "P04", "herbal");
        addObat(o17);

        ObatModel o18 = new ObatModel("OB18", "Kalium permangnate(PK) pada dosis 1 gram/liter air selama 90 menit", "P05", "disenfektan");
        addObat(o18);

        ObatModel o19 = new ObatModel("OB19", "Garam dapur pada konsentrasi 1-10 gr/liter selama 10-60 menit", "P05", "disenfektan");
        addObat(o19);

        ObatModel o20 = new ObatModel("OB20", "Daun randa nunut(Mencampur 5-10 lembar remasan daun segar dengan air, kemudia peras airnya dan laurtkan kedalam 30 liter air. Gunakan larutan tersebut untuk merendam ikan yang sakit selama 60 menit)", "P05", "herbal");
        addObat(o20);
    }

    private void addMencegah(CegahModel cegahModel) {
        ContentValues cv = new ContentValues();
        cv.put(KEY_ID, cegahModel.getId());
        cv.put(KEY_MENCEGAH, cegahModel.getCara_mencegah());
        cv.put(KEY_ID_PENYAKIT, cegahModel.getId_penyakit());
        db.insert(TABLE_CEGAH, null, cv);
    }

    private void fillMencegah() {
        CegahModel c1 = new CegahModel("M01", "Menjaga kualitas air agar tetap sesuai dengan kebutuhan hidup ikan", "P01");
        addMencegah(c1);

        CegahModel c2 = new CegahModel("M02", "Memperbaiki kualitas air secara keseluruhan atau meningkatkan frekuensi pergantian air baru", "P01");
        addMencegah(c2);

        CegahModel c3 = new CegahModel("M03", "Pencegahan secara dini(benih) melalui vaksinasi anti aeromonas hydrophylia", "P02");
        addMencegah(c3);

        CegahModel c4 = new CegahModel("M04", "Disenfeksi sarana budidaya sebelum dan selama proses pemeliharaan ikan", "P02");
        addMencegah(c4);

        CegahModel c5 = new CegahModel("M05", "Pemberian unnsur immonustimulan secara rutin selama proses pemeliharaan", "P02");
        addMencegah(c5);

        CegahModel c6 = new CegahModel("M06", "Menghindari terjadinya stress", "P02");
        addMencegah(c6);

        CegahModel c7 = new CegahModel("M07", "Memperbaiki kualitas air secara keseluruhan atau meningkatkan frekuensi pergantian air baru", "P02");
        addMencegah(c7);

        CegahModel c8 = new CegahModel("M08", "Pengelolaan ikan secara terpadu(ikan, lingkungan dan patogen)", "P02");
        addMencegah(c8);

        CegahModel c9 = new CegahModel("M09", "Mempertahankan suhu air ≥ 29°C", "P03");
        addMencegah(c9);

        CegahModel c10 = new CegahModel("M10", "Pemberian unsrur immunostimulan secara rutin", "P03");
        addMencegah(c10);

        CegahModel c11 = new CegahModel("M11", "Meningkatkan frekuensi pergantian air", "P03");
        addMencegah(c11);

        CegahModel c12 = new CegahModel("M12", "Pemindahan ikan pada air yang bersih secara berkala", "P03");
        addMencegah(c12);

        CegahModel c13 = new CegahModel("M13", "Hindari stress", "P04");
        addMencegah(c13);

        CegahModel c14 = new CegahModel("M14", "Menaikkan dan mempertahankan suhu air ≥ 29°C atau pergantian air baru yang lebih sering", "P04");
        addMencegah(c14);

        CegahModel c15 = new CegahModel("M15", "Menjaga stamina dan meningkatkan ketahanan tubuh ikan melalui imunostimulisasi", "P04");
        addMencegah(c15);

        CegahModel c16 = new CegahModel("M16", "Hindari stress", "P05");
        addMencegah(c16);

        CegahModel c17 = new CegahModel("M17", "Menaikkan dan mempertahankan suhu air ≥ 29°C atau pergantian air baru yang lebih sering", "P05");
        addMencegah(c17);

        CegahModel c18 = new CegahModel("M18", "Menjaga stamina dan meningkatkan ketahanan tubuh ikan melalui imunostimulisasi", "P05");
        addMencegah(c18);
    }

    private void addGejalaPenyakit(GejalaPenyakitModel gejalaPenyakit) {
        ContentValues cv = new ContentValues();
        cv.put(KEY_ID, gejalaPenyakit.getId());
        cv.put(KEY_GEJALA, gejalaPenyakit.getGejala());
        cv.put(KEY_ID_PENYAKIT, gejalaPenyakit.getId_penyakit());
        db.insert(TABLE_GEJALA_PENYAKIT, null, cv);
    }

    private void fillGejalaPenyakit() {
        GejalaPenyakitModel g1 = new GejalaPenyakitModel("GP1", "Ikan lemah bergerak lambat", "P01");
        addGejalaPenyakit(g1);

        GejalaPenyakitModel g2 = new GejalaPenyakitModel("GP2", "Bernafas megap-megap dipermukaan air", "P01");
        addGejalaPenyakit(g2);

        GejalaPenyakitModel g3 = new GejalaPenyakitModel("GP3", "Warna insang pucat", "P01");
        addGejalaPenyakit(g3);

        GejalaPenyakitModel g4 = new GejalaPenyakitModel("GP4", "Terdapat bercak merah", "P01");
        addGejalaPenyakit(g4);

        GejalaPenyakitModel g5 = new GejalaPenyakitModel("GP5", "Kerusakan pada insang, kulit, sirip", "P01");
        addGejalaPenyakit(g5);

        GejalaPenyakitModel g6 = new GejalaPenyakitModel("GP6", "Lendir berlebihan", "P01");
        addGejalaPenyakit(g6);

        GejalaPenyakitModel g7 = new GejalaPenyakitModel("GP7", "Sirip dan ekor rontok", "P01");
        addGejalaPenyakit(g7);

        GejalaPenyakitModel g8 = new GejalaPenyakitModel("GP8", "Pendarahan perut ikan menjadi kembung(Dropsy)", "P01");
        addGejalaPenyakit(g8);

        GejalaPenyakitModel g9 = new GejalaPenyakitModel("GP9", "Terjadi perubahan warna tubuh", "P01");
        addGejalaPenyakit(g9);

        GejalaPenyakitModel g10 = new GejalaPenyakitModel("GP10", "Adanya borok", "P01");
        addGejalaPenyakit(g10);

        GejalaPenyakitModel g11 = new GejalaPenyakitModel("GP11", "Ikan lemah bergerak lambat", "P02");
        addGejalaPenyakit(g11);

        GejalaPenyakitModel g12 = new GejalaPenyakitModel("GP12", "Bernafas megap-megap di permukaan air", "P02");
        addGejalaPenyakit(g12);

        GejalaPenyakitModel g13 = new GejalaPenyakitModel("GP13", "Warna insang pucat", "P02");
        addGejalaPenyakit(g13);

        GejalaPenyakitModel g14 = new GejalaPenyakitModel("GP14", "Terdapat bercak merah", "P02");
        addGejalaPenyakit(g14);

        GejalaPenyakitModel g15 = new GejalaPenyakitModel("GP15", "Pendaraha perut ikan menjadi kembung (Dropsy)", "P02");
        addGejalaPenyakit(g15);

        GejalaPenyakitModel g16 = new GejalaPenyakitModel("GP16", "Terjadi perubahan warna tubuh", "P02");
        addGejalaPenyakit(g16);

        GejalaPenyakitModel g17 = new GejalaPenyakitModel("GP17", "Pendarahan pada kulit", "P02");
        addGejalaPenyakit(g17);

        GejalaPenyakitModel g18 = new GejalaPenyakitModel("GP18", "Sungut patah", "P02");
        addGejalaPenyakit(g18);

        GejalaPenyakitModel g19 = new GejalaPenyakitModel("GP19", "Mata menonjol", "P02");
        addGejalaPenyakit(g19);

        GejalaPenyakitModel g20 = new GejalaPenyakitModel("GP20", "Kehilangan nafsu makan", "P02");
        addGejalaPenyakit(g20);

        GejalaPenyakitModel g21 = new GejalaPenyakitModel("GP21", "Kemampuan berenang menurun", "P02");
        addGejalaPenyakit(g21);

        GejalaPenyakitModel g22 = new GejalaPenyakitModel("GP22", "Ikan sering berada didasar kolam", "P02");
        addGejalaPenyakit(g22);

        GejalaPenyakitModel g23 = new GejalaPenyakitModel("GP23", "Ikan sering berada dekat saluran pembuangan", "P02");
        addGejalaPenyakit(g23);

        GejalaPenyakitModel g24 = new GejalaPenyakitModel("GP24", "Kulit kasar", "P02");
        addGejalaPenyakit(g24);

        GejalaPenyakitModel g25 = new GejalaPenyakitModel("GP25", "Ekses lendir", "P02");
        addGejalaPenyakit(g25);

        GejalaPenyakitModel g26 = new GejalaPenyakitModel("GP26", "Ikan mati lemas sering ditemukan didasar kolam", "P02");
        addGejalaPenyakit(g26);

        GejalaPenyakitModel g27 = new GejalaPenyakitModel("GP27", "Ikan lemah bergerak lambat", "P03");
        addGejalaPenyakit(g27);

        GejalaPenyakitModel g28 = new GejalaPenyakitModel("GP28", "Bernafas megap-megap dipermukaan air", "P03");
        addGejalaPenyakit(g28);

        GejalaPenyakitModel g29 = new GejalaPenyakitModel("GP29", "Terjadi perubahan warna tubuh", "P03");
        addGejalaPenyakit(g29);

        GejalaPenyakitModel g30 = new GejalaPenyakitModel("GP30", "Kehilangan nafsu makan", "P03");
        addGejalaPenyakit(g30);

        GejalaPenyakitModel g31 = new GejalaPenyakitModel("GP31", "Terjadinya iritasi", "P03");
        addGejalaPenyakit(g31);

        GejalaPenyakitModel g32 = new GejalaPenyakitModel("GP32", "Ikan menggosokkan tubuhnya ke pinggir kolam", "P03");
        addGejalaPenyakit(g32);

        GejalaPenyakitModel g33 = new GejalaPenyakitModel("GP33", "Ikan terlihat meloncat-loncat ke permukaan air", "P03");
        addGejalaPenyakit(g33);

        GejalaPenyakitModel g34 = new GejalaPenyakitModel("GP34", "Tidak merespon terhadap rangsangan", "P03");
        addGejalaPenyakit(g34);

        GejalaPenyakitModel g35 = new GejalaPenyakitModel("GP35", "Tubuh ditutupi ledir tebal", "P03");
        addGejalaPenyakit(g35);

        GejalaPenyakitModel g36 = new GejalaPenyakitModel("GP36", "Bintik putih terlihat lebih banyak di kepala, kulit, sirip", "P03");
        addGejalaPenyakit(g36);

        GejalaPenyakitModel g37 = new GejalaPenyakitModel("GP37", "Ikan berdiam diri ditepi kolam", "P03");
        addGejalaPenyakit(g37);

        GejalaPenyakitModel g38 = new GejalaPenyakitModel("GP38", "Ikan lemah bergerak lambat", "P04");
        addGejalaPenyakit(g38);

        GejalaPenyakitModel g39 = new GejalaPenyakitModel("GP39", "Bernafas megap-megap dipermukaan air", "P04");
        addGejalaPenyakit(g39);

        GejalaPenyakitModel g40 = new GejalaPenyakitModel("GP40", "Di kulit terlihat benang halus", "P04");
        addGejalaPenyakit(g40);

        GejalaPenyakitModel g41 = new GejalaPenyakitModel("GP41", "Telur ikan diselimuti kapur", "P04");
        addGejalaPenyakit(g41);

        GejalaPenyakitModel g42 = new GejalaPenyakitModel("GP42", "Miselia(Kumpulan hifa) berwarna putih kecoklatan", "P04");
        addGejalaPenyakit(g42);

        GejalaPenyakitModel g43 = new GejalaPenyakitModel("GP43", "Adanya borok", "P05");
        addGejalaPenyakit(g43);

        GejalaPenyakitModel g44 = new GejalaPenyakitModel("GP44", "Di kulit terlihat benang halus", "P05");
        addGejalaPenyakit(g44);

        GejalaPenyakitModel g45 = new GejalaPenyakitModel("GP45", "Kehilangan sebagian tubuh posteriornya", "P05");
        addGejalaPenyakit(g45);
    }

    public ArrayList<GejalaModel> getAllQuestion() {
        ArrayList<GejalaModel> questionList = new ArrayList<>();
        db = getReadableDatabase();
        Cursor c = db.rawQuery("SELECT * FROM " + TABLE_GEJALA, null);

        if (c.moveToFirst()) {
            do {
                GejalaModel gejalaModel = new GejalaModel();
                gejalaModel.setId(c.getString(c.getColumnIndex(KEY_ID)));
                gejalaModel.setGejala(c.getString(c.getColumnIndex(KEY_GEJALA)));
                gejalaModel.setProbabilitas_b(c.getDouble(c.getColumnIndex(KEY_PROBABILITAS_B)));
                gejalaModel.setProbabilitas_p01(c.getDouble(c.getColumnIndex(KEY_PROBABILITAS_P01)));
                gejalaModel.setProbabilitas_p02(c.getDouble(c.getColumnIndex(KEY_PROBABILITAS_P02)));
                gejalaModel.setProbabilitas_p03(c.getDouble(c.getColumnIndex(KEY_PROBABILITAS_P03)));
                gejalaModel.setProbabilitas_p04(c.getDouble(c.getColumnIndex(KEY_PROBABILITAS_P04)));
                gejalaModel.setProbabilitas_p05(c.getDouble(c.getColumnIndex(KEY_PROBABILITAS_P05)));
                questionList.add(gejalaModel);
            } while (c.moveToNext());
        }
        c.close();
        return questionList;
    }

    public ArrayList<CegahModel> getCegahbyPenyakit(String idPenyakit) {
        ArrayList<CegahModel> cegahList = new ArrayList<>();
        db = getReadableDatabase();

        String selection = KEY_ID_PENYAKIT + " =? ";
        String[] selectionArgs = new String[]{idPenyakit};

        Cursor c = db.query(TABLE_CEGAH, null, selection, selectionArgs, null, null, null);

        if (c.moveToFirst()) {
            do {
                CegahModel cegah = new CegahModel();
                cegah.setId(c.getString(c.getColumnIndex(KEY_ID)));
                cegah.setCara_mencegah(c.getString(c.getColumnIndex(KEY_MENCEGAH)));
                cegah.setId_penyakit(c.getString(c.getColumnIndex(KEY_ID_PENYAKIT)));
                cegahList.add(cegah);
            } while (c.moveToNext());
        }
        c.close();
        return cegahList;
    }

    public ArrayList<ObatModel> getObatPenyakit(String idPenyakit) {
        ArrayList<ObatModel> obatList = new ArrayList<>();
        db = getReadableDatabase();

        String selection = KEY_ID_PENYAKIT + " =? ";
        String[] selectionArgs = new String[]{idPenyakit};

        Cursor c = db.query(TABLE_OBAT, null, selection, selectionArgs, null, null, null);

        if (c.moveToFirst()) {
            do {
                ObatModel obat = new ObatModel();
                obat.setId(c.getString(c.getColumnIndex(KEY_ID)));
                obat.setObat(c.getString(c.getColumnIndex(KEY_OBAT)));
                obat.setJenis_pengobatan(c.getString(c.getColumnIndex(KEY_JENIS_OBAT)));
                obat.setId_penyakit(c.getString(c.getColumnIndex(KEY_ID_PENYAKIT)));
                obatList.add(obat);
            } while (c.moveToNext());
        }
        c.close();
        return obatList;
    }

    public ArrayList<PenyakitModel> getPenyakitList() {
        ArrayList<PenyakitModel> penyakitList = new ArrayList<>();
        db = getReadableDatabase();
        Cursor c = db.rawQuery("SELECT * FROM " + TABLE_PENYAKIT, null);

        if (c.moveToFirst()) {
            do {
                PenyakitModel penyakitModel = new PenyakitModel();
                penyakitModel.setId(c.getString(c.getColumnIndex(KEY_ID)));
                penyakitModel.setPenyakit(c.getString(c.getColumnIndex(KEY_PENYAKIT)));
                penyakitList.add(penyakitModel);
            } while (c.moveToNext());
        }
        c.close();
        return penyakitList;
    }

    public ArrayList<GejalaPenyakitModel> getGejalaPenyakit(String idPenyakit) {
        ArrayList<GejalaPenyakitModel> gejalaList = new ArrayList<>();
        db = getReadableDatabase();

        String selection = KEY_ID_PENYAKIT + " =? ";
        String[] selectionArgs = new String[]{idPenyakit};

        Cursor c = db.query(TABLE_GEJALA_PENYAKIT, null, selection, selectionArgs, null, null, null);

        if (c.moveToFirst()) {
            do {
                GejalaPenyakitModel gejala = new GejalaPenyakitModel();
                gejala.setId(c.getString(c.getColumnIndex(KEY_ID)));
                gejala.setGejala(c.getString(c.getColumnIndex(KEY_GEJALA)));
                gejala.setId_penyakit(c.getString(c.getColumnIndex(KEY_ID_PENYAKIT)));
                gejalaList.add(gejala);
            } while (c.moveToNext());
        }
        c.close();
        return gejalaList;
    }
}
