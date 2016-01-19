package com.example.mati.accesobd;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by mati on 11/01/16.
 */
public class SQLiteHelper extends SQLiteOpenHelper {
    //Cadena con la sentencia SQL que permite crear la tabla Clientes
    String cadSQL = "CREATE TABLE Clientes (codigo INTEGER, nombre TEXT, telefono TEXT)";

    public SQLiteHelper(Context contexto, String nombre, SQLiteDatabase.CursorFactory almacen, int version){
        super(contexto, nombre, almacen, version);
    }


    @Override
    public void onCreate(SQLiteDatabase bd) {
        bd.execSQL(cadSQL);
    }


    @Override
    public void onUpgrade(SQLiteDatabase bd, int versionAnterior, int versionNueva) {

        //Eliminamos la version anterior de la tabla
        bd.execSQL("DROP TABLE IF EXISTS Clientes");

        //Creamos la nueva versi�n de la tabla
        bd.execSQL(cadSQL);
    }
}

