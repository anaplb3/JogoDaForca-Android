package br.ufpb.dcx.appalpha.control.json;

import android.content.Context;
import android.content.res.AssetManager;
import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public abstract class JsonManager {

    public JSONObject getJsonObjectOfArchive(Context appContext){
        FileInputStream inputStream = null;
        BufferedReader reader = null;
        JSONObject jsonObj = null;

        try {
            inputStream = appContext.openFileInput(getJsonFileName());
            reader = new BufferedReader(new InputStreamReader(inputStream));
            String json = reader.readLine();
            if(json.isEmpty()){
                jsonObj = fillUsingAssets(appContext);
            }else{
                jsonObj = new JSONObject(json);
            }

            Log.i("Json", getDiretory()+getJsonFileName()+" lido.");
            Log.i("Json", "Json Obj encontrado: " + json);
            reader.close();
        } catch (FileNotFoundException e) {
            Log.i("Json", "Json Obj lido do assets");
            jsonObj = fillUsingAssets(appContext);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return  jsonObj;

    }

    public JSONObject fillUsingAssets(Context appContext){
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
            Log.i("Json-Assets", jsonConf);
            jsonObg = new JSONObject(jsonConf);

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

    protected abstract String getDiretory();

    protected abstract String getJsonFileName();
}
