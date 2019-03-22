package com.example.anaplb.appalpha.config;

import android.os.Environment;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.io.IOException;

public abstract class JsonManager {
    /** Case need instantiate
    private static JsonManager instance;

    private JsonManager(){}

    public static JsonManager getInstance(){
        if(instance == null){
            instance = new JsonManager();
        }

        return instance;
    }**/

    private JSONObject getJsonOnjectOfArchive(){
        File directory = Environment.getExternalStoragePublicDirectory("AppAlpha/"+getDiretory());
        if(!directory.exists()){
            directory.mkdir();
        }

        File archive = new File("AppAlpha/"+getDiretory()+getJsonFileName());

        if(!archive.exists()){
            JSONObject configObj = new JSONObject();
        }

        JSONObject jsonObg = null;

        try {
            jsonObg = new JSONObject(archive.toString());
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return jsonObg;
    }

    public abstract String getDiretory();

    public abstract String getJsonFileName();
}
