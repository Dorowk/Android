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
public class SQLiteHelper extends SQLiteOpenHelper {
    String cadSQL = "CREATE TABLE Pedidos " +
            "(ID INTEGER PRIMARY KEY, Nombre TEXT, Tiempo INTEGER," +
            " Extras INTEGER, Total INTEGER, Imagen INTEGER," +
            "Seguro INTERGER )";

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


    static void guardarFactura(Context context,Factura factura){
        final SQLiteHelper SQH = new SQLiteHelper(context, "DBPedidos", null, 1);
        SQLiteDatabase bd = SQH.getWritableDatabase();
        int seguro;
        if (factura.getSeguro())
             seguro=1;
        else
            seguro=0;

        if (bd!=null) {
            ContentValues nuevoRegistro = new ContentValues();
            nuevoRegistro.put("Nombre",factura.getNombre());
            nuevoRegistro.put("Tiempo", Integer.parseInt(factura.getTiempo()));
            nuevoRegistro.put("Extras", Integer.parseInt(factura.getExtras()));
            nuevoRegistro.put("Total", Integer.parseInt(factura.getTotal()));
            nuevoRegistro.put("Imagen", factura.getId());
            nuevoRegistro.put("Seguro",seguro);

            bd.insert("Pedidos", null, nuevoRegistro);
            Toast.makeText(context, "Registro guardado correctamente", Toast.LENGTH_SHORT).show();

        }else{
            Toast.makeText(context, "Error al conectar con la base de datos", Toast.LENGTH_SHORT).show();
        }
        bd.close();
        SQH.close();
    }

    public static Factura[] CargarArray(Context contexts){
        SQLiteHelper cliBDh = new SQLiteHelper(contexts, "DBPedidos", null, 1);
        SQLiteDatabase bd = cliBDh.getWritableDatabase();
        Cursor cursor =bd.rawQuery("SELECT * FROM Pedidos", null);
        cursor.moveToFirst();
        Factura[] listaFacturas = new Factura[cursor.getCount()];
        String nombre,tiempo,extras,total;
        int imagen;
        boolean seguro;

        for(int i =0; i<listaFacturas.length;i++){
            nombre=cursor.getString(1);
            tiempo=Integer.toString(cursor.getInt(2));
            extras=Integer.toString(cursor.getInt(3));
            total=Integer.toString(cursor.getInt(4));
            imagen=cursor.getInt(5);
            if(cursor.getInt(6)==1)
                seguro = true;
            else
                seguro = false;

            listaFacturas[i]=new Factura(nombre,"",tiempo,extras,total,imagen,seguro);
            cursor.moveToNext();
        }
        cursor.close();
        bd.close();
        return listaFacturas;
    }
}
