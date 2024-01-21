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
        applyFeatureSwitch(bottomNavigationView, FeatureSwitchOverride.ENABLE_SEARCH, FeatureSwitch.FeatureMaster.enableSearch, one.nem.lacerta.feature.search.R.id.feature_search_navigation);
        applyFeatureSwitch(bottomNavigationView, FeatureSwitchOverride.ENABLE_DEBUG_MENU, FeatureSwitch.FeatureMaster.enableDebugMenu, one.nem.lacerta.feature.debug.R.id.feature_debug_navigation);


        // Set navigation bar color
        getWindow().setNavigationBarColor(ContextCompat.getColor(this, one.nem.lacerta.shared.ui.R.color.colorSecondaryContainer));

        // Set status bar color
        getWindow().setStatusBarColor(ContextCompat.getColor(this, one.nem.lacerta.shared.ui.R.color.colorSurface));
    }

    private void applyFeatureSwitch(BottomNavigationView bottomNavigationView, FeatureSwitchOverride featureSwitchOverride, boolean defaultValue, int menuId) {
        boolean isEnabled = FeatureSwitch.Meta.canOverrideSwitch ? sharedPrefUtils.getFeatureSwitchOverride(featureSwitchOverride) : defaultValue;
        if (!isEnabled) bottomNavigationView.getMenu().removeItem(menuId);
    }
}
