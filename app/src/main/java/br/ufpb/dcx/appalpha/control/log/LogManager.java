package br.ufpb.dcx.appalpha.control.log;

import android.content.Context;
import android.util.Log;

import br.ufpb.dcx.appalpha.control.json.JsonManager;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.Date;

public class LogManager extends JsonManager {
    private JSONObject jsonObjLog;
    private JSONArray jsonErroArray;
    private int erroCount;
    private Context appContext;

    public LogManager(Context appContext){
        this.appContext = appContext;
        this.jsonObjLog = super.getJsonObjectOfArchive(appContext);
        Log.i("Json-Log", jsonObjLog.toString());
        try {
            this.erroCount = this.jsonObjLog.getInt("erro_count");
            this.jsonErroArray = this.jsonObjLog.getJSONArray("erros");
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected String getDiretory() {
        return "AppAlpha/logs/";
    }

    @Override
    protected String getJsonFileName() {
        return "erro_log.json";
    }

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

    public void saveLogInFile(){
        try {
            this.jsonObjLog.put("erros", this.jsonErroArray);
            super.writeJsonObject(appContext, this.jsonObjLog);
            Log.i("Json - Log", "All logs have been saved : " + this.jsonObjLog.toString());
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    private String getTodayDateAndHour(){
        SimpleDateFormat dateFormatted = new SimpleDateFormat("dd-MM-yyyy-HH:mm:ss");
        Date date = new Date();
        String result = dateFormatted.format(date);
        return result;
    }
}
