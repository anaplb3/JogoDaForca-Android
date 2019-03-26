package com.example.anaplb.appalpha.config;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

public class AppConfig extends JsonManager {
    private static AppConfig instance;
    public final static String CASUAL = "Casual";
    public final static String CURSIVA = "Cursiva";
    public final static String BASTAO = "Bastão";
    public final static String IMPRENSA = "Imprensa";
    public final static String UPPER = "Maiúsculas";
    public final static String LOWER = "Minúsculas";
    private String currentLetterType;
    private String currentLetterCase;
    private JSONObject jsonObjConfig;

    private AppConfig(Context appContext){
        this.jsonObjConfig = super.getJsonOnjectOfArchive(appContext);
        try {
            this.currentLetterType = (String) this.jsonObjConfig.get("letter_type");
            this.currentLetterCase = (String) this.jsonObjConfig.get("letter_case");
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public static AppConfig getInstance(Context appContext){
        if(instance == null){
            instance = new AppConfig(appContext);
        }

        return instance;
    }

    @Override
    protected String getDiretory() {
        return "configs/";
    }

    @Override
    protected String getJsonFileName() {
        return "configs.json";
    }

    public String getCurrentLetterType() {
        return currentLetterType;
    }

    public void setCurrentLetterType(String currentLetterType) {
        this.currentLetterType = currentLetterType;
    }

    public String getCurrentLetterCase() {
        return currentLetterCase;
    }

    public void setCurrentLetterCase(String currentLetterCase) {
        this.currentLetterCase = currentLetterCase;
    }

    public void saveAllChange(Context appContext){
        JSONObject newJsonObjConfig = new JSONObject();
        try {
            newJsonObjConfig.put("letter_type", this.currentLetterType);
            newJsonObjConfig.put("letter_case", this.currentLetterCase);
            this.jsonObjConfig = newJsonObjConfig;
            super.writeJsonObject(appContext, this.jsonObjConfig);
            Log.i("Json - AppConfig", "All changes have been saved");
            Toast.makeText(appContext, "Configurações salvas com sucesso!", Toast.LENGTH_LONG).show();
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}
