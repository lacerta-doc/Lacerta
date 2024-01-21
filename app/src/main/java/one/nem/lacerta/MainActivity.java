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
import android.widget.Toast;

import one.nem.lacerta.utils.FeatureSwitch;

import com.google.android.material.bottomnavigation.BottomNavigationView;




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

        // Init navigation
        try {
            FragmentManager supportFragmentManager = getSupportFragmentManager();
            NavHostFragment navHostFragment = (NavHostFragment) supportFragmentManager.findFragmentById(R.id.nav_host_fragment);
            assert navHostFragment != null;
            NavController navController = navHostFragment.getNavController();
            BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_nav);
            NavigationUI.setupWithNavController(bottomNavigationView, navController);
        }
        catch (Exception e) {
            Log.e("Init", "Failed to init navigation");
            Log.e("Init", "Searchable Error code: " + "894b5941-3bc0-46fe-b752-0dbc88be29a8");
            Toast.makeText(this, "Failed to init navigation", Toast.LENGTH_LONG).show();
            finish(); // Exit app
        }

        // Set navigation bar color
        getWindow().setNavigationBarColor(ContextCompat.getColor(this, one.nem.lacerta.shared.ui.R.color.colorSecondaryContainer));

        // Set status bar color
        getWindow().setStatusBarColor(ContextCompat.getColor(this, one.nem.lacerta.shared.ui.R.color.colorSurface));
    }

    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        if (!FeatureSwitch.Meta.canOverrideSwitch) {
            Log.d("FeatureSwitch", "Switch override is disabled");
            if (!FeatureSwitch.FeatureMaster.enableDebugMenu) {
                menu.removeItem(one.nem.lacerta.feature.debug.R.id.feature_debug_navigation);

            }
            if (!FeatureSwitch.FeatureMaster.enableSearch) {
                menu.removeItem(one.nem.lacerta.feature.search.R.id.feature_search_navigation);
            }
        } else {
            Log.d("FeatureSwitch", "Switch override is enabled");
            if (!FeatureSwitch.FeatureMaster.enableDebugMenu && !sharedPrefUtils.getFeatureSwitchOverride(one.nem.lacerta.model.pref.FeatureSwitchOverride.ENABLE_DEBUG_MENU)) {
                menu.removeItem(one.nem.lacerta.feature.debug.R.id.feature_debug_navigation);
            }
            if (!FeatureSwitch.FeatureMaster.enableSearch && !sharedPrefUtils.getFeatureSwitchOverride(one.nem.lacerta.model.pref.FeatureSwitchOverride.ENABLE_SEARCH)) {
                menu.removeItem(one.nem.lacerta.feature.search.R.id.feature_search_navigation);
            }
        }

        return super.onPrepareOptionsMenu(menu);
    }
}
