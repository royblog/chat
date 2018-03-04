package com.lianluo.chatrebot;

import android.content.Context;
import android.content.SharedPreferences;

import java.util.Map;

/**
 * Created by wangyaoguo on 2018/3/4.
 */

public class Utils {
    public static boolean saveInfo(Context context, String key, String value) {
        SharedPreferences sp = context.getSharedPreferences("data", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();
        editor.putString(key, value);
        editor.commit();
        return true;
    }

    public static String getInfo(Context context, String key) {
        SharedPreferences sp = context.getSharedPreferences("data", Context.MODE_PRIVATE);
        String
    }
}
