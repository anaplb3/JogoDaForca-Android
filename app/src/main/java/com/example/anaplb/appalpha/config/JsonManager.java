package com.example.anaplb.appalpha.config;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageManager;
import android.content.res.AssetManager;
import android.os.Environment;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;

public abstract class JsonManager {

    public JSONObject getJsonOnjectOfArchive(Context appContext){
        AssetManager assetManager = appContext.getAssets();
        InputStream is = null;
        String jsonConf = null;
        JSONObject jsonObg = null;
        try {
            is = assetManager.open(getDiretory()+getJsonFileName());
            int size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();
            jsonConf = new String(buffer);
            jsonObg = new JSONObject(jsonConf);
            Log.i("Json",jsonObg.toString());

        } catch (IOException | JSONException e) {
            e.printStackTrace();
        }

        return jsonObg;
    }

    public void writeJsonObject(Context appContext, JSONObject jsonObject){
        File directory = Environment.getExternalStoragePublicDirectory("AppAlpha/"+getDiretory());
        if(!directory.exists()){
            directory.mkdir();
        }

        File archive = new File(directory+"/"+getJsonFileName());
        Log.i("Json", directory+"/"+getJsonFileName());
        if(!archive.exists()){
            try {
                archive.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
                Log.i("Json","Erro ao salvar arquivo JSON");
            }
        }

        FileWriter writeFile = null;

        try{
            writeFile = new FileWriter(archive);
            //Escreve no arquivo conteudo do Objeto JSON
            writeFile.write(jsonObject.toString());
            writeFile.close();
        }catch (IOException e) {
            e.printStackTrace();
        }

        Log.i("Json", "JSON SALVO COM SUCESSO!");
    }

    public abstract String getDiretory();

    public abstract String getJsonFileName();
}
