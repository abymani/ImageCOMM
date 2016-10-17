package com.example.abymani.image_comm;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

public class RecieveFile extends AppCompatActivity {
    Toolbar toolbar;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recieve_file);
        toolbar = (Toolbar)findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Image COMM");


    }

    public void boundry(View view) {
        Intent intent = new Intent(this , Live_camera.class);
        startActivity(intent);
    }

    public void take_picture(View view) {
        Intent intents = new Intent(this, CannyEdge.class);
        startActivity(intents);
    }

    public void startreceiving(View view) {
        Intent intent=new Intent(this,CaptureRecognizeDecode.class);
        startActivity(intent);
    }
}