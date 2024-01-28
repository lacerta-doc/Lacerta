package one.nem.lacerta.component.scanner;

import android.Manifest;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.android.material.appbar.MaterialToolbar;
import com.websitebeaver.documentscanner.DocumentScanner;

import java.util.ArrayList;
import java.util.Objects;
import java.util.concurrent.CompletableFuture;

import javax.inject.Inject;

import dagger.hilt.android.AndroidEntryPoint;
import one.nem.lacerta.component.common.picker.LacertaFilePickerDialog;
import one.nem.lacerta.data.Document;
import one.nem.lacerta.data.LacertaLibrary;
import one.nem.lacerta.model.document.DocumentDetail;
import one.nem.lacerta.model.document.DocumentMeta;
import one.nem.lacerta.model.document.page.Page;
import one.nem.lacerta.processor.DocumentProcessor;
import one.nem.lacerta.processor.factory.DocumentProcessorFactory;
import one.nem.lacerta.utils.LacertaLogger;
import one.nem.lacerta.vcs.factory.LacertaVcsFactory;

@AndroidEntryPoint
public class ScannerManagerActivity extends AppCompatActivity {

    String TAG = "ScannerManagerActivity";

    @Inject
    LacertaLogger logger;

    @Inject
    Document document;

    @Inject
    DocumentProcessorFactory documentProcessorFactory;

    @Inject
    LacertaVcsFactory lacertaVcsFactory;

//    @Inject
//    public ScannerManagerActivity(LacertaLogger logger, Document document, LacertaLibrary lacertaLibrary, DocumentProcessorFactory documentProcessorFactory, LacertaVcsFactory lacertaVcsFactory) {
//        this.logger = logger;
//        this.document = document;
//        this.lacertaLibrary = lacertaLibrary;
//        this.documentProcessorFactory = documentProcessorFactory;
//        this.lacertaVcsFactory = lacertaVcsFactory;
//    }

    @Inject
    public ScannerManagerActivity() {
        // Required empty public constructor
    }

    // Variables
    private ArrayList<Bitmap> croppedImages = new ArrayList<>();

    private boolean update = false;
    private String documentId;
    private int index = 0;

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

    DocumentScanner documentScannerUpdate = new DocumentScanner( // TODO-rca: ひどすぎるのでなんとかする
            this,
            (croppedImageResults) -> {
                logger.debug(TAG, "croppedImage size: " + croppedImageResults.size());
                ArrayList<Bitmap> croppedImages = new ArrayList<>();
                for (String result : croppedImageResults) {
                    croppedImages.add(BitmapFactory.decodeFile(result));
                }
                updatePage();
                return null;
            },
            (errorMessage) -> {
                // an error happened
                logger.error(TAG, "Error: " + errorMessage);
                logger.e_code("543a230e-cb9a-47a2-8131-3beecfe1c458");
                finish();
                return null;
            },
            () -> {
                // user canceled document scan
                logger.debug(TAG, "User canceled document scan");
                finish();
                return null;
            },
            null,
            null,
            1
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



        if (ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA)
                != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.CAMERA}, 1); // TODO-rca: リクエストコードを定数にする
        }


        MaterialToolbar toolbar = findViewById(R.id.top_toolbar);
        setSupportActionBar(toolbar);
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);

        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        if (bundle != null) {
            update = bundle.getBoolean("update", false);
            documentId = bundle.getString("documentId");
            index = bundle.getInt("index", 0);
        }
        if (this.update) {
            documentScanner = documentScannerUpdate;
        }
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
            saveNewDocument();
            return true;
        }
        else if (item.getItemId() == R.id.action_insert_exist) {
            // 既存ドキュメントに挿入
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
        // Deprecatedだが、中断機能が存在しないので操作をブロックする目的で(意図的に)使用
        ProgressDialog dialog = new ProgressDialog(this);
        dialog.setMessage("保存中..."); // TODO-rca: テキストをリソースに移動
        dialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        dialog.setCancelable(false);
        dialog.show();
        DocumentMeta documentMeta = new DocumentMeta("Untitled"); // TODO-rca: デフォルトタイトルを指定できるようにする
        document.createDocument(documentMeta).thenAccept((documentDetail) -> {
            Bitmap[] bitmaps = new Bitmap[croppedImages.size()];
            croppedImages.toArray(bitmaps);
            logger.debug(TAG, "bitmaps.length: " + bitmaps.length);
            addPagesToDocumentDetail(documentDetail, bitmaps, null).join();
            document.updateDocument(documentDetail).join();
            dialog.dismiss();
            finish();
        });

    }

    private CompletableFuture<Void> addPagesToDocumentDetail(DocumentDetail documentDetail, Bitmap[] bitmaps, String commitMessage) {
        return CompletableFuture.runAsync(() -> {
            try {
                document.updateDocument(documentProcessorFactory.create(documentDetail).addNewPagesToLast(bitmaps).getDocumentDetail()).join();
                lacertaVcsFactory.create(documentDetail.getMeta().getId()).generateRevisionAtCurrent(commitMessage == null ? "Update" : commitMessage);
            } catch (Exception e) {
                logger.error(TAG, "Error: " + e.getMessage());
                logger.e_code("9dff2a28-20e8-4ccd-9d04-f0c7646faa6a");
            }
        });
    }

    private void updatePage() {
        logger.debug(TAG, "updatePage");
        // Deprecatedだが、中断機能が存在しないので操作をブロックする目的で(意図的に)使用
        ProgressDialog dialog = new ProgressDialog(this);
        dialog.setMessage("保存中..."); // TODO-rca: テキストをリソースに移動
        dialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        dialog.setCancelable(false);
        dialog.show();
        document.getDocument(documentId).thenAccept((documentDetail) -> {
            DocumentProcessor documentProcessor = documentProcessorFactory.create(documentDetail);
            if (croppedImages.size() != 1) {
                logger.error(TAG, "croppedImages.size() != 1");
                logger.e_code("d8e2b8c9-9b7e-4b7e-9e1e-9e3b8b8b8b8b");
                return;
            }
            if (croppedImages.get(0) == null) {
                logger.error(TAG, "croppedImages.get(0) == null");
                logger.e_code("d8e2b8c9-9b7e-4b7e-9e1e-9e3b8b8b8b8b");
                return;
            }
//            documentProcessor.updatePageAtIndex(croppedImages.get(0), index);
            logger.debug(TAG, "documentProcessor.getPageCount(): " + documentProcessor.getPageCount()
                + ", documentDetail.getPages().size(): " + documentDetail.getPages().size());
            document.updateDocument(documentProcessor.getDocumentDetail()).join();
            dialog.dismiss();
        });
    }

    private void insertToExistDocument() {
        logger.debug(TAG, "insertToExistDocument");
        LacertaFilePickerDialog dialog = new LacertaFilePickerDialog();
        dialog.setListener(((name, fileId) -> {
            document.getDocument(fileId).thenAccept((documentDetail) -> {
                Bitmap[] bitmaps = new Bitmap[croppedImages.size()];
                croppedImages.toArray(bitmaps);
                logger.debug(TAG, "bitmaps.length: " + bitmaps.length);
                addPagesToDocumentDetail(documentDetail, bitmaps, "ページを追加").join();
                document.updateDocument(documentDetail).join();
                finish();
            });
        }));
        dialog.setTitle("追加するドキュメントを選択");
        dialog.show(getSupportFragmentManager(), "LacertaFilePickerDialog");
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

        selectedImage.setImageBitmap(resultImages.get(0));
    }

}