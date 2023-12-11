package one.nem.lacerta.source.pref.impl;

import android.content.Context;
import android.content.SharedPreferences;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.inject.Inject;

public class CommonImpl {

    private final AppCompatActivity activity;

    @Inject
    public CommonImpl(AppCompatActivity activity) {
        this.activity = activity;
    }

    public String getStringValue(String key) {
        SharedPreferences pref = activity.getSharedPreferences("common", Context.MODE_PRIVATE);
        return pref.getString(key, "");
    }

    public void setStringValue(String key, String value) {
        SharedPreferences pref = activity.getSharedPreferences("common", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = pref.edit();
        editor.putString(key, value);
        editor.apply();
    }

    public boolean isExist(String key) {
        SharedPreferences pref = activity.getSharedPreferences("common", Context.MODE_PRIVATE);
        return pref.contains(key);
    }

    public void remove(String key) {
        SharedPreferences pref = activity.getSharedPreferences("common", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = pref.edit();
        editor.remove(key);
        editor.apply();
    }

    public ArrayList<String> getExistKeys() {
        SharedPreferences pref = activity.getSharedPreferences("common", Context.MODE_PRIVATE);
        // キーだけをArrayListに切り出す
        return new ArrayList<>(pref.getAll().keySet());
    }



}
