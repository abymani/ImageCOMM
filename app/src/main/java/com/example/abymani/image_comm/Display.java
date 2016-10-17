package com.example.abymani.image_comm;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.Toast;

public class Display extends AppCompatActivity {

    static String[] colors;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent obj=getIntent();
        colors = obj.getStringArrayExtra("color");

     //   toast(String.valueOf(colors.length));
        setContentView(R.layout.activity_display);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

//        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
//        fab.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                        .setAction("Action", null).show();
//            }
//        });





        //DrawColors drawColors= new DrawColors(getApplicationContext());
        //drawColors.setColorArray(colors);
        //drawColors.setint(220);
        //Toast.makeText(getApplicationContext(),"hghghhhg", Toast.LENGTH_SHORT).show();
//        for(int i =0 ; i< colors.length;i++)
//        {
//            toast(colors[i]);
//        }



    }
    //getter func to get colors array
    public  String[] getStringArray(){
        return colors;
    }
    //temporary func for debug purpose
    public void toast(String text) {

        Toast t1 = Toast.makeText(getApplicationContext(), text, Toast.LENGTH_SHORT);
        t1.show();

    }


}
