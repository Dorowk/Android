package com.example.mati.figurasaleatorias;

import java.util.Random;

/**
 * Created by mati on 3/12/15.
 */
public class RandomUtils {
    private static Random r = new Random();

    /** Devuelve un entero aleatorio en el intervalo [0,range-1]. */
     public static int randomInt(int range) {
     return(r.nextInt(range));
     }

    /** Devuelve un indice aleatorio en el intervalo [0 array.length-1] */
    public static int randomIndex(Object[] array) {
        return(randomInt(array.length));
    }

    /** Devuelve un elemento aleatorio perteneciente a un array
     * se usa genericidad y siempre necesita un valor de retorno.*/
    public static <T> T randomElement(T[] array) {
        return(array[randomIndex(array)]);
    }

    /** devuelve un numero float aleatorio, en el intervalo[0, n) */
    public static float randomFloat(int n) {
        return((float)Math.random()*n);
    }

}
