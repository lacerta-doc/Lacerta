package one.nem.lacerta.data.impl;

import android.content.Context;
import android.content.SharedPreferences;

import javax.inject.Inject;

import dagger.hilt.android.qualifiers.ApplicationContext;
import one.nem.lacerta.data.repository.SharedPref;

import one.nem.lacerta.data.model.shared_pref.enums.SharedPrefType;

public class SharedPrefImpl implements SharedPref{

    private final Context applicationContext;

    @Inject
    public SharedPrefImpl(@ApplicationContext Context applicationContext) {
        this.applicationContext = applicationContext;
    }

        @Override
        public SharedPreferences getSharedPreferencesByTag(SharedPrefType sharedPrefType) {
            return applicationContext.getSharedPreferences(sharedPrefType.getTag(), Context.MODE_PRIVATE);
        }
}
