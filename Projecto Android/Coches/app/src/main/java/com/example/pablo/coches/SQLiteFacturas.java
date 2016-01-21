package com.example.pablo.coches;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

/**
 * Created by mati on 11/01/16.
 */
public class SQLiteFacturas extends SQLiteOpenHelper {
    final String cadSQL = "CREATE TABLE Pedidos " +
            "(ID INTEGER PRIMARY KEY, Nombre TEXT, Tiempo INTEGER," +
            " Extras INTEGER, Total INTEGER, Imagen INTEGER," +
            "Seguro INTERGER )";

    public SQLiteFacturas(Context contexto, String nombre, SQLiteDatabase.CursorFactory almacen, int version){
        super(contexto, nombre, almacen, version);
    }



    @Override
    public void onCreate(SQLiteDatabase bd) {
        bd.execSQL(cadSQL);
    }


    @Override
    public void onUpgrade(SQLiteDatabase bd, int versionAnterior, int versionNueva) {

        bd.execSQL("DROP TABLE IF EXISTS Pedidos");

        bd.execSQL(cadSQL);
    }

    public  void comprobarBD(SQLiteFacturas cliBDh){
        SQLiteDatabase bd = cliBDh.getWritableDatabase();

        if(comprobarTabla(bd)) {
           onCreate(bd);
        }


    }
    private static boolean comprobarTabla(SQLiteDatabase bd){
        Cursor cursor = bd.rawQuery("SELECT COUNT(*) FROM sqlite_master WHERE type = 'table' AND name = 'Pedidos' ",null);
        if (!cursor.moveToFirst())
        {
            return true;
        }
        int count = cursor.getInt(0);
        cursor.close();
        return count != 1;
    }

    public void guardarFactura(SQLiteFacturas SQH, Context context,Factura factura){
        SQLiteDatabase bd = SQH.getWritableDatabase();
        int seguro;
        if (factura.getSeguro())
             seguro=1;
        else
            seguro=0;

        if (bd!=null) {
            ContentValues nuevoRegistro = new ContentValues();
            nuevoRegistro.put("Nombre",factura.getNombre());
            nuevoRegistro.put("Tiempo", factura.getTiempo());
            nuevoRegistro.put("Extras", factura.getExtras());
            nuevoRegistro.put("Total", factura.getTotal());
            nuevoRegistro.put("Imagen", factura.getImagenId());
            nuevoRegistro.put("Seguro",seguro);

            bd.insert("Pedidos", null, nuevoRegistro);

        }else{
            Toast.makeText(context, "Error al conectar con la base de datos", Toast.LENGTH_SHORT).show();
        }
        bd.close();
        SQH.close();
    }

    public static Factura[] CargarArray(Context context){
        SQLiteFacturas cliBDh = new SQLiteFacturas(context, "DBAppCoches", null, 1);
        SQLiteDatabase bd = cliBDh.getReadableDatabase();
        Cursor cursor =bd.rawQuery("SELECT * FROM Pedidos", null);
        cursor.moveToFirst();
        Factura[] listaFacturas = new Factura[cursor.getCount()];
        String nombre;
        int id,imagen ,tiempo,extras,total;
        boolean seguro;

        for(int i =0; i<listaFacturas.length;i++){
            id=cursor.getInt(0);
            nombre=cursor.getString(1);
            tiempo=cursor.getInt(2);
            extras=cursor.getInt(3);
            total=cursor.getInt(4);
            imagen=cursor.getInt(5);
            if(cursor.getInt(6)==1)
                seguro = true;
            else
                seguro = false;

            listaFacturas[i]=new Factura(id,nombre,0,tiempo,extras,total,imagen,seguro);

            cursor.moveToNext();
        }
        cursor.close();
        bd.close();
        return listaFacturas;
    }
    public static void borrarRegistro(Context context, int Id){
        SQLiteFacturas cliBDh = new SQLiteFacturas(context, "DBAppCoches", null, 1);
        SQLiteDatabase bd = cliBDh.getWritableDatabase();
        String[] arg = new String[]{String.valueOf(Id)};
        bd.delete("Pedidos","ID =?",arg);
        bd.close();
        cliBDh.close();
        Toast.makeText(context,"Registro borrado",Toast.LENGTH_SHORT).show();

    }
}
