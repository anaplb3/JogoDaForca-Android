package com.example.anaplb.appalpha.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.example.anaplb.appalpha.R;
import com.example.anaplb.appalpha.config.AppConfig;

public class ConfigActivity extends AppCompatActivity {
    private AppConfig configurator;
    private RadioGroup rgLetterType;
    private RadioButton rbCasual;
    private RadioButton rbCursiva;
    private RadioButton rbBastao;
    private RadioButton rbImprensa;

    private RadioGroup rgLetterCase;
    private RadioButton rbUpper;
    private RadioButton rbLower;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_config);

        this.rgLetterType = findViewById(R.id.rgLetterType);
        this.rgLetterCase = findViewById(R.id.rgLetterCase);
        this.rbCasual = findViewById(R.id.rb_casual);
        this.rbCursiva = findViewById(R.id.rb_cursiva);
        this.rbBastao = findViewById(R.id.rb_bastao);
        this.rbImprensa = findViewById(R.id.rb_imprensa);
        this.rbUpper = findViewById(R.id.rb_uppercase);
        this.rbLower = findViewById(R.id.rb_lowercase);

        this.configurator = AppConfig.getInstance(getApplicationContext());


    }

    @Override
    protected void onResume() {
        super.onResume();
        loadConfigsInView();
    }

    public void backToMainScreen(View view){
        finish();
    }

    @Override
    protected void onPause() {
        super.onPause();
        pushChanges();
        this.configurator.saveAllChange(getApplicationContext());
    }

    private void pushChanges(){
        String rgSelectedLetterType =((RadioButton)findViewById(this.rgLetterType.getCheckedRadioButtonId())).getText().toString();
        String rgSelectedLetterCase =((RadioButton)findViewById(this.rgLetterCase.getCheckedRadioButtonId())).getText().toString();
        this.configurator.setCurrentLetterType(rgSelectedLetterType);
        this.configurator.setCurrentLetterCase(rgSelectedLetterCase);
    }

    private void loadConfigsInView(){
        Log.i("Json-Config","Entrou em LoadConfigs");
        Log.i("Json-Config","CurrentLetterType: " + this.configurator.getCurrentLetterType());
        Log.i("Json-Config","CurrentLetterCase: " + this.configurator.getCurrentLetterCase());
        switch(this.configurator.getCurrentLetterType()){
            case(AppConfig.CASUAL):
                rgLetterType.check(rbCasual.getId());
                Log.i("Json-Config","CASUAL");
            break;

            case(AppConfig.CURSIVA):
                rgLetterType.check(rbCursiva.getId());

                Log.i("Json-Config","CURSIVA");
            break;

            case(AppConfig.BASTAO):
                rgLetterType.check(rbBastao.getId());

                Log.i("Json-Config","BASTAO");
            break;

            case(AppConfig.IMPRENSA):
                rgLetterType.check(rbImprensa.getId());

                Log.i("Json-Config","IMPRENSA");
            break;

        }

        switch(this.configurator.getCurrentLetterCase()){
            case(AppConfig.UPPER):
                rgLetterCase.check(rbUpper.getId());
                Log.i("Json-Config","UPPER");
            break;

            case(AppConfig.LOWER):
                rgLetterCase.check(rbLower.getId());
                Log.i("Json-Config","LOWER");
            break;

        }
    }
}
