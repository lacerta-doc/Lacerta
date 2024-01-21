package one.nem.lacerta;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.FragmentManager;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;
import androidx.navigation.ui.NavigationUI;

import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import one.nem.lacerta.model.pref.FeatureSwitchOverride;
import one.nem.lacerta.utils.FeatureSwitch;

import com.google.android.material.bottomnavigation.BottomNavigationView;


import java.io.NotActiveException;

import dagger.hilt.android.AndroidEntryPoint;
import one.nem.lacerta.utils.repository.SharedPrefUtils;

import javax.inject.Inject;

@AndroidEntryPoint
public class MainActivity extends AppCompatActivity {

    @Inject
    SharedPrefUtils sharedPrefUtils;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_nav);

        // Init navigation
        try {
            FragmentManager supportFragmentManager = getSupportFragmentManager();
            NavHostFragment navHostFragment = (NavHostFragment) supportFragmentManager.findFragmentById(R.id.nav_host_fragment);
            assert navHostFragment != null;
            NavController navController = navHostFragment.getNavController();
            NavigationUI.setupWithNavController(bottomNavigationView, navController);
        }
        catch (Exception e) {
            Log.e("Init", "Failed to init navigation");
            Log.e("Init", "Searchable Error code: " + "894b5941-3bc0-46fe-b752-0dbc88be29a8");
            Toast.makeText(this, "Failed to init navigation", Toast.LENGTH_LONG).show();
            finish(); // Exit app
        }

        // Apply feature switch
        if (FeatureSwitch.Meta.canOverrideSwitch) {
            Log.d("FeatureSwitch", "Feature switch override is enabled");
            if (!FeatureSwitch.FeatureMaster.enableDebugMenu) {
                if (sharedPrefUtils.getFeatureSwitchOverride(FeatureSwitchOverride.ENABLE_DEBUG_MENU)) {
                    Log.d("FeatureSwitch", "Debug menu is enabled");
                } else {
                    bottomNavigationView.getMenu().removeItem(one.nem.lacerta.feature.debug.R.id.feature_debug_navigation);
                    Log.d("FeatureSwitch", "Debug menu is disabled");
                }
            } else {
                Log.d("FeatureSwitch", "Debug menu is enabled");
            }
            if (!FeatureSwitch.FeatureMaster.enableSearch) {
                if (sharedPrefUtils.getFeatureSwitchOverride(FeatureSwitchOverride.ENABLE_SEARCH)) {
                    Log.d("FeatureSwitch", "Search is enabled");
                } else {
                    bottomNavigationView.getMenu().removeItem(one.nem.lacerta.feature.search.R.id.feature_search_navigation);
                    Log.d("FeatureSwitch", "Search is disabled");
                }
            } else {
                Log.d("FeatureSwitch", "Search is enabled");
            }
        } else {
            Log.d("FeatureSwitch", "Feature switch override is disabled");
            if (FeatureSwitch.FeatureMaster.enableDebugMenu) {
                Log.d("FeatureSwitch", "Debug menu is enabled");
            } else {
                Log.d("FeatureSwitch", "Debug menu is disabled");
                bottomNavigationView.getMenu().removeItem(one.nem.lacerta.feature.debug.R.id.feature_debug_navigation);
            }
            if (FeatureSwitch.FeatureMaster.enableSearch) {
                Log.d("FeatureSwitch", "Search is enabled");
            } else {
                Log.d("FeatureSwitch", "Search is disabled");
                bottomNavigationView.getMenu().removeItem(one.nem.lacerta.feature.search.R.id.feature_search_navigation);
            }
        }

        // Set navigation bar color
        getWindow().setNavigationBarColor(ContextCompat.getColor(this, one.nem.lacerta.shared.ui.R.color.colorSecondaryContainer));

        // Set status bar color
        getWindow().setStatusBarColor(ContextCompat.getColor(this, one.nem.lacerta.shared.ui.R.color.colorSurface));
    }
}
