package br.ufpb.dcx.appalpha.view.activities;

import android.content.Intent;
import android.net.Uri;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import br.ufpb.dcx.appalpha.R;

public class SobreActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sobre);

    }

    public void voltandoAoMenu(View v) {
        finish();
    }

    public void redirecionarASite(View view){
        Intent browserintent = new Intent(Intent.ACTION_VIEW, Uri.parse("http://pt.freeimages.com"));
        startActivity(browserintent);
    }
}
