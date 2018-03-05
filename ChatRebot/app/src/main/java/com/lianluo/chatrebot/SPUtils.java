package com.lianluo.chatrebot;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

import java.util.Map;

/**
 * Created by wangyaoguo on 2018/3/5.
 */

public class SPUtils {

    public static final String FILE_NAME= "share_data";

    /**
     * 保存数据
     * @param context
     * @param key
     * @param object
     */
    public static void put(Context context, String key, Object object) {

        SharedPreferences sp = context.getSharedPreferences(FILE_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();

        if (object instanceof String) {
            editor.putString(key, (String)object);
        } else if (object instanceof Integer) {
            editor.putInt(key, (Integer)object);
        } else if (object instanceof Boolean) {
            editor.putBoolean(key, (Boolean)object);
        } else if (object instanceof Float) {
            editor.putFloat(key, (Float)object);
        } else if (object instanceof Long) {
            editor.putLong(key, (Long)object);
        } else  {
            editor.putString(key, object.toString());
        }

        editor.commit();
    }

    /**
     * 获取数据，根据默认值具体类型，调用相应方法
     * @param context
     * @param key
     * @param defaultObject
     * @return
     */
    public static Object get(Context context, String key, Object defaultObject) {
        SharedPreferences sp = context.getSharedPreferences(FILE_NAME, Context.MODE_PRIVATE);

        if (defaultObject instanceof String) {
            return sp.getString(key, (String)defaultObject);
        } else if (defaultObject instanceof Integer) {
            return sp.getInt(key, (Integer)defaultObject);
        } else if (defaultObject instanceof Boolean) {
            return sp.getBoolean(key, (Boolean)defaultObject);
        } else if (defaultObject instanceof Float) {
            return sp.getFloat(key, (Float)defaultObject);
        } else if (defaultObject instanceof Long) {
            return sp.getLong(key, (Long)defaultObject);
        }
        return null;
    }

    /**
     * 移除某个key值对应的值
     * @param context
     * @param key
     */
    public static void remove(Context context, String key) {
        SharedPreferences sp = context.getSharedPreferences(FILE_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();
        editor.remove(key);
        editor.commit();
    }

    /**
     * 清空所有数据
     * @param context
     */
    public static void clear(Context context) {
        SharedPreferences sp = context.getSharedPreferences(FILE_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();
        editor.clear();
        editor.commit();
    }

    /**
     * 查询某个key是否应存在
     * @param context
     * @param key
     * @return
     */
    public static boolean contains(Context context, String key) {
        SharedPreferences sp = context.getSharedPreferences(FILE_NAME, Context.MODE_PRIVATE);
        return sp.contains(key);
    }

    /**
     * 返回所有键值对
     * @param context
     * @return
     */
    public static Map<String, ?> getAll(Context context) {
        SharedPreferences sp = context.getSharedPreferences(FILE_NAME, Context.MODE_PRIVATE);
        return sp.getAll();
    }
}
