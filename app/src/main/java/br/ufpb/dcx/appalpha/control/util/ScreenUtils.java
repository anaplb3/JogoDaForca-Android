package br.ufpb.dcx.appalpha.control.util;

import android.app.Activity;
import android.view.WindowManager;

public class ScreenUtils {
    private static ScreenUtils instance;

    private ScreenUtils(){    }

    public static synchronized ScreenUtils getInstance(){
        if(instance == null){
            instance = new ScreenUtils();
        }
        return instance;
    }

    public void lockScreenTouch(Activity activity){
        activity.getWindow().setFlags(
                WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE,
                WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);
    }
}
