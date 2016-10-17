package com.example.abymani.image_comm;


import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;

public class MainActivity extends AppCompatActivity {
    Toolbar toolbar;
    DrawerLayout drawerLayout;
    ActionBarDrawerToggle actionBarDrawerToggle;
    NavigationView navigationView;
    android.support.v4.app.FragmentTransaction fragmentTransaction;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        System.loadLibrary("opencv_java3");
        navigation();
    }
//Navigational menu
    public void navigation(){

        toolbar = (Toolbar)findViewById(R.id.toolbar);
        navigationView= (NavigationView)findViewById(R.id.navigation_view);
        drawerLayout = (DrawerLayout)findViewById(R.id.drawer_layout);
        setSupportActionBar(toolbar);
        actionBarDrawerToggle = new ActionBarDrawerToggle(this,drawerLayout,toolbar,R.string.drawer_open,R.string.drawer_close);

        drawerLayout.setDrawerListener(actionBarDrawerToggle);

        fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.add(R.id.main_content,new Image_COMM());
        fragmentTransaction.commit();
        getSupportActionBar().setTitle("Image COMM");
        drawerLayout.closeDrawers();

        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(MenuItem item) {

                switch (item.getItemId()) {
                    case R.id.home:

                        fragmentTransaction = getSupportFragmentManager().beginTransaction();
                        fragmentTransaction.replace(R.id.main_content, new Image_COMM());
                        fragmentTransaction.commit();
                        getSupportActionBar().setTitle("Image_COMM");
                        item.setChecked(true);
                        drawerLayout.closeDrawers();

                        break;
                    case R.id.message:
                        fragmentTransaction = getSupportFragmentManager().beginTransaction();
                        fragmentTransaction.replace(R.id.main_content, new homefragment());
                        fragmentTransaction.commit();
                        getSupportActionBar().setTitle("Help");
                        item.setChecked(true);
                        drawerLayout.closeDrawers();
                        break;
                    case R.id.setting:
                        fragmentTransaction = getSupportFragmentManager().beginTransaction();
                        fragmentTransaction.replace(R.id.main_content, new settingfragment());
                        fragmentTransaction.commit();
                        getSupportActionBar().setTitle("About");
                        item.setChecked(true);
                        drawerLayout.closeDrawers();


                }
                return true;
            }
        });
    }

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);

        actionBarDrawerToggle.syncState();
    }

//File sending
    public void file_send(View view) {
        Intent intent = new Intent(this,Selector.class);
        startActivity(intent);

    }
//File receiving
    public void receiveFile(View view) {
        Intent receive=new Intent(this,CaptureRecognizeDecode.class);
        startActivity(receive);
    }
}
