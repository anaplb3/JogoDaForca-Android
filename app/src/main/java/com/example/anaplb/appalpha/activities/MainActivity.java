package com.example.anaplb.appalpha.activities;

import android.Manifest;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.example.anaplb.appalpha.R;
import com.example.anaplb.appalpha.config.AppConfig;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

    }

    public void indoParaRecordes(View v) {
        Intent it = new Intent(getApplicationContext(), RecordesActivity.class);
        startActivity(it);
    }

    public void indoPraTema(View v) {
        Intent it = new Intent(getApplicationContext(), TemaActivity.class);
        startActivity(it);
    }

    public void saindo(View v) {
        Intent intent = new Intent(Intent.ACTION_MAIN);
        intent.addCategory(Intent.CATEGORY_HOME);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    }

    public void indoParaSobre(View v) {
        Intent it = new Intent(getApplicationContext(), SobreActivity.class);
        startActivity(it);
    }

    public void goToConfigScreen(View view){
        Intent it = new Intent(getApplicationContext(), ConfigActivity.class);
        startActivity(it);
    }
}
