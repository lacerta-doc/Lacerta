package one.nem.lacerta;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.navigation.NavController;
import androidx.navigation.NavOptions;
import androidx.navigation.fragment.NavHostFragment;
import androidx.navigation.ui.NavigationUI;

import android.app.ActivityOptions;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import one.nem.lacerta.component.scanner.ScannerManagerActivity;
import one.nem.lacerta.model.FragmentNavigation;
import one.nem.lacerta.model.pref.FeatureSwitchOverride;
import one.nem.lacerta.utils.FeatureSwitch;

import com.google.android.material.appbar.AppBarLayout;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;


import java.io.NotActiveException;

import dagger.hilt.android.AndroidEntryPoint;
import one.nem.lacerta.utils.repository.SharedPrefUtils;

import javax.inject.Inject;

@AndroidEntryPoint
public class MainActivity extends AppCompatActivity implements FragmentNavigation {

    @Inject
    SharedPrefUtils sharedPrefUtils;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_nav);

        // Initialize app
        if (sharedPrefUtils.getIsFirstLaunch()) initializeApp();

        // Init navigation
        try {
            FragmentManager supportFragmentManager = getSupportFragmentManager();
            NavHostFragment navHostFragment = (NavHostFragment) supportFragmentManager.findFragmentById(R.id.nav_host_fragment);
            assert navHostFragment != null;
            NavController navController = navHostFragment.getNavController();
            NavigationUI.setupWithNavController(bottomNavigationView, navController);

            bottomNavigationView.setOnItemSelectedListener(item -> {
                NavOptions navOptions = new NavOptions.Builder()
                        .setLaunchSingleTop(true)
                        .setEnterAnim(androidx.navigation.ui.R.anim.nav_default_enter_anim)
                        .setExitAnim(androidx.navigation.ui.R.anim.nav_default_exit_anim)
                        .setPopEnterAnim(androidx.navigation.ui.R.anim.nav_default_pop_enter_anim)
                        .setPopExitAnim(androidx.navigation.ui.R.anim.nav_default_pop_exit_anim)
                        .build();

                navController.navigate(item.getItemId(), null, navOptions);
                return true;
            });
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

        // Fab
        findViewById(R.id.scanFab).setOnClickListener(v -> {
            Toast.makeText(this, "Scan", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(this.getApplicationContext(), ScannerManagerActivity.class);
//            startActivity(intent);
            startActivity(intent, ActivityOptions.makeCustomAnimation(this, 0, 0).toBundle());
        });

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == 1) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                Toast.makeText(this, "Permission granted", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(this, "Permission denied", Toast.LENGTH_SHORT).show();
                Toast.makeText(this, "カメラの権限は必須です.", Toast.LENGTH_LONG).show();
                finish(); // Exit app
            }
        }
    }

    private void initializeApp() {
        Log.d("Init", "Initializing app");
        // Set feature switch override to default value
        sharedPrefUtils.setFeatureSwitchOverride(FeatureSwitchOverride.ENABLE_SEARCH, FeatureSwitch.FeatureMaster.enableSearch);
        sharedPrefUtils.setFeatureSwitchOverride(FeatureSwitchOverride.ENABLE_DEBUG_MENU, FeatureSwitch.FeatureMaster.enableDebugMenu);

        // Set isFirstLaunch to false
        sharedPrefUtils.setIsFirstLaunch(false);
    }

    private void applyFeatureSwitch(BottomNavigationView bottomNavigationView, FeatureSwitchOverride featureSwitchOverride, boolean defaultValue, int menuId) {
        boolean isEnabled = FeatureSwitch.Meta.canOverrideSwitch ? sharedPrefUtils.getFeatureSwitchOverride(featureSwitchOverride) : defaultValue;
        if (!isEnabled) bottomNavigationView.getMenu().removeItem(menuId);
    }

    @Override
    public void navigateToFragment(Fragment fragment) {
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.nav_host_fragment, fragment)
                .addToBackStack(null)
                .commit();
    }

    @Override
    public void navigateToFragment(Fragment fragment, boolean addToBackStack) {
        if (addToBackStack) {
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.nav_host_fragment, fragment)
                    .addToBackStack(null)
                    .commit();
        } else {
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.nav_host_fragment, fragment)
                    .commit();
        }
    }

    @Override
    public void navigateToFragment(Fragment fragment, boolean addToBackStack, boolean clearBackStack) {
        if (clearBackStack) {
            getSupportFragmentManager().popBackStack(null, FragmentManager.POP_BACK_STACK_INCLUSIVE);
        }
        if (addToBackStack) {
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.nav_host_fragment, fragment)
                    .addToBackStack(null)
                    .commit();
        } else {
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.nav_host_fragment, fragment)
                    .commit();
        }
    }

    public void navigateToFragmentAlternate(Fragment fragment, boolean addToBackStack) {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();

        // get the current fragment
        Fragment currentFragment = getSupportFragmentManager().getPrimaryNavigationFragment();

        // hide the current fragment
        if (currentFragment != null) {
            transaction.hide(currentFragment);
        }

        // Add the new fragment
        transaction.add(R.id.nav_host_fragment, fragment);

        // Add the transaction to the back stack if needed
        if (addToBackStack) {
            transaction.addToBackStack(null);
        }

        // Commit the transaction
        transaction.commit();

        // Update the primary navigation fragment
        getSupportFragmentManager().beginTransaction().setPrimaryNavigationFragment(fragment).commit();
    }
}
