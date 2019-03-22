package com.example.anaplb.appalpha.config;

public class configJson extends JsonManager {
    @Override
    public String getDiretory() {
        return "configs/";
    }

    @Override
    public String getJsonFileName() {
        return "configs.json";
    }
}
