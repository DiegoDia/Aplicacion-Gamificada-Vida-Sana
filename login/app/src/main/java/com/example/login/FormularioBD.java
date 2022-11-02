package com.example.login;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class FormularioBD extends SQLiteOpenHelper {

    private static final String NOMBRE_BD="FormularioBD.db";
    private static final int VERSION_BD=1;
    private static final String TABLA_FORMULARIO="CREATE TABLE FORMULARIO (TELEFONO TEXT PRIMARY KEY, DIRECCION TEXT, REFERENCIA TEXT, MATERIAL TEXT)";

    public FormularioBD(Context context){
        super (context, NOMBRE_BD, null, VERSION_BD);

    }
    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase){
        sqLiteDatabase.execSQL(TABLA_FORMULARIO);

    }
    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i,int i1){
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS "+TABLA_FORMULARIO);
        sqLiteDatabase.execSQL(TABLA_FORMULARIO);
    }
    public  void agregarFomulario(String telefono, String direccion, String referencia, String material){
        SQLiteDatabase bd=getWritableDatabase();
        if (bd!=null){
            bd.execSQL("INSERT INTO FORMULARIO VALUES('"+telefono+"','"+direccion+"','"+referencia+"','"+material+"')");
            bd.close();
        }
    }
}
