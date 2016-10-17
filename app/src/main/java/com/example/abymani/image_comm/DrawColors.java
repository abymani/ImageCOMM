package com.example.abymani.image_comm;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Handler;
import android.os.Looper;
import android.util.AttributeSet;
import android.view.View;
import android.widget.Toast;

/**
 * Created by abymani on 22/09/2016.
 */
public class DrawColors extends View {
    int index = 0;
    int w, h;


    String[] colorArray= Display.colors;
    boolean bool=false;
    boolean boolforstart=true;
    int check=0;
    boolean boolforend=false;
    boolean firstImage=true;
    final Handler handler = new Handler(Looper.getMainLooper());

    Runnable changeColor = new Runnable(){
        public void run(){
            //canvas.drawRect(0, 0, w / 2 - 10, h / 2 - 10, paints1);
            // invalidate(); //will trigger the onDraw
            bool=true;
            if(index<=colorArray.length-1) {

                handler.postDelayed(this, 1500);
                postInvalidate();

            }
            else{
                bool=false;
                boolforend=true;
                // boolforstart=true;
                invalidate();

                Toast.makeText(getContext(),"File sended successfully " +String.valueOf(check),Toast.LENGTH_SHORT).show();
//                    handler.postDelayed(new Runnable() {
//                        @Override
//                        public void run() {
//
//                          //  Intent intent= new Intent(,MainActivity.class);
//
//
//                        }
//                    },2000);
            }
        }
    };


    private final Paint paint = new Paint();
    private final Paint paint1 = new Paint();
    private final Paint paint2 = new Paint();
    private final Paint paint3 = new Paint();
    private final Paint white=new Paint();
    private final Paint black=new Paint();
    private final Paint Red=new Paint();
    int redi= -2018543;



    public DrawColors(Context context) {
        super(context);
        white.setARGB(255, 255, 255, 255);
    }

    public DrawColors(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public DrawColors(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }


    @Override
    protected void onDraw(final Canvas canvas) {
        super.onDraw(canvas);

        check++;
        if(bool){
//            canvas.drawRect(0, 0, w / 2 - 10, h / 2 - 10, paint);
//            canvas.drawRect(w / 2 + 10, 0, w, h / 2 - 10, paint1);
//            canvas.drawRect(0, h / 2 + 10, w / 2 - 10, h, paint2);
//            canvas.drawRect(w / 2 + 10, h / 2 + 10, w, h, paint3);

            firstImage=false;
            setPaintColors();
            canvas.drawCircle(w/4,h/4,150,Red);
            canvas.drawCircle(w/4,3*h/4,150,Red);
            canvas.drawCircle(3*w/4,h/4,150,Red);
            canvas.drawCircle(3*w/4,3*h/4,150,Red);


            canvas.drawCircle(w/4,h/4,100,paint);
            canvas.drawCircle(w/4,3*h/4,100,paint1);
            canvas.drawCircle(3*w/4,h/4,100,paint2);
            canvas.drawCircle(3*w/4,3*h/4,100,paint3);
        }

        if(boolforend){
          //  canvas.drawRect(0,0,w,h,white);

            canvas.drawCircle(w/2,h/2,200,Red);
        }
        if(boolforstart) {
            //toast("start");
            // canvas.drawRect(0,0,w,h,white);
            // toast(String.valueOf(colorArray.length));

            w = canvas.getWidth();
            h = canvas.getHeight();
//            white.setARGB(255, 180, 173, 255);
//            black.setARGB(255,0,0,0);
            Red.setARGB(255,Color.red(redi),Color.blue(redi),Color.blue(redi));

            handler.postDelayed(changeColor, 1500);




        }

        if(firstImage) {
            boolforstart = false;
            canvas.drawCircle(w/2,h/2,200,Red);
        }


    }
    public void toast(String text) {

        Toast t1 = Toast.makeText(getContext(), text, Toast.LENGTH_SHORT);
        t1.show();
    }

    public void setColorArray(String[] col){
        this.colorArray=col;
    }
    public void setPaintColors(){

        paint.setColor(Color.parseColor(colorArray[index]));
        paint1.setColor(Color.parseColor(colorArray[index + 1]));
        paint2.setColor(Color.parseColor(colorArray[index + 2]));
        paint3.setColor(Color.parseColor(colorArray[index + 3]));


       // toast(String.valueOf(index));
        index=index+4;



    }

}