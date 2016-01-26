package com.example.pablo.coches.SQLiteClass;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import com.example.pablo.coches.Objetos.Coche;
import com.example.pablo.coches.R;

/**
 * Created by mati on 20/01/16.
 */
public class SQLiteCoches extends SQLiteOpenHelper {
    String cadSQL = "CREATE TABLE Coches " +
            "(ID INTEGER PRIMARY KEY, Nombre TEXT, Marca TEXT," +
            " Precio INT,Imagen INT )";

    public SQLiteCoches(Context contexto, String nombre, SQLiteDatabase.CursorFactory almacen, int version){
         super(contexto, nombre, almacen, version);
        }

    @Override
    public void onCreate(SQLiteDatabase bd) {
        bd.execSQL(cadSQL);
    }

    @Override
    public void onUpgrade(SQLiteDatabase bd, int versionAnterior, int versionNueva) {
        bd.execSQL("DROP TABLE IF EXISTS Coches");

        bd.execSQL(cadSQL);
    }
    public static void onDelete(Context context){
        SQLiteFacturas cliBDh = new SQLiteFacturas(context, "DBAppCoches", null, 1);
        SQLiteDatabase bd = cliBDh.getWritableDatabase();
        bd.execSQL("DROP TABLE IF EXISTS Coches");
    }

    public boolean comprobarBD(Context context){
        SQLiteFacturas cliBDh = new SQLiteFacturas(context, "DBAppCoches", null, 1);
        SQLiteDatabase bd = cliBDh.getWritableDatabase();
        Boolean estado = false;

        if(comprobarTabla(bd)) {
            onCreate(bd);
            SQLiteCoches.insertDatosBD(context);
            estado = true;
        }else if (comprobarDatos(bd)){
            SQLiteCoches.insertDatosBD(context);
            estado = true;
        }
        bd.close();
        cliBDh.close();
        return estado;

    }
    private boolean comprobarTabla(SQLiteDatabase bd){
        Cursor cursor = bd.rawQuery("SELECT COUNT(*) FROM sqlite_master WHERE type = 'table' AND name = 'Coches' ",null);
        if (!cursor.moveToFirst())
        {
            return true;
        }
        int count = cursor.getInt(0);
        cursor.close();
        return count != 1;
    }
    private boolean comprobarDatos(SQLiteDatabase bd){
        Cursor cursor = bd.rawQuery("SELECT COUNT(*) FROM Coches",null);
        cursor.moveToFirst();
        boolean aux = cursor.getInt(0)==0;
        cursor.close();
        return aux;
    }

    public static void insertDatosBD(Context context){
        Coche[] listaCoches = new Coche[]{
                new Coche("Megane","Seat",20, R.drawable.coche1),
                new Coche("X-11","Ferrari",100,R.drawable.coche2),
                new Coche("Leon","Seat",30,R.drawable.coche3),
                new Coche("Fiesta","Ford",40,R.drawable.coche4),
                new Coche("600","Ford",5,R.drawable.coche5),
        };
        final SQLiteFacturas SQH = new SQLiteFacturas(context, "DBAppCoches", null, 1);
        SQLiteDatabase bd = SQH.getWritableDatabase();

        if (bd!=null) {
            for(int i=0;i<listaCoches.length;i++) {
                ContentValues nuevoRegistro = new ContentValues();
                nuevoRegistro.put("Nombre", listaCoches[i].getNombre());
                nuevoRegistro.put("Marca", listaCoches[i].getMarca() );
                nuevoRegistro.put("Precio", listaCoches[i].getPrecio());
                nuevoRegistro.put("Imagen", listaCoches[i].getIdImagen());
                bd.insert("Coches", null, nuevoRegistro);

            }

        }else{
            Toast.makeText(context, "Error al conectar con la base de datos", Toast.LENGTH_SHORT).show();
        }
        bd.close();
        SQH.close();
    }

    public static Coche[] CargarArray(Context context){
        SQLiteFacturas cliBDh = new SQLiteFacturas(context, "DBAppCoches", null, 1);
        SQLiteDatabase bd = cliBDh.getWritableDatabase();
        Coche[] listaCoche=null;
        if (bd!=null) {
        Cursor cursor =bd.rawQuery("SELECT * FROM Coches", null);
        cursor.moveToFirst();
        listaCoche = new Coche[cursor.getCount()];
        String nombre, marca;
        int id,idimagen,precio;

        for(int i =0; i<listaCoche.length;i++){
            id = cursor.getInt(0);
            nombre=cursor.getString(1);
            marca=cursor.getString(2);
            precio=cursor.getInt(3);
            idimagen=cursor.getInt(4);

            listaCoche[i]=new Coche(id,nombre,marca,precio,idimagen);

            cursor.moveToNext();
        }
            cursor.close();
        }else {
            Toast.makeText(context, "Error al conectar con la base de datos", Toast.LENGTH_SHORT).show();
        }

        bd.close();
        return listaCoche;
    }

    public static Coche cargarCoche(Context context,int idCoche){

        String nombre, marca;
        int id,idimagen,precio;
        SQLiteFacturas cliBDh = new SQLiteFacturas(context, "DBAppCoches", null, 1);
        SQLiteDatabase bd = cliBDh.getWritableDatabase();
        Cursor cursor =bd.rawQuery("SELECT * FROM Coches WHERE ID = "+idCoche, null);
        cursor.moveToFirst();
        id = cursor.getInt(0);
        nombre=cursor.getString(1);
        marca=cursor.getString(2);
        precio=cursor.getInt(3);
        idimagen=cursor.getInt(4);
        return new Coche(id,nombre,marca,precio,idimagen);
    }
}
