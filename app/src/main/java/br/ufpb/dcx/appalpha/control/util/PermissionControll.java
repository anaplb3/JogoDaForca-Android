package br.ufpb.dcx.appalpha.control.util;

import android.Manifest;
import android.app.Activity;
import android.content.pm.PackageManager;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

public class PermissionControll {
    public static final int READ_PERMISSION_REQ_CODE = 100;
    public static final int WRITE_PERMISSION_REQ_CODE = 101;
    private Activity activity;
    private final String TAG = "PermissionControll";

    public PermissionControll(Activity activity) {
        this.activity = activity;
    }

    /**
     * Verifica se a permissão para gravar no armazenamento externo está ativa, caso não, pede permissão ao usuário.
     */
    public void getWriteExternalStoragePermission(){
        Log.i(TAG, "Verificando permissão de escrita");
        if (ContextCompat.checkSelfPermission(this.activity, Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
            Log.i(TAG, "Permissão de escrita não existe");

            if (ActivityCompat.shouldShowRequestPermissionRationale(this.activity,
                    Manifest.permission.WRITE_EXTERNAL_STORAGE)) {
                Log.i(TAG, "Pedindo permissão WA");

            } else {
                ActivityCompat.requestPermissions(this.activity, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, WRITE_PERMISSION_REQ_CODE);
                Log.i(TAG, "Pedindo permissão WB");
            }
        }
        Log.i(TAG, "Permissão " + Manifest.permission.WRITE_EXTERNAL_STORAGE + " agora está " + (ContextCompat.checkSelfPermission(this.activity, Manifest.permission.WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED?"ativa":"desativa"));
    }

    /**
     * Verifica se a permissão para ler do armazenamento externo está ativa, caso não, pede permissão ao usuário.
     */
    public void getReadExternalStoragePermission(){
        Log.i(TAG, "Verificando permissão de leitura");
        if (ContextCompat.checkSelfPermission(this.activity, Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
            Log.i(TAG, "Permissão de leitura não existe");

            if (ActivityCompat.shouldShowRequestPermissionRationale(this.activity,
                    Manifest.permission.READ_EXTERNAL_STORAGE)) {
                Log.i(TAG, "Pedindo permissão RA");

            } else {
                ActivityCompat.requestPermissions(this.activity, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, READ_PERMISSION_REQ_CODE);
                Log.i(TAG, "Pedindo permissão RB");
            }
        }

        Log.i(TAG, "Permissão " + Manifest.permission.WRITE_EXTERNAL_STORAGE + " agora está " + (ContextCompat.checkSelfPermission(this.activity, Manifest.permission.WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED?"ativa":"desativa"));
    }


}
