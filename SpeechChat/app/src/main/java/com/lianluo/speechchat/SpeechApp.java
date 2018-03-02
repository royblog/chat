package com.lianluo.speechchat;

import android.app.Application;

import com.iflytek.cloud.SpeechUtility;

/**
 * Created by wangyaoguo on 2018/3/2.
 */

public class SpeechApp extends Application {

    public void onCreate() {
        SpeechUtility.createUtility(SpeechApp.this, "appid=" + "5a976cf6");
        super.onCreate();
    }
}

