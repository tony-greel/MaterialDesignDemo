package com.example.rxjavadeno;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import java.util.Map;

public class BaseActivity extends AppCompatActivity {

    private static Toast toast;
    private static SharedPreferences sharedPreferences;
    private static SharedPreferences.Editor editor;

    public static void showToast(Context context , String content){
        if (toast == null){
            toast = Toast.makeText(context,content,Toast.LENGTH_SHORT);
        }else {
            toast.setText(content);
        }
        toast.show();
    }

    /**
     * 缓存数据
     * @param context
     * @param key
     * @param object
     */
    public static void putSharedPreference(Context context , String key , Object object){
        SharedPreferences sharedPreferences = context.getSharedPreferences(context.getPackageName(),Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        if (object instanceof String){
            editor.putString(key , (String)object);

        }else if (object instanceof Integer){
            editor.putInt(key, (Integer) object);

        } else if (object instanceof Boolean) {
            editor.putBoolean(key, (Boolean) object);

        } else if (object instanceof Float) {
            editor.putFloat(key, (Float) object);

        } else if (object instanceof Long) {
            editor.putLong(key, (Long) object);

        } else {
            editor.putString(key, object.toString());
        }
        editor.commit();
    }

    /**
     * 获取缓存数据
     * @param context
     * @param key
     * @param object
     * @return
     */
    public static Object getSharedPreference(Context context , String key , Object object){
        SharedPreferences sharedPreferences = context.getSharedPreferences(context.getPackageName(), Context.MODE_PRIVATE);
        if (object instanceof String) {
            return sharedPreferences.getString(key, (String) object);
        } else if (object instanceof Integer) {
            return sharedPreferences.getInt(key, (Integer) object);
        } else if (object instanceof Boolean) {
            return sharedPreferences.getBoolean(key, (Boolean) object);
        } else if (object instanceof Float) {
            return sharedPreferences.getFloat(key, (Float) object);
        } else if (object instanceof Long) {
            return sharedPreferences.getLong(key, (Long) object);
        }
        return sharedPreferences.getString(key,null);
    }

    /**
     * 移除某个key值已经对应的值
     */
    public static void remove(String key) {
        editor.remove(key);
        editor.commit();
    }

    /**
     * 清除所有数据
     */
    public static void clear() {
        editor.clear();
        editor.commit();
    }

    /**
     * 查询某个key是否存在
     */
    public static Boolean contain(String key) {
        return sharedPreferences.contains(key);
    }

    /**
     * 返回所有的键值对
     */
    public static Map<String, ?> getAll() {
        return sharedPreferences.getAll();
    }
}

