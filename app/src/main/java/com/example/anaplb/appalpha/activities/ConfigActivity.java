package com.example.anaplb.appalpha.activities;

import android.Manifest;
import android.content.pm.PackageManager;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.example.anaplb.appalpha.R;
import com.example.anaplb.appalpha.config.AppConfig;

public class ConfigActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_config);

        AppConfig.getInstance(getApplicationContext()).changeLetterType(getApplicationContext(), AppConfig.BASTAO);
    }

    public void backToMainScreen(View view){
        finish();
    }


}
