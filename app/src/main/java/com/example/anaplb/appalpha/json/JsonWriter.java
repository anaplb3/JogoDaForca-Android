package com.example.anaplb.appalpha.json;

import android.content.Context;
import android.content.res.AssetManager;
import android.os.Environment;
import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;

/**
 * Classe responsável por gerênciar a leitura e escrita do arquivo de log Json;
 */
public abstract class JsonWriter {
    private final String LOG_TAG = "Json-Writer";

    /**
     * Lê o arquivo JSON do assets e retorna um objeto JSON caso o arquivo da memoria interna não exista, caso contrário ele lê o arquivo da memoria interna e retorna o objeto JSON.
     * @param appContext
     * @return JsonObject
     */
    public JSONObject getJsonObjectOfArchive(Context appContext){
        JSONObject jsonObj = null;
        if(isExternalStorageReadable() && isExternalStorageWritable()){
            File jsonLogFile = getErroLogFile();

            FileReader fr = null;
            try {
                fr = new FileReader(jsonLogFile);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }

            BufferedReader br = new BufferedReader(fr);

            String json = "";
            String line = "";

            try{
                while((line = br.readLine()) != null){
                    json += line;
                }
            } catch(IOException ex){
                ex.printStackTrace();
            }

            if(json.isEmpty()){
                jsonObj = fillUsingAssets(appContext);
            }else{
                try {
                    jsonObj = new JSONObject(json);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

            try {
                br.close();
            } catch (IOException e) {
                e.printStackTrace();
            }

        }

        return jsonObj;
    }

    /**
     * Lẽ o arquivo JSON da pasta assets e retorna um objeto JSON
     * @param appContext
     * @return JsonObject
     */
    public JSONObject fillUsingAssets(Context appContext){
        AssetManager assetManager = appContext.getAssets();
        InputStream is = null;
        String jsonConf = null;
        JSONObject jsonObg = null;
        try {
            Log.i(LOG_TAG,getDiretory()+getJsonFileName());
            is = assetManager.open(getDiretory()+getJsonFileName());
            int size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();
            jsonConf = new String(buffer);
            Log.i(LOG_TAG, "JSON DO ASSETS" + jsonConf);
            jsonObg = new JSONObject(jsonConf);

        } catch (IOException | JSONException e) {
            Log.i(LOG_TAG,"Não foi possivel ler json do assets");
            e.printStackTrace();
        }

        return jsonObg;
    }

    /**
     * Escreve o objeto JSON recebido no parâmetro na memoria interna do dispositivo.
     * @param jsonObject
     */
    public void writeJsonObject(JSONObject jsonObject){
        File jsonLogFile = getErroLogFile();

        FileWriter fw = null;
        try {
            fw = new FileWriter(jsonLogFile);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        BufferedWriter bw = new BufferedWriter(fw);

        try {
            bw.write(jsonObject.toString());
            bw.flush();
            bw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    /**
     * Método abstrato que deve retornar o caminho para a pasta que contém o arquivo JSON de log
     * @return
     */
    protected abstract String getDiretory();

    /**
     * Retorna o nome do arquivo JSON de log
     * @return
     */
    protected abstract String getJsonFileName();

    /**
     * Cria um arquivo do tipo File que contém o arquivo de log JSON
     * @return File com o arquivo de log JSON
     */
    private File getErroLogFile() {
        File file = new File(getDirectoryOfArchive().getPath(), getJsonFileName());
        if (!file.exists()) {
            try {
                file.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return file;
    }

    /**
     * Cria um File contendo o diretório para a pasta que contém o arquivo de log JSON
     * @return File com o diretório para a pasta onde fica o arquivo de log JSON
     */
    private File getDirectoryOfArchive(){
        File file = new File(Environment.getExternalStorageDirectory()+File.separator+getDiretory());
        if (!file.exists()) {
            file.mkdirs();
        }
        return file;
    }

    /**
     *  Checa se a memória externa está disponível para escrita
     */
    private boolean isExternalStorageWritable() {
        String state = Environment.getExternalStorageState();
        if (Environment.MEDIA_MOUNTED.equals(state)) {
            return true;
        }
        return false;
    }

    /**
     * Checa se a memória externa está disponível para leitura
     */
    private boolean isExternalStorageReadable() {
        String state = Environment.getExternalStorageState();
        if (Environment.MEDIA_MOUNTED.equals(state) ||
                Environment.MEDIA_MOUNTED_READ_ONLY.equals(state)) {
            return true;
        }
        return false;
    }
}
