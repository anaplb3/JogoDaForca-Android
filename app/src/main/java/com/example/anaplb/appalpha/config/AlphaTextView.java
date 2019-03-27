package com.example.anaplb.appalpha.config;

import android.content.Context;
import android.graphics.Typeface;
import android.support.v7.widget.AppCompatTextView;
import android.util.AttributeSet;

public class AlphaTextView extends AppCompatTextView {
    private Context context;
    private String fontFamily;
    private String fontCase;


    public AlphaTextView(Context context) {
        super(context);
        this.context = context;
        init();
    }

    public AlphaTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.context = context;
        init();
    }

    public AlphaTextView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.context = context;
        init();
    }



    private void init() {
        this.fontFamily = AppConfig.getInstance(this.context).getCurrentLetterType();
        this.fontCase = AppConfig.getInstance(this.context).getCurrentLetterCase();

        switch (this.fontFamily) {
            case AppConfig.CASUAL: {
                Typeface tf = Typeface.create("casual", Typeface.NORMAL);
                setTypeface(tf);
                break;
            }

            case AppConfig.CURSIVA: {
                Typeface tf = Typeface.create("cursiva", Typeface.NORMAL);
                setTypeface(tf);
                break;
            }

            case AppConfig.BASTAO: {
                Typeface tf = Typeface.create("sans-serif", Typeface.NORMAL);
                setTypeface(tf);
                setAllCaps(true);
                break;
            }

            case AppConfig.IMPRENSA: {
                Typeface tf = Typeface.create("sans-serif", Typeface.NORMAL);
                setTypeface(tf);
                break;
            }

        }

        switch (this.fontCase){
            case AppConfig.UPPER:{
                if(this.fontFamily != AppConfig.IMPRENSA) {
                    setAllCaps(true);
                }
                break;
            }

            case AppConfig.LOWER:{
                setAllCaps(false);
            }
        }

    }
}