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

    private void processResult(ArrayList<Bitmap> resultImages) {
        logger.debug(TAG, "processResult");

        if (resultImages.isEmpty()) {
            logger.debug(TAG, "resultImages(arg) is empty");
            if (this.croppedImages.isEmpty()) {
                logger.debug(TAG, "this.resultImages is empty");
                logger.error(TAG, "error code: 398f4ca7-06f4-43c4-a153-e2ba5bbdb92b"); // TODO-rca: Loggerに組み込む？
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

    private void updateResultView(ArrayList<Bitmap> resultImages) {
        logger.debug(TAG, "updateResultView");

        LinearLayout resultView = findViewById(R.id.result_list_container);
        resultView.removeAllViews();

        for (Bitmap resultImage : resultImages) {
            View resultImageView = getLayoutInflater().inflate(R.layout.result_image_container_item, null);
            ImageView imageView = resultImageView.findViewById(R.id.result_image);
            imageView.setImageBitmap(resultImage);
            imageView.setOnClickListener(v -> {
                // タッチされた画像をプレビューに設定します。
//                selectedImage.setImageBitmap(resultImage);
                // すべての画像の選択状態を解除します。
                for (int i = 0; i < resultView.getChildCount(); i++) {
                    View child = resultView.getChildAt(i).findViewById(R.id.result_image);
                    child.setSelected(false);
                }
                // タッチされた画像を選択状態にします。
                v.setSelected(true);
            });

            resultView.addView(resultImageView);
        }
    }

}