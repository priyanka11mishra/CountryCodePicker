package com.example.myapplication;


import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import androidx.annotation.NonNull;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.hbb20.CountryCodePicker;

public class MainActivity extends Activity {
    BottomNavigationView navigationView;
    CountryCodePicker ccp;
    Button btncpp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ccp=(CountryCodePicker)findViewById(R.id.cpp);
        btncpp=(Button)findViewById(R.id.cpbtn);
        navigationView = findViewById(R.id.botomnav);

        btncpp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(MainActivity.this,LoginActivity.class);
                startActivity(intent);
            }
        });
        openFragment(HomeFragment.newInstance("",""));


    }

    BottomNavigationView.OnNavigationItemSelectedListener navigationItemSelectedListener=new BottomNavigationView.OnNavigationItemSelectedListener() {
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
            switch (menuItem.getItemId()) {
                case R.id.home1:
                    openFragment(HomeFragment.newInstance("", ""));
                    return true;
                case R.id.search:
                    openFragment(SearchFragment.newInstance("", ""));
                    return true;
                case R.id.attachment:
                    openFragment(AttachmentFragment.newInstance("", ""));
                    return true;
            }
            return false;
        }
    };

    public void openFragment(Fragment fragment) {
        FragmentTransaction transaction = getFragmentManager().beginTransaction();
        transaction.replace(R.id.container, fragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }


}
