package one.nem.lacerta.utils.repository;

import android.content.SharedPreferences;

public interface SharedPrefUtils {

    // Shared preferences editorの取得
    SharedPreferences.Editor getEditor();

    // TODO-rca: 名称をenumで管理する？
    SharedPreferences.Editor getEditor(String name);

    SharedPreferences getSharedPreferences(String name);

    SharedPreferences getSharedPreferences();

}
