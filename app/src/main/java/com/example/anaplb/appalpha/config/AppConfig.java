package com.example.anaplb.appalpha.config;

import android.content.Context;

import org.json.JSONException;
import org.json.JSONObject;

public class AppConfig extends JsonManager {
    private static AppConfig instance;
    public final static String CASUAL = "casual";
    public final static String CURSIVA = "cursiva";
    public final static String BASTAO = "bastao";
    public final static String IMPRENSA = "imprensa";
    public String CURRENT_LETTER_TYPE = CASUAL;
    private JSONObject jsonObjConfig;

    private AppConfig(Context appContext){
        this.jsonObjConfig = super.getJsonOnjectOfArchive(appContext);
        try {
            this.CURRENT_LETTER_TYPE = (String) this.jsonObjConfig.get("letter_type");
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
    public String getDiretory() {
        return "configs/";
    }

    @Override
    public String getJsonFileName() {
        return "configs.json";
    }

    public void changeLetterType(Context appContext, String type){
        JSONObject newJsonObjConfig = new JSONObject();
        try {
            newJsonObjConfig.put("letter_type", type);
            this.jsonObjConfig = newJsonObjConfig;
            super.writeJsonObject(appContext, this.jsonObjConfig);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        this.CURRENT_LETTER_TYPE = type;
    }
}
