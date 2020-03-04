package com.example.myapplication;


import android.app.Activity;
import android.os.Bundle;

import com.hbb20.CountryCodePicker;

public class MainActivity extends Activity {
    CountryCodePicker ccp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ccp=(CountryCodePicker)findViewById(R.id.cpp);
    }
}
