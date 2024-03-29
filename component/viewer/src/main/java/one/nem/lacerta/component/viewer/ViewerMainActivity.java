package one.nem.lacerta.component.viewer;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.activity.OnBackPressedCallback;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

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
    String documentName;
    boolean hasCombined = false;

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

//        // Transition
//        overridePendingTransition(one.nem.lacerta.shared.ui.R.anim.nav_up_enter_anim, one.nem.lacerta.shared.ui.R.anim.nav_up_exit_anim);

        Intent intent = getIntent();
        try {
            documentId = intent.getStringExtra("documentId");
            documentName = intent.getStringExtra("documentName");
            hasCombined = intent.getBooleanExtra("hasCombined", false);
        }
        catch (Exception e) {
            logger.error(TAG, "Failed to get documentId from intent");
            logger.error(TAG, "Searchable Error code: " + "f64c21a2-391f-4c40-92f6-183da459de21");
            Toast.makeText(this, "Failed to get documentId from intent", Toast.LENGTH_LONG).show();
            finish();
        }

        // Navigation
//        getSupportFragmentManager().beginTransaction()
//                .replace(R.id.nav_host_fragment, ViewerListFragment.newInstance(documentId, documentName))
//                .commit();
        if (hasCombined) {
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.nav_host_fragment, ViewerContainerFragment.newInstance(documentId, documentName, hasCombined))
                    .commit();
        } else {
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.nav_host_fragment, ViewerContainerFragment.newInstance(documentId, documentName))
                    .commit();
        }
    }

}