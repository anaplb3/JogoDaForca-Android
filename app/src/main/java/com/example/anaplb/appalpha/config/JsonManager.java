package com.example.anaplb.appalpha.config;

import android.content.Context;
import android.content.res.AssetManager;
import android.os.Environment;
import android.provider.ContactsContract;
import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStreamWriter;

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
        FileOutputStream outputStream = null;
        BufferedWriter write = null;

        try {
            outputStream = appContext.openFileOutput(getJsonFileName(), Context.MODE_PRIVATE);
            write = new BufferedWriter(new OutputStreamWriter(outputStream));
            write.write(jsonObject.toString());
            Log.i("Json", "configs.json atualizado.");
            write.flush();
            write.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public abstract String getDiretory();

    public abstract String getJsonFileName();
}
