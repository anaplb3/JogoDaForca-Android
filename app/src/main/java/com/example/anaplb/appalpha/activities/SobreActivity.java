package com.example.anaplb.appalpha.activities;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.example.anaplb.appalpha.R;

public class SobreActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sobre);

    }

    public void voltandoAoMenu(View v) {

        Intent it = new Intent(getApplicationContext(), MainActivity.class);
        startActivity(it);
        finish();
    }

    public void redirecionarASite(View view){
        Intent browserintent = new Intent(Intent.ACTION_VIEW, Uri.parse("http://pt.freeimages.com"));
        startActivity(browserintent);
    }
}
