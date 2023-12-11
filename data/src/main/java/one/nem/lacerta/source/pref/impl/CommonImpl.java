package one.nem.lacerta.source.pref.impl;

import android.content.Context;
import android.content.SharedPreferences;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.inject.Inject;

import dagger.hilt.android.AndroidEntryPoint;
import dagger.hilt.android.qualifiers.ApplicationContext;

import one.nem.lacerta.source.pref.repository.Common;

public class CommonImpl implements Common {

    Context applicationContext;

    @Inject
    public CommonImpl(@ApplicationContext Context applicationContext) {
        this.applicationContext = applicationContext;
    }

    public String getStringValue(String key) {
        SharedPreferences pref = applicationContext.getSharedPreferences("common", Context.MODE_PRIVATE);
        return pref.getString(key, "");
    }

    public void setStringValue(String key, String value) {
        SharedPreferences pref = applicationContext.getSharedPreferences("common", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = pref.edit();
        editor.putString(key, value);
        editor.apply();
    }

    public boolean isExist(String key) {
        SharedPreferences pref = applicationContext.getSharedPreferences("common", Context.MODE_PRIVATE);
        return pref.contains(key);
    }

    public void remove(String key) {
        SharedPreferences pref = applicationContext.getSharedPreferences("common", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = pref.edit();
        editor.remove(key);
        editor.apply();
    }

    public ArrayList<String> getExistKeys() {
        SharedPreferences pref = applicationContext.getSharedPreferences("common", Context.MODE_PRIVATE);
        // キーだけをArrayListに切り出す
        return new ArrayList<>(pref.getAll().keySet());
    }



}
