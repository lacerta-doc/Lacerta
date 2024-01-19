package one.nem.lacerta;

import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.content.ContextCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.FragmentManager;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;
import androidx.navigation.ui.NavigationUI;

import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.shape.CornerFamily;
import com.google.android.material.shape.MaterialShapeDrawable;
import com.google.android.material.shape.ShapeAppearanceModel;


import dagger.hilt.android.AndroidEntryPoint;
import javax.inject.Inject;

@AndroidEntryPoint
public class MainActivity extends AppCompatActivity {

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
        getWindow().setNavigationBarColor(ContextCompat.getColor(this, one.nem.lacerta.shared.ui.R.color.colorSurfaceVariant));

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

//        // Create a new TextView
//        TextView toolbarTitle = new TextView(this);
//        // Set the text and center it in the TextView
//        toolbarTitle.setText("App Name");
//        toolbarTitle.setTextColor(Color.WHITE);
//        toolbarTitle.setTextSize(20);
//        toolbarTitle.setLayoutParams(new Toolbar.LayoutParams(Toolbar.LayoutParams.WRAP_CONTENT,
//                Toolbar.LayoutParams.WRAP_CONTENT, Gravity.CENTER_HORIZONTAL));
//
//        // Set the TextView as the title
//        toolbar.addView(toolbarTitle);

        // Create a ShapeAppearanceModel with rounded corners
        ShapeAppearanceModel shapeAppearanceModel = new ShapeAppearanceModel()
                .toBuilder()
                .setAllCorners(CornerFamily.ROUNDED, getResources().getDimension(one.nem.lacerta.shared.ui.R.dimen.toolbar_corner_radius))
                .build();

        // Create a MaterialShapeDrawable with the ShapeAppearanceModel
        MaterialShapeDrawable shapeDrawable = new MaterialShapeDrawable(shapeAppearanceModel);

        // Set the MaterialShapeDrawable as the background of the Toolbar
        toolbar.setBackground(shapeDrawable);

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, one.nem.lacerta.shared.ui.R.string.placeholder, one.nem.lacerta.shared.ui.R.string.placeholder);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        // Set the app name in the middle of the toolbar
        getSupportActionBar().setTitle("App Name");
    }
}
