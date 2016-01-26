package com.example.pablo.coches.Objetos;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Pablo on 28/12/2015.
 */
public class Factura2 implements Parcelable{
    private String modelo, precioHoras, tiempo, extras, total;
    private int id;
    private boolean seguro;

    /*public Factura2(String modelo, String precioHoras, String tiempo, String extras, String total, int id, boolean seguro) {
        this.modelo = modelo;
        this.precioHoras = precioHoras;
        this.tiempo = tiempo;
        this.extras = extras;
        this.total = total;
        this.id = id;
        this.seguro = seguro;
    }*/

    public static final Parcelable.Creator<Factura2> CREATOR = new Parcelable.Creator<Factura2>() {
        @Override
        public Factura2 createFromParcel(Parcel source) {
            Factura2 fact = new Factura2();
            fact.modelo = source.readString();
            fact.precioHoras = source.readString();
            fact.tiempo = source.readString();
            fact.extras = source.readString();
            fact.total = source.readString();
            fact.id = source.readInt();


            return fact;
        }

        @Override
        public Factura2[] newArray(int size) {
            return new Factura2[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(modelo);
        dest.writeString(precioHoras);
        dest.writeString(tiempo);
        dest.writeString(extras);
        dest.writeString(total);
        dest.writeInt(id);
    }

    public String getModelo() {
        return modelo;
    }

    public String getPrecioHoras() {
        return precioHoras;
    }

    public String getTiempo() {
        return tiempo;
    }

    public String getExtras() {
        return extras;
    }

    public String getTotal() {
        return total;
    }

    public int getId() {
        return id;
    }

    public boolean getSeguro() {
        return seguro;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public void setSeguro(boolean seguro) {
        this.seguro = seguro;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setTotal(String total) {
        this.total = total;
    }

    public void setExtras(String extras) {
        this.extras = extras;
    }

    public void setTiempo(String tiempo) {
        this.tiempo = tiempo;
    }

    public void setPrecioHoras(String precioHoras) {
        this.precioHoras = precioHoras;
    }
}
