package com.example.abymani.image_comm;

import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.Toast;

/**
 * Created by abymani on 02/09/2016.
 */
public class Navigation extends MainActivity{
    Button b1;


    Toolbar toolbar;
    DrawerLayout drawerLayout;
    ActionBarDrawerToggle actionBarDrawerToggle;
    NavigationView navigationView;
    android.support.v4.app.FragmentTransaction fragmentTransaction;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
       // navigation();

    }

    public void navigation(){

        Toast.makeText(getApplicationContext(),"In the navigation",Toast.LENGTH_SHORT).show();
        toolbar = (Toolbar)findViewById(R.id.toolbar);
        navigationView= (NavigationView)findViewById(R.id.navigation_view);
        drawerLayout = (DrawerLayout)findViewById(R.id.drawer_layout);
        setSupportActionBar(toolbar);
        actionBarDrawerToggle = new ActionBarDrawerToggle(this,drawerLayout,toolbar,R.string.drawer_open,R.string.drawer_close);

        drawerLayout.setDrawerListener(actionBarDrawerToggle);

        fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.add(R.id.main_content,new Image_COMM());
        fragmentTransaction.commit();
       // getSupportActionBar().setTitle("Image_COMM");
        drawerLayout.closeDrawers();

        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(MenuItem item) {

                switch (item.getItemId()) {
                    case R.id.home:

                        fragmentTransaction = getSupportFragmentManager().beginTransaction();
                        fragmentTransaction.replace(R.id.main_content, new Image_COMM());
                        fragmentTransaction.commit();
                       // getSupportActionBar().setTitle("Image_COMM");
                        item.setChecked(true);
                        drawerLayout.closeDrawers();

                        break;
                    case R.id.message:
                        fragmentTransaction = getSupportFragmentManager().beginTransaction();
                        fragmentTransaction.replace(R.id.main_content, new homefragment());
                        fragmentTransaction.commit();
                        //getSupportActionBar().setTitle("Help");
                        item.setChecked(true);
                        drawerLayout.closeDrawers();
                        break;
                    case R.id.setting:
                        fragmentTransaction = getSupportFragmentManager().beginTransaction();
                        fragmentTransaction.replace(R.id.main_content, new settingfragment());
                        fragmentTransaction.commit();
                      //  getSupportActionBar().setTitle("About");
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

}
