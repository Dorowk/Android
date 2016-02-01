package com.example.pablo.coches.SQLiteClass;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import com.example.pablo.coches.Objetos.Factura;

/**
 * Created by mati on 11/01/16.
 */
public class SQLiteFacturas extends SQLiteOpenHelper {
    final String cadSQL = "CREATE TABLE Pedidos " +
            "(ID INTEGER PRIMARY KEY, IDCoche INTEGER, IDCliente INTEGER, Tiempo INTEGER," +
            " Extras INTEGER, Total INTEGER,Seguro INTERGER," +
            " FOREIGN KEY(IDCoche) REFERENCES Coches(ID)"+
            " FOREIGN KEY(IDCliente) REFERENCES Clientes(ID)"+
            ")";

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

    public static void recargarBD(Context context){
        SQLiteFacturas SQL = new SQLiteFacturas(context, "DBAppCoches", null, 1);
        SQLiteDatabase bd = SQL.getWritableDatabase();
        SQL.onUpgrade(bd, 0, 1);
    }
    public  void comprobarBD(SQLiteFacturas cliBDh){
        SQLiteDatabase bd = cliBDh.getWritableDatabase();

        if(comprobarTabla(bd)) {
           onCreate(bd);
        }
    }
    private static boolean comprobarTabla(SQLiteDatabase bd){
        Cursor cursor = bd.rawQuery("SELECT COUNT(*) FROM sqlite_master WHERE type = 'table' AND name = 'Pedidos' ", null);
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
            nuevoRegistro.put("IDCoche",factura.getIdCoche());
            nuevoRegistro.put("IDCLiente",factura.getIdCliente());
            nuevoRegistro.put("Tiempo", factura.getTiempo());
            nuevoRegistro.put("Extras", factura.getExtras());
            nuevoRegistro.put("Total", factura.getTotal());
            nuevoRegistro.put("Seguro",seguro);

            bd.insert("Pedidos", null, nuevoRegistro);

        }else{
            Toast.makeText(context, "Error al conectar con la base de datos", Toast.LENGTH_SHORT).show();
        }
        bd.close();
    }

    public static Factura[] CargarArray(Context context, int idCli){
        SQLiteFacturas cliBDh = new SQLiteFacturas(context, "DBAppCoches", null, 1);
        SQLiteDatabase bd = cliBDh.getWritableDatabase();
        Cursor cursor;
        if(idCli==1)
             cursor=bd.rawQuery("SELECT * FROM Pedidos", null);
        else
             cursor =bd.rawQuery("SELECT * FROM Pedidos WHERE IDCliente ='"+idCli+"'", null);

        cursor.moveToFirst();
        Factura[] listaFacturas = new Factura[cursor.getCount()];

        int id,idCoche,idCliente,tiempo,extras,total;
        boolean seguro;

        for(int i =0; i<listaFacturas.length;i++){
            id=cursor.getInt(0);
            idCoche=cursor.getInt(1);
            idCliente=cursor.getInt(2);
            tiempo=cursor.getInt(3);
            extras=cursor.getInt(4);
            total=cursor.getInt(5);

            if(cursor.getInt(6)==1)
                seguro = true;
            else
                seguro = false;

            listaFacturas[i]=new Factura(id,idCoche,idCliente,tiempo,extras,total,seguro);

            cursor.moveToNext();
        }
        cursor.close();
        bd.close();
        cliBDh.close();
        return listaFacturas;
    }
    public static Factura CargarFactura(Context context, int idCli){
        SQLiteFacturas cliBDh = new SQLiteFacturas(context, "DBAppCoches", null, 1);
        SQLiteDatabase bd = cliBDh.getWritableDatabase();
        Cursor cursor=bd.rawQuery("SELECT * FROM Pedidos WHERE ID ='"+idCli+"'", null);

        cursor.moveToFirst();

        int id,idCoche,idCliente,tiempo,extras,total;
        boolean seguro;

        id=cursor.getInt(0);
        idCoche=cursor.getInt(1);
        idCliente=cursor.getInt(2);
        tiempo=cursor.getInt(3);
        extras=cursor.getInt(4);
        total=cursor.getInt(5);

        if(cursor.getInt(6)==1)
            seguro = true;
        else
            seguro = false;

        cursor.close();
        bd.close();
        cliBDh.close();
        return new Factura(id,idCoche,idCliente,tiempo,extras,total,seguro);
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
