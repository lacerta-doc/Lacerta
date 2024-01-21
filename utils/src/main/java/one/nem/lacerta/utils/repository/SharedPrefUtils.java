package one.nem.lacerta.utils.repository;

import android.content.SharedPreferences;

import one.nem.lacerta.model.pref.FeatureSwitchOverride;

public interface SharedPrefUtils {

    // Shared preferences editorの取得
    SharedPreferences.Editor getEditor();

    // TODO-rca: 名称をenumで管理する？
    SharedPreferences.Editor getEditor(String name);

    SharedPreferences getSharedPreferences(String name);

    SharedPreferences getSharedPreferences();

    boolean getFeatureSwitchOverride(FeatureSwitchOverride featureSwitchOverride);

    void setFeatureSwitchOverride(FeatureSwitchOverride featureSwitchOverride, boolean value);
}
