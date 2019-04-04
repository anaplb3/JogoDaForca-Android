package com.example.anaplb.appalpha.log;

import android.content.Context;

import com.example.anaplb.appalpha.json.JsonWriter;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Classe responsável por gerenciar o objeto de log Json
 */
public class LogManagerExtStor extends JsonWriter{
    private JSONObject jsonObjLog;
    private JSONArray jsonErroArray;
    private int erroCount;

    public LogManagerExtStor(Context appContext){
        this.jsonObjLog = super.getJsonObjectOfArchive(appContext);

        try {
            this.erroCount = this.jsonObjLog.getInt("erro_count");
            this.jsonErroArray = this.jsonObjLog.getJSONArray("erros");
        } catch (JSONException e) {
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
        } catch (JSONException e) {
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
        } catch (JSONException e) {
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
