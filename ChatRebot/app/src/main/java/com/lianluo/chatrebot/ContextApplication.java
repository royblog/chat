package com.lianluo.chatrebot;

import android.app.Application;
import android.content.Context;

import com.iflytek.cloud.SpeechConstant;
import com.iflytek.cloud.SpeechUtility;

/**
 * Created by wangyaoguo on 2018/3/5.
 */

public class ContextApplication extends Application {

    private static Context context;

    public void onCreate() {
        super.onCreate();
        ContextApplication.context = getApplicationContext();
        SpeechUtility.createUtility(ContextApplication.this, SpeechConstant.APPID + "=5a976cf6");
    }

    public static Context getAppContext() {
        return ContextApplication.context;
    }
}
