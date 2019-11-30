package br.ufpb.dcx.appalpha.control.util;

import android.app.Activity;
import android.view.WindowManager;

public class ScreenUtil {
    private static ScreenUtil instance;

    private ScreenUtil(){    }

    public static synchronized ScreenUtil getInstance(){
        if(instance == null){
            instance = new ScreenUtil();
        }
        return instance;
    }

    public void lockScreenTouch(Activity activity){
        activity.getWindow().setFlags(
                WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE,
                WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);
    }

    public void unlockScreenTouch(Activity activity){
        activity.getWindow().clearFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);
    }
}
