package com.nativegame.nattyengine.util.storage.xml;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Oscar Liang on 2022/12/11
 */

public class XMLResultSet {

    private final Map<String, String> mResultSets = new HashMap<>();

    //--------------------------------------------------------
    // Constructors
    //--------------------------------------------------------
    public XMLResultSet() {
    }
    //========================================================

    //--------------------------------------------------------
    // Getter and Setter
    //--------------------------------------------------------
    public void setValue(String name, String value) {
        mResultSets.put(name, value);
    }

    public String getString(String tag) {
        return mResultSets.get(tag);
    }

    public int getInt(String tag) {
        String value = mResultSets.get(tag);
        if (value != null) {
            return Integer.parseInt(value);
        }

        return 0;
    }

    public float getFloat(String tag) {
        String value = mResultSets.get(tag);
        if (value != null) {
            return Float.parseFloat(value);
        }

        return 0;
    }

    public double getDouble(String tag) {
        String value = mResultSets.get(tag);
        if (value != null) {
            return Double.parseDouble(value);
        }

        return 0;
    }

    public boolean getBoolean(String tag) {
        String value = mResultSets.get(tag);
        if (value != null) {
            return Boolean.parseBoolean(value);
        }

        return false;
    }
    //========================================================

}
