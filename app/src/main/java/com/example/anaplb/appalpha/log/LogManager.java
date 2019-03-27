package com.example.anaplb.appalpha.log;

import com.example.anaplb.appalpha.json.JsonManager;

public class LogManager extends JsonManager {
    @Override
    protected String getDiretory() {
        return "logs/";
    }

    @Override
    protected String getJsonFileName() {
        return "log.json";
    }
}
