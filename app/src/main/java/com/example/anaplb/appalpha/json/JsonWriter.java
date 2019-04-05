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
    public JSONObject getJsonObjectOfArchive(Context appContext) throws IOException, JSONException {
        JSONObject jsonObj = null;
        if(isExternalStorageReadable() && isExternalStorageWritable()){
            File jsonLogFile = null;
            FileReader fr = null;
            BufferedReader br = null;
            String json = "";
            String line = "";

            jsonLogFile = getErroLogFile();
            Log.i(LOG_TAG,"Json File recuperado!");

            fr = new FileReader(jsonLogFile);
            Log.i(LOG_TAG,"FileReader com Json File criado!");

            br = new BufferedReader(fr);
            Log.i(LOG_TAG,"BufferedReader com FileReader criado!");

            while((line = br.readLine()) != null){
                json += line;
            }
            Log.i(LOG_TAG,"Arquivo log.json lido e armazenado em buffer String!");

            if(json.isEmpty()){
                jsonObj = fillUsingAssets(appContext);
                Log.i(LOG_TAG,"Lendo Json File do Assets!");
            }else{
                jsonObj = new JSONObject(json);
                Log.i(LOG_TAG,"Criando novo JsonObject com o buffer lido do arquivo log.json!");
            }

            br.close();
            Log.i(LOG_TAG,"BufferReader fechado!");

        }

        return jsonObj;
    }

    /**
     * Lẽ o arquivo JSON da pasta assets e retorna um objeto JSON, é usado na primeira iniciação do aplicativo ou quando não foi possivel achar o arquivo .json namemoria interna do dispositivo
     * @param appContext
     * @return JsonObject
     */
    public JSONObject fillUsingAssets(Context appContext) throws IOException, JSONException {
        AssetManager assetManager = appContext.getAssets();
        InputStream is = assetManager.open(getDiretory()+getJsonFileName());
        int size = is.available();
        byte[] buffer = new byte[size];
        is.read(buffer);
        is.close();
        String jsonConf = new String(buffer);
        JSONObject jsonObg = new JSONObject(jsonConf);

        return jsonObg;
    }

    /**
     * Escreve o objeto JSON recebido no parâmetro na memoria interna do dispositivo.
     * @param jsonObject
     */
    public void writeJsonObject(JSONObject jsonObject) throws IOException {
        File jsonLogFile = getErroLogFile(); //Pega o File log.json
        FileWriter fw = new FileWriter(jsonLogFile); //Cria um file write com o file log.json
        BufferedWriter bw = new BufferedWriter(fw); //Cria um bufferedWrite com o fileWrite
        bw.write(jsonObject.toString()); //Grava o file no arquivo log.json

        bw.flush();
        bw.close();
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
    private File getErroLogFile() throws IOException {
        File file = new File(getDirectoryOfArchive().getPath(), getJsonFileName());
        if (!file.exists()) {
            file.createNewFile();
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
