package de.bitb.astroskop.utils;

import android.content.Context;
import android.content.SharedPreferences;

import java.util.HashSet;
import java.util.Set;

import javax.inject.Inject;

import de.bornholdtlee.snh.R;

public class SharedPreferencesUtils {

    private final SharedPreferences sharedPreferencesReader;
    private final SharedPreferences.Editor sharedPreferencesWriter;

    @Inject
    public SharedPreferencesUtils(Context context) {
        sharedPreferencesReader = context.getSharedPreferences(context.getString(R.string.app_name), Context.MODE_PRIVATE);
        sharedPreferencesWriter = sharedPreferencesReader.edit();
    }

    public void putString(String key, String value) {
        sharedPreferencesWriter.putString(key, value);
        sharedPreferencesWriter.apply();
    }

    public void putBoolean(String key, boolean value) {
        sharedPreferencesWriter.putBoolean(key, value);
        sharedPreferencesWriter.apply();
    }

    public void putInt(String key, int value) {
        sharedPreferencesWriter.putInt(key, value);
        sharedPreferencesWriter.apply();
    }

    public void putLong(String key, Long value) {
        sharedPreferencesWriter.putLong(key, value);
        sharedPreferencesWriter.apply();
    }

    public void remove(String key) {
        sharedPreferencesWriter.remove(key);
        sharedPreferencesWriter.apply();
    }

    public void removeAll() {
        sharedPreferencesWriter.clear().apply();
    }

    public String getString(String key, String defaultValue) {
        return sharedPreferencesReader.getString(key, defaultValue);
    }

    public boolean getBoolean(String key, boolean defaultValue) {
        return sharedPreferencesReader.getBoolean(key, defaultValue);
    }

    public long getLong(String key, long defaultValue) {
        return sharedPreferencesReader.getLong(key, defaultValue);
    }

    public int getInt(String key, int defaultValue) {
        return sharedPreferencesReader.getInt(key, defaultValue);
    }

    public void putFloat(String key, float value) {
        sharedPreferencesWriter.putFloat(key, value);
        sharedPreferencesWriter.apply();
    }

    public float getFloat(String key, float defaultValue) {
        return sharedPreferencesReader.getFloat(key, defaultValue);
    }

    public void addToKeySet(String key, String setEntry) {
        Set<String> set = getKeySet(key);
        set.add(setEntry);
        sharedPreferencesWriter.putStringSet(key, set);
        sharedPreferencesWriter.apply();
    }

    public HashSet<String> getKeySet(String key) {
        return new HashSet<>(sharedPreferencesReader.getStringSet(key, new HashSet<>()));
    }
}