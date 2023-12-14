package one.nem.lacerta.utils.impl;


import android.content.Context;
import android.content.SharedPreferences;

import dagger.hilt.android.qualifiers.ApplicationContext;
import one.nem.lacerta.utils.repository.SharedPrefUtils;

public class SharedPrefUtilsImpl implements SharedPrefUtils{

    private final Context applicationContext;

    public SharedPrefUtilsImpl(@ApplicationContext Context applicationContext) {
        this.applicationContext = applicationContext;
    }

    @Override
    public SharedPreferences.Editor getEditor() {
        // Editorの取得
        return applicationContext.getSharedPreferences("common", Context.MODE_PRIVATE).edit(); // TODO-rca: 決め打ちやめる?
    }

    @Override
    public SharedPreferences.Editor getEditor(String name) {
        // Editorの取得
        return applicationContext.getSharedPreferences(name, Context.MODE_PRIVATE).edit();
    }
}
