package one.nem.lacerta.data.repository;

import android.content.SharedPreferences;

import one.nem.lacerta.data.model.shared_pref.enums.SharedPrefType;

public interface SharedPref {

    // タグのTypoを防ぐ為にEnumで管理して取得させるやつ
    SharedPreferences getSharedPreferencesByTag(SharedPrefType sharedPrefType);
}
