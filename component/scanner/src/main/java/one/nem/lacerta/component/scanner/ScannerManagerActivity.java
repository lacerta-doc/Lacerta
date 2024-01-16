package one.nem.lacerta.component.scanner;

import android.os.Bundle;
import android.util.Log;

import androidx.activity.EdgeToEdge;
import androidx.annotation.AnimatorRes;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.websitebeaver.documentscanner.DocumentScanner;

import javax.inject.Inject;

import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class ScannerManagerActivity extends AppCompatActivity {

    DocumentScanner documentScanner = new DocumentScanner(
            this,
            (croppedImageResults) -> {
                // display the first cropped image
                return null;
            },
            (errorMessage) -> {
                // an error happened
                Log.v("documentscannerlogs", errorMessage);
                return null;
            },
            () -> {
                // user canceled document scan
                Log.v("documentscannerlogs", "User canceled document scan");
                return null;
            },
            null,
            null,
            5
    );

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_scanner_manager);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        documentScanner.startScan();
        // Init

    }

}