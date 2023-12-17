package one.nem.lacerta;

import android.app.Application;

import com.google.android.material.color.DynamicColors;

import dagger.hilt.android.HiltAndroidApp;

@HiltAndroidApp
public class LacertaApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();

        // DynamicColorを有効化
        DynamicColors.applyToActivitiesIfAvailable(this);
    }
}
