package one.nem.lacerta;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;
import androidx.navigation.ui.NavigationUI;

import android.os.Bundle;
import android.transition.Transition;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.transition.MaterialSharedAxis;


import dagger.hilt.android.AndroidEntryPoint;
import javax.inject.Inject;

@AndroidEntryPoint
public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Debug
        FragmentManager supportFragmentManager = getSupportFragmentManager();

        NavHostFragment navHostFragment = (NavHostFragment) supportFragmentManager.findFragmentById(R.id.nav_host_fragment);

        // Animation
        if (navHostFragment != null) {
            MaterialSharedAxis exitTransition = new MaterialSharedAxis(MaterialSharedAxis.Z, true);
            exitTransition.setDuration(500);
            navHostFragment.setExitTransition(exitTransition);

            MaterialSharedAxis reenterTransition = new MaterialSharedAxis(MaterialSharedAxis.Z, false);
            reenterTransition.setDuration(500);
            navHostFragment.setReenterTransition(reenterTransition);
        }

        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_nav);

        NavController navController = navHostFragment.getNavController();
        NavigationUI.setupWithNavController(bottomNavigationView, navController);

        Toast.makeText(this, "testMessage", Toast.LENGTH_SHORT).show();
    }

}