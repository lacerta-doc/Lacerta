package one.nem.lacerta;

import android.app.Application;
import android.util.Log;

import com.google.android.material.color.DynamicColors;

import dagger.hilt.android.HiltAndroidApp;

@HiltAndroidApp
public class LacertaApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();

        if (DynamicColors.isDynamicColorAvailable()) {
            Log.d("DynamicColors", "DynamicColors is available. Applying to activities...");
            DynamicColors.applyToActivitiesIfAvailable(this);
        } else {
            Log.d("DynamicColors", "DynamicColors is not available.");
        }

    }

}
