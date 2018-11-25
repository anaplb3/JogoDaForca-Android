package com.example.anaplb.appalpha;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class ModoActivity extends AppCompatActivity {
    private final int FORCA_ID = 2;
    private final int COMPLETAR_ID = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_modo);


    }

    public void escolhaForca(View v) {
        indoPraTema(FORCA_ID);
    }

    public void escolhaCompletar(View v) {
        indoPraTema(COMPLETAR_ID);
    }

    public void indoPraTema(int id) {
        Intent it = new Intent(getApplicationContext(), TemaActivity.class);
        it.putExtra("id", id);
        startActivity(it);
        finish();
    }
}
