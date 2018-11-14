package com.example.anaplb.appalpha;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class FinalActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_final);

        Intent it = getIntent();
        boolean ganhou = it.getBooleanExtra("ganhou", true);

        TextView txt = findViewById(R.id.textView);

        if(ganhou) {
            txt.setText("Parabéns, você ganhou!");
        } else {
            txt.setText("Poxa, mais sorte na próxima!");
        }
    }

    public void jogarNovamente(View v) {
        Intent it = new Intent(getApplicationContext(), TemaActivity.class);
        startActivity(it);
        finish();
    }

    public void sair(View v) {
        Intent intent = new Intent(Intent.ACTION_MAIN);
        intent.addCategory(Intent.CATEGORY_HOME);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    }
}
