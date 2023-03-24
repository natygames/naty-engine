package com.nativegame.nattyengine.util.storage.preference;

import android.content.Context;
import android.content.SharedPreferences;

import java.util.Map;

/**
 * Created by Oscar Liang on 2022/12/11
 */

public class Preference {

    private final SharedPreferences mPrefs;

    //--------------------------------------------------------
    // Constructors
    //--------------------------------------------------------
    public Preference(Context context, String name) {
        mPrefs = context.getSharedPreferences(name, Context.MODE_PRIVATE);
    }
    //========================================================

    //--------------------------------------------------------
    // Getter and Setter
    //--------------------------------------------------------
    public Map<String, ?> getAll() {
        return mPrefs.getAll();
    }

    public int getInt(String key) {
        return mPrefs.getInt(key, 0);
    }

    public int getInt(String key, int defValue) {
        return mPrefs.getInt(key, defValue);
    }

    public void putInt(String key, int value) {
        mPrefs.edit()
                .putInt(key, value)
                .apply();
    }

    public long getLong(String key) {
        return mPrefs.getLong(key, 0L);
    }

    public long getLong(String key, long defValue) {
        return mPrefs.getLong(key, defValue);
    }

    public void putLong(String key, long value) {
        mPrefs.edit()
                .putLong(key, value)
                .apply();
    }

    public float getFloat(String key) {
        return mPrefs.getFloat(key, 0.0f);
    }

    public float getFloat(String key, float defValue) {
        return mPrefs.getFloat(key, defValue);
    }

    public void putFloat(String key, float value) {
        mPrefs.edit()
                .putFloat(key, value)
                .apply();
    }

    public boolean getBoolean(String key) {
        return mPrefs.getBoolean(key, false);
    }

    public boolean getBoolean(String key, boolean defValue) {
        return mPrefs.getBoolean(key, defValue);
    }

    public void putBoolean(String key, boolean value) {
        mPrefs.edit()
                .putBoolean(key, value)
                .apply();
    }

    public String getString(String key) {
        return mPrefs.getString(key, "");
    }

    public String getString(String key, String defValue) {
        return mPrefs.getString(key, defValue);
    }

    public void putString(String key, String value) {
        mPrefs.edit()
                .putString(key, value)
                .apply();
    }
    //========================================================

    //--------------------------------------------------------
    // Methods
    //--------------------------------------------------------
    public boolean contains(String key) {
        return mPrefs.contains(key);
    }

    public void remove(String key) {
        mPrefs.edit()
                .remove(key)
                .apply();
    }

    public void clear() {
        mPrefs.edit()
                .clear()
                .apply();
    }
    //========================================================

}
