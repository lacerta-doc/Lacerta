package one.nem.lacerta.component.scanner;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.AnimatorRes;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.android.material.appbar.MaterialToolbar;
import com.websitebeaver.documentscanner.DocumentScanner;

import java.util.ArrayList;
import java.util.Objects;

import javax.inject.Inject;

import dagger.hilt.android.AndroidEntryPoint;
import one.nem.lacerta.utils.LacertaLogger;

@AndroidEntryPoint
public class ScannerManagerActivity extends AppCompatActivity {

    String TAG = "ScannerManagerActivity";

    @Inject
    LacertaLogger logger;

    // Variables
    private ArrayList<Bitmap> croppedImages = new ArrayList<>();

    View view;

    DocumentScanner documentScanner = new DocumentScanner(
            this,
            (croppedImageResults) -> {
                logger.debug(TAG, "croppedImage size: " + croppedImageResults.size());
                ArrayList<Bitmap> croppedImages = new ArrayList<>();
                for (String result : croppedImageResults) {
                    croppedImages.add(BitmapFactory.decodeFile(result));
                }
                processResult(croppedImages);
                return null;
            },
            (errorMessage) -> {
                // an error happened
                logger.error(TAG, "Error: " + errorMessage);
                logger.e_code("543a230e-cb9a-47a2-8131-3beecfe1c458");
                return null;
            },
            () -> {
                // user canceled document scan
                logger.debug(TAG, "User canceled document scan");
                return null;
            },
            null,
            null,
            null
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

        MaterialToolbar toolbar = findViewById(R.id.top_toolbar);
        setSupportActionBar(toolbar);
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);

        documentScanner.startScan();
        // Init

        this.view = findViewById(R.id.main); // TODO-rca:なんとかする

    }

    @Override
    public boolean onCreateOptionsMenu(android.view.Menu menu) {
        getMenuInflater().inflate(R.menu.scanner_result_toolbar, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(android.view.MenuItem item) {
        if (item.getItemId() == R.id.action_save_new) {
            // 新ドキュメントとして保存
            Toast.makeText(this, "保存処理", Toast.LENGTH_SHORT).show();
            saveNewDocument();
            return true;
        } else if (item.getItemId() == R.id.action_insert_exist) {
            // 既存ドキュメントに挿入
            Toast.makeText(this, "挿入処理", Toast.LENGTH_SHORT).show();
            insertToExistDocument();
            return true;
        } else if (item.getItemId() == android.R.id.home) {
            finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void processResult(ArrayList<Bitmap> resultImages) {
        logger.debug(TAG, "processResult");

        if (resultImages.isEmpty()) {
            logger.debug(TAG, "resultImages(arg) is empty");
            if (this.croppedImages.isEmpty()) {
                logger.debug(TAG, "this.resultImages is empty");
                logger.e_code("7cb0584e-74ef-48ec-848a-c4d14e75e15a");
                // TODO-rca: なんかする
            } else {
                logger.debug(TAG, "this.resultImages is not empty");
                updateResultView(this.croppedImages);
            }
        } else {
            logger.debug(TAG, "resultImages(arg) is not empty");
            updateResultView(resultImages);
            this.croppedImages = resultImages;
        }

    }

    private void saveNewDocument() {
        logger.debug(TAG, "saveNewDocument");
        // TODO-rca: 実装
    }

    private void insertToExistDocument() {
        logger.debug(TAG, "insertToExistDocument");
        // TODO-rca: 実装
    }

    private void updateResultView(ArrayList<Bitmap> resultImages) {
        logger.debug(TAG, "updateResultView");

        LinearLayout resultView = findViewById(R.id.result_list_container);
        ImageView selectedImage = findViewById(R.id.selected_image);
        resultView.removeAllViews();

        for (Bitmap resultImage : resultImages) {
            View resultImageView = getLayoutInflater().inflate(R.layout.result_image_container_item, null);
            ImageView imageView = resultImageView.findViewById(R.id.result_image);
            imageView.setImageBitmap(resultImage);
            imageView.setOnClickListener(v -> {

                selectedImage.setImageBitmap(resultImage);

                for (int i = 0; i < resultView.getChildCount(); i++) {
                    View child = resultView.getChildAt(i).findViewById(R.id.result_image);
                    child.setSelected(false);
                }

                v.setSelected(true);
            });

            resultView.addView(resultImageView);
        }
    }

}