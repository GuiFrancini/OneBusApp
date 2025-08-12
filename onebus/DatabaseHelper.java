package com.example.onebus;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.content.ContentValues;
import java.util.List;
import java.util.ArrayList;

public class DatabaseHelper extends SQLiteOpenHelper {

    public DatabaseHelper(Context context) {
        super(context, "OneBus.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE usuarios (id INTEGER PRIMARY KEY AUTOINCREMENT, usuario TEXT, senha TEXT, papel TEXT)");
        db.execSQL("CREATE TABLE linhas (id INTEGER PRIMARY KEY AUTOINCREMENT, numero TEXT, nome TEXT)");
        db.execSQL("CREATE TABLE onibus (id INTEGER PRIMARY KEY AUTOINCREMENT, modelo TEXT, chassi TEXT, prefixo TEXT)");
        db.execSQL("CREATE TABLE operacoes (id INTEGER PRIMARY KEY AUTOINCREMENT, responsavel TEXT, linha TEXT, sentido TEXT, horario TEXT, prefixo TEXT)");

        // Inserção de usuários de exemplo
        db.execSQL("INSERT INTO usuarios (usuario, senha, papel) VALUES ('admin', '123', 'admin')");
        db.execSQL("INSERT INTO usuarios (usuario, senha, papel) VALUES ('gestor', '123', 'gestor')");
        db.execSQL("INSERT INTO usuarios (usuario, senha, papel) VALUES ('funcionario', '123', 'funcionario')");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS usuarios");
        db.execSQL("DROP TABLE IF EXISTS linhas");
        db.execSQL("DROP TABLE IF EXISTS onibus");
        db.execSQL("DROP TABLE IF EXISTS operacoes");
        onCreate(db);
    }

    public String validateUser(String user, String pass) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT papel FROM usuarios WHERE usuario=? AND senha=?", new String[]{user, pass});
        if (cursor.moveToFirst()) {
            return cursor.getString(0); // papel
        }
        return null;
    }

    public boolean inserirUsuario(String usuario, String senha, String papel) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put("usuario", usuario);
        cv.put("senha", senha);
        cv.put("papel", papel);
        long result = db.insert("usuarios", null, cv);
        return result != -1;
    }

    public boolean inserirLinha(String numero, String nome) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put("numero", numero);
        cv.put("nome", nome);
        long result = db.insert("linhas", null, cv);
        return result != -1;
    }

    public boolean inserirOnibus(String modelo, String chassi, String prefixo) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put("modelo", modelo);
        cv.put("chassi", chassi);
        cv.put("prefixo", prefixo);
        long result = db.insert("onibus", null, cv);
        return result != -1;
    }

    public boolean inserirOperacao(String responsavel, String linha, String sentido, String horario, String prefixo) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put("responsavel", responsavel);
        cv.put("linha", linha);
        cv.put("sentido", sentido);
        cv.put("horario", horario);
        cv.put("prefixo", prefixo);
        long result = db.insert("operacoes", null, cv);
        return result != -1;
    }

    public List<String> getTodasLinhas() {
        List<String> lista = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor c = db.rawQuery("SELECT numero, nome FROM linhas", null);
        if (c.moveToFirst()) {
            do {
                lista.add(c.getString(0) + " - " + c.getString(1));
            } while (c.moveToNext());
        }
        c.close();
        return lista;
    }

    public List<String> getTodosOnibus() {
        List<String> lista = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor c = db.rawQuery("SELECT modelo, prefixo FROM onibus", null);
        if (c.moveToFirst()) {
            do {
                lista.add(c.getString(0) + " - " + c.getString(1));
            } while (c.moveToNext());
        }
        c.close();
        return lista;
    }


    public List<String> getOperacoesPorResponsavel(String responsavel) {
        List<String> lista = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor c = db.rawQuery("SELECT linha, sentido, horario, prefixo FROM operacoes WHERE responsavel = ?", new String[]{responsavel});
        if (c.moveToFirst()) {
            do {
                String linha = c.getString(0);
                String sentido = c.getString(1);
                String horario = c.getString(2);
                String prefixo = c.getString(3);

                lista.add("Linha: " + linha + "\nSentido: " + sentido + "\nHorário: " + horario + "\nÔnibus: " + prefixo);
            } while (c.moveToNext());
        }
        c.close();
        return lista;
    }


}
