package com.example.anaplb.appalpha.activities;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.anaplb.appalpha.R;

public class FinalActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_final);

        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        Intent it = getIntent();
        boolean ganhou = it.getBooleanExtra("ganhou", true);

        TextView txt = findViewById(R.id.textView);
        ImageView img = findViewById(R.id.imageView9);

        if(ganhou) {
            img.setImageResource(R.drawable.meninafeliz);
            txt.setText(R.string.venceu);
        } else {
            txt.setText(R.string.perdeu);
            img.setImageResource(R.drawable.meninotriste);
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
