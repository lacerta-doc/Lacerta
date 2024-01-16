package one.nem.lacerta.component.scanner;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;

import androidx.activity.EdgeToEdge;
import androidx.annotation.AnimatorRes;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.websitebeaver.documentscanner.DocumentScanner;

import java.util.ArrayList;

import javax.inject.Inject;

import dagger.hilt.android.AndroidEntryPoint;
import one.nem.lacerta.utils.LacertaLogger;

@AndroidEntryPoint
public class ScannerManagerActivity extends AppCompatActivity {

    String TAG = "ScannerManagerActivity";

    @Inject
    LacertaLogger logger;

    // Variables
    private ArrayList<Bitmap> resultImages = new ArrayList<>();

    View view;

    DocumentScanner documentScanner = new DocumentScanner(
            this,
            (croppedImageResults) -> {
                for (String result : croppedImageResults) {
                    this.resultImages.add(BitmapFactory.decodeFile(result));
                }
                initResultView();
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

        this.view = findViewById(R.id.main); // TODO-rca:なんとかする

    }

    private void initResultView() {
        if (this.view == null) {
            logger.debug(TAG, "initResultView: view is null");
            return;
        }

        // Log pt
        logger.debug(TAG, "Total images: " + this.resultImages.size());

        LinearLayout resultContainer = view.findViewById(R.id.result_list_container);

        // ImageButtonを追加する
    }

}