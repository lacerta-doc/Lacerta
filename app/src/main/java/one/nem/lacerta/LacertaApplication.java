package one.nem.lacerta;

import android.app.Application;
import android.util.Log;

import com.google.android.material.color.DynamicColors;

import dagger.hilt.android.HiltAndroidApp;
import one.nem.lacerta.utils.FeatureSwitch;

@HiltAndroidApp
public class LacertaApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();

        if (DynamicColors.isDynamicColorAvailable()) {
            Log.d("DynamicColors", "DynamicColors is available. Applying to activities...");
            if (FeatureSwitch.Meta.disableDynamicColor) {
                Log.d("DynamicColors", "DynamicColors is disabled by FeatureSwitch.");
            } else {
                DynamicColors.applyToActivitiesIfAvailable(this);
            }
        } else {
            Log.d("DynamicColors", "DynamicColors is not available.");
        }

    }

}
