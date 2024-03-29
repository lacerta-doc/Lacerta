package one.nem.lacerta.utils.impl;


import android.content.Context;
import android.content.SharedPreferences;

import javax.inject.Inject;

import dagger.hilt.android.qualifiers.ApplicationContext;
import one.nem.lacerta.model.pref.FeatureSwitchOverride;
import one.nem.lacerta.utils.repository.SharedPrefUtils;

public class SharedPrefUtilsImpl implements SharedPrefUtils{

    private final Context applicationContext;

    @Inject
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

    @Override
    public SharedPreferences getSharedPreferences(String name) {
        return applicationContext.getSharedPreferences(name, Context.MODE_PRIVATE);
    }

    @Override
    public SharedPreferences getSharedPreferences() {
        return applicationContext.getSharedPreferences("common", Context.MODE_PRIVATE); // TODO-rca: 決め打ちやめる?
    }

    @Override
    public boolean getFeatureSwitchOverride(FeatureSwitchOverride featureSwitchOverride) {
        return getSharedPreferences().getBoolean(featureSwitchOverride.getKey(), false);
    }

    @Override
    public void setFeatureSwitchOverride(FeatureSwitchOverride featureSwitchOverride, boolean value) {
        getEditor().putBoolean(featureSwitchOverride.getKey(), value).apply();
    }

    @Override
    public boolean getIsFirstLaunch() {
        return getSharedPreferences().getBoolean("isFirstLaunch", true);
    }

    @Override
    public void setIsFirstLaunch(boolean value) {
        getEditor().putBoolean("isFirstLaunch", value).apply();
    }
}
