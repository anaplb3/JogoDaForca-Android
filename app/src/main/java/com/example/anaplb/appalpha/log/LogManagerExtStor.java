package com.example.anaplb.appalpha.log;

import android.content.Context;
import android.util.Log;

import com.example.anaplb.appalpha.json.JsonWriter;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Classe responsável por gerenciar o objeto de log Json
 */
public class LogManagerExtStor extends JsonWriter{
    private JSONObject jsonObjLog;
    private JSONArray jsonErroArray;
    private int erroCount;
    private final String LOG_TAG = "LogManagerExtStor";

    public LogManagerExtStor(Context appContext){
        try {
            this.jsonObjLog = super.getJsonObjectOfArchive(appContext); //Recupera o Json Object do arquivo ou cria um novo com o arquivo do asset
            this.erroCount = this.jsonObjLog.getInt("erro_count"); //Recupera o contador de erros do jsonObject
            this.jsonErroArray = this.jsonObjLog.getJSONArray("erros"); //Recupera o JsonArray de erros do JsonObejct

        }  catch (IOException e) {
            Log.e(LOG_TAG,"Erro ao recuperar JSON File");
            e.printStackTrace();
        } catch (JSONException e) {
            Log.e(LOG_TAG,"Erro ao criar JSON Object");
            e.printStackTrace();
        }
    }

    /**
     * Incrementa o contador de erros do objeto Json e salva o erro relatado em um novo objeto json para ser adicionado a jsonArray
     * @param word
     * @param letter
     */
    public void addNewErro(String word, String letter){
        JSONObject newJsonObj = new JSONObject();
        try {
            newJsonObj.put("erro_count", ++this.erroCount);
            JSONObject erro = new JSONObject();
            erro.put("erro_id",this.erroCount);
            erro.put("date_and_hour", getTodayDateAndHour());
            erro.put("word", word);
            erro.put("letter", letter);
            this.jsonErroArray.put(erro);
            newJsonObj.put("erros", erro);
            this.jsonObjLog = newJsonObj;
            Log.i(LOG_TAG,"Erro salvo com sucesso no JsonObject!");
        } catch (JSONException e) {
            Log.e(LOG_TAG,"Erro salvar novo erro no JsonObejct!");
            e.printStackTrace();
        }
    }

    /**
     * Salva as modificações do objeto json no arquivo
     */
    public void saveLogInFile(){
        try {
            this.jsonObjLog.put("erros", this.jsonErroArray);
            super.writeJsonObject(this.jsonObjLog);
            Log.i(LOG_TAG,"JsonObject salvo no arquivo log.json!");
        } catch (JSONException e) {
            Log.e(LOG_TAG,"Erro ao adicionar o jsonArray ao JsonObject!");
            e.printStackTrace();
        } catch (IOException e) {
            Log.e(LOG_TAG,"Erro salvar JsonObject no arquivo!");
            e.printStackTrace();
        }
    }

    /**
     * Obtem a data e hora atuais do dispositivo
     * @return String com data e hora atuais
     */
    private String getTodayDateAndHour(){
        SimpleDateFormat dateFormatted = new SimpleDateFormat("dd-MM-yyyy-HH:mm:ss");
        Date date = new Date();
        String result = dateFormatted.format(date);
        return result;
    }

    @Override
    protected String getDiretory() {
        return "AppAlpha"+ File.separator+ "logs" +File.separator;
    }

    @Override
    protected String getJsonFileName() {
        return "erro_log.json";
    }
}
