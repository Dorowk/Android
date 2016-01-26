package com.example.pablo.coches.SQLiteClass;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.pablo.coches.Objetos.Cliente;

/**
 * Created by mati on 25/01/16.
 */
public class SQLiteClientes extends SQLiteOpenHelper {
    String cadSQL = "CREATE TABLE Clientes " +
            "(ID INTEGER PRIMARY KEY, Usuario TEXT,Password TEXT," +
            " Nombre TEXT,Correo TEXT,Telefono INTEGER)";

    public SQLiteClientes(Context contexto, String nombre, SQLiteDatabase.CursorFactory almacen, int version){
        super(contexto, nombre, almacen, version);
    }

    @Override
    public void onCreate(SQLiteDatabase bd) {
        bd.execSQL(cadSQL);
        ContentValues nuevoRegistro = new ContentValues();
        nuevoRegistro.put("Usuario", "Anonimo");
        nuevoRegistro.put("Nombre","Anonimo");
        bd.insert("Clientes", null, nuevoRegistro);

        nuevoRegistro.clear();
        nuevoRegistro.put("Usuario", "Admin");
        nuevoRegistro.put("Password", "admin");
        bd.insert("Clientes", null, nuevoRegistro);
    }

    @Override
    public void onUpgrade(SQLiteDatabase bd, int versionAnterior, int versionNueva) {
        bd.execSQL("DROP TABLE IF EXISTS Clientes");
        onCreate(bd);
    }
    public static void onDelete(Context context){
        SQLiteFacturas cliBDh = new SQLiteFacturas(context, "DBAppCoches", null, 1);
        SQLiteDatabase bd = cliBDh.getWritableDatabase();
        bd.execSQL("DROP TABLE IF EXISTS Clientes");
    }

    public static int comprobarCuenta(Context context, String[] args){
        SQLiteFacturas cliBDh = new SQLiteFacturas(context, "DBAppCoches", null, 1);
        SQLiteDatabase bd = cliBDh.getWritableDatabase();
        Cursor cursor;
        if(args.length==1) {
            cursor = bd.rawQuery("SELECT * FROM Clientes WHERE Usuario = ?", args);
        }else{
            cursor = bd.rawQuery("SELECT * FROM Clientes WHERE Usuario = ? AND Password = ?", args);
        }
        if (cursor.moveToFirst())
            return cursor.getInt(0);
        else
            return 0;
    }

    public void comprobarBD(SQLiteClientes SQL){
        SQLiteDatabase bd = SQL.getWritableDatabase();

        if(comprobarTabla(bd)) {
            onCreate(bd);
        }
        bd.close();
        SQL.close();
    }
    private boolean comprobarTabla(SQLiteDatabase bd){
        Cursor cursor = bd.rawQuery("SELECT COUNT(*) FROM sqlite_master WHERE type = 'table' AND name = 'Clientes' ",null);
        if (!cursor.moveToFirst())
        {
            return true;
        }
        int count = cursor.getInt(0);
        cursor.close();
        return count != 1;
    }

    public void insertarCuenta(SQLiteClientes SQL, String[] args){
        SQLiteDatabase bd = SQL.getWritableDatabase();
        ContentValues nuevoRegistro = new ContentValues();
        nuevoRegistro.put("Usuario", args[0]);
        nuevoRegistro.put("Password",args[1]);
        nuevoRegistro.put("Nombre",args[2]);
        nuevoRegistro.put("Correo",args[3]);
        nuevoRegistro.put("Telefono",Integer.valueOf(args[4]));
        bd.insert("Clientes", null, nuevoRegistro);
    }

}
