package one.nem.lacerta.component.viewer;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.fragment.app.FragmentManager;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;
import androidx.navigation.ui.NavigationUI;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import javax.inject.Inject;

import dagger.hilt.android.AndroidEntryPoint;
import one.nem.lacerta.utils.LacertaLogger;

@AndroidEntryPoint
public class ViewerMainActivity extends AppCompatActivity {

    @Inject
    LacertaLogger logger;

    @Inject
    public ViewerMainActivity() {
    }

    // Variables
    private static final String TAG = "ViewerMainActivity";
    String documentId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_viewer_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        Intent intent = getIntent();
        try {
            documentId = intent.getStringExtra("documentId");
        }
        catch (Exception e) {
            logger.error(TAG, "Failed to get documentId from intent");
            logger.error(TAG, "Searchable Error code: " + "f64c21a2-391f-4c40-92f6-183da459de21");
            Toast.makeText(this, "Failed to get documentId from intent", Toast.LENGTH_LONG).show();
            finish();
        }

        // Navigation
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.nav_host_fragment, ComponentViewerTopFragment.newInstance(documentId))
                .commit();
    }
}