package com.example.pablo.coches;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by Pablo on 30/12/2015.
 */



public class Dibujo extends View {

    public Dibujo(Context context) {
        super(context);
    }

    public Dibujo(Context context, AttributeSet atributos) {
        super(context, atributos);
    }



    protected void onDraw(Canvas canvas) {

        float base= canvas.getWidth();
        float altura = canvas.getHeight();
        float mBase;
        float mAltura;


        canvas.drawColor(Color.LTGRAY);
        Paint paint= new Paint();

        mBase=base/2;
        mAltura=altura/2;

        paint.setStyle(Paint.Style.STROKE);
        paint.setColor(Color.BLACK);
        paint.setStrokeWidth(5);

        canvas.drawCircle(mBase, mAltura / 2, 50, paint);

        canvas.drawLine(mBase - 30, mAltura / 2 + 50, mBase - 60, mAltura / 2 + 150, paint);
        canvas.drawLine(mBase + 30, mAltura / 2 + 50, mBase + 100, mAltura / 2 - 50, paint);
        canvas.drawLine(mBase + 100, mAltura / 2 - 50, mBase + 130, mAltura / 2 - 50, paint);

        canvas.drawLine(mBase + 20, mAltura / 2 + 200, mBase + 20, mAltura + 50, paint);
        canvas.drawLine(mBase - 20, mAltura / 2 + 200, mBase - 20, mAltura + 50, paint);



        Path path = new Path();

        path.moveTo(mBase - 30, mAltura / 2 + 50);
        path.lineTo(mBase + 30, mAltura / 2 + 50);

        path.lineTo(mBase + 30, mAltura / 2 + 150);

        path.lineTo(mBase + 60, mAltura / 2 + 200);
        path.lineTo(mBase - 60, mAltura / 2 + 200);

        path.lineTo(mBase - 30, mAltura / 2 + 150);
        path.close();

        path.moveTo(mBase + 15, mAltura+30);
        path.lineTo(mBase + 15, mAltura +50);
        path.lineTo(mBase + 60, mAltura + 50);
        path.close();

        path.moveTo(mBase - 15, mAltura+30);
        path.lineTo(mBase - 15, mAltura + 50);
        path.lineTo(mBase - 60, mAltura + 50);
        path.close();


        paint.setColor(Color.RED);
        paint.setStyle(Paint.Style.FILL);
        canvas.drawPath(path,paint);




    }

}


