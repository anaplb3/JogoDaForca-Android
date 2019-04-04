package com.example.anaplb.appalpha.activities;

import android.Manifest;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageManager;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.example.anaplb.appalpha.R;

public class MainActivity extends AppCompatActivity {
    private final int READ_PERMISSION_REQ_CODE = 100;
    private final int WRITE_PERMISSION_REQ_CODE = 101;
    private final String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        //checkSdkVersionToShowConfigMenu(); Usar somente caso limite o app para android +7
        getReadExternalStoragePermission();
        getWriteExternalStoragePermission();

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

    /**
     * Verifica se a permissão para gravar no armazenamento externo está ativa, caso não, pede permissão ao usuário.
     */
    public void getWriteExternalStoragePermission(){
        Log.i(TAG, "Verificando permissão de escrita");
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
            Log.i(TAG, "Permissão de escrita não existe");

            if (ActivityCompat.shouldShowRequestPermissionRationale(this,
                    Manifest.permission.WRITE_EXTERNAL_STORAGE)) {
                Log.i(TAG, "Pedindo permissão WA");

            } else {
                ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, WRITE_PERMISSION_REQ_CODE);
                Log.i(TAG, "Pedindo permissão WB");
            }
        }
        Log.i(TAG, "Permissão " + Manifest.permission.WRITE_EXTERNAL_STORAGE + " agora está " + (ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED?"ativa":"desativa"));
    }

    /**
     * Verifica se a permissão para ler do armazenamento externo está ativa, caso não, pede permissão ao usuário.
     */
    public void getReadExternalStoragePermission(){
        Log.i(TAG, "Verificando permissão de leitura");
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
            Log.i(TAG, "Permissão de leitura não existe");

            if (ActivityCompat.shouldShowRequestPermissionRationale(this,
                    Manifest.permission.READ_EXTERNAL_STORAGE)) {
                Log.i(TAG, "Pedindo permissão RA");

            } else {
                ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, READ_PERMISSION_REQ_CODE);
                Log.i(TAG, "Pedindo permissão RB");
            }
        }

        Log.i(TAG, "Permissão " + Manifest.permission.WRITE_EXTERNAL_STORAGE + " agora está " + (ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED?"ativa":"desativa"));
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch(requestCode){
            case READ_PERMISSION_REQ_CODE:
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {

                    Log.i(TAG, "Permission READ has been granted by user");

                } else {
                    Log.i(TAG, "Permission READ has been denied by user");
                }

            case WRITE_PERMISSION_REQ_CODE:
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    Log.i(TAG, "Permission WRITE has been granted by user");

                } else {
                    Log.i(TAG, "Permission WRITE has been denied by user");
                }
                return;
        }
    }

    /***
     * Verifica a versão do SDK do dispositivo para decidir se será possível permitir a modificação de configurações que só são permitidas a partir da versão 23(Android 7) do SDK.
     **/
    public void checkSdkVersionToShowConfigMenu(){
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            findViewById(R.id.btnConfigs).setVisibility(View.VISIBLE);
        }else{
            findViewById(R.id.btnConfigs).setVisibility(View.GONE);
        }
    }
}
