package one.nem.lacerta.component.scanner;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.core.content.FileProvider;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Environment;
import android.provider.MediaStore;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;
import java.util.UUID;

import javax.inject.Inject;

import one.nem.lacerta.model.document.DocumentDetail;
import one.nem.lacerta.model.document.DocumentMeta;
import one.nem.lacerta.processor.DocumentProcessor;
import one.nem.lacerta.processor.factory.DocumentProcessorFactory;

import one.nem.lacerta.utils.LacertaLogger;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ScannerDataManagerStubFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ScannerDataManagerStubFragment extends Fragment {

    // TODO-rca: 時間があったらcacheを使うようにする？

    // Results
    private ArrayList<CapturedData> results = new ArrayList<>();

    private Uri photoURI;

    @Inject
    DocumentProcessorFactory documentProcessorFactory;

    @Inject
    LacertaLogger logger;

    private final ActivityResultLauncher<Intent> cameraLauncher = registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult(),
            result -> {
                if (result.getResultCode() == Activity.RESULT_OK) {
                    try {
                        if (getActivity() == null) {
                            Log.d("ScannerDataManagerStubFragment", "getActivity() is null");
                            return;
                        }
                        if (photoURI == null) {
                            Log.d("ScannerDataManagerStubFragment", "photoURI is null");
                            Toast.makeText(getActivity(), "photoURI is null", Toast.LENGTH_LONG).show();
                            return;
                        }
                        Bitmap imageBitmap = MediaStore.Images.Media.getBitmap(getActivity().getContentResolver(), photoURI);
                        results.add(new CapturedData("Placeholder", Integer.toString(imageBitmap.getHeight()), Integer.toString(imageBitmap.getWidth()), "Placeholder", imageBitmap));
                    } catch (IOException e) {
                        Log.e("ScannerDataManagerStubFragment", "Error occurred while reading the file", e);
                    }
                }
            }
    );

    public ScannerDataManagerStubFragment() {
        // Required empty public constructor
    }
    // TODO: Rename and change types and number of parameters
    public static ScannerDataManagerStubFragment newInstance() {
        ScannerDataManagerStubFragment fragment = new ScannerDataManagerStubFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_scanner_data_manager_stub, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        view.findViewById(R.id.button_call_camera).setOnClickListener(v -> {
            Log.d("ScannerDataManagerStubFragment", "button_call_camera clicked");
            Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
            if (takePictureIntent.resolveActivity(getActivity().getPackageManager()) != null) {
                File photoFile = null;
                try {
                    String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss", Locale.US).format(new Date());
                    String imageFileName = "JPEG_" + timeStamp + "_";
                    File storageDir = getActivity().getExternalFilesDir(Environment.DIRECTORY_PICTURES);
                    photoFile = File.createTempFile(imageFileName, ".jpg", storageDir);
                } catch (IOException ex) {
                    Log.e("ScannerDataManagerStubFragment", "Error occurred while creating the file", ex);
                }
                if (photoFile != null) {
                    photoURI = FileProvider.getUriForFile(getActivity(), "one.nem.lacerta.provider", photoFile);
                    takePictureIntent.putExtra(MediaStore.EXTRA_OUTPUT, photoURI);
                    cameraLauncher.launch(takePictureIntent);
                }
                else {
                    Log.d("ScannerDataManagerStubFragment", "photoFile is null");
                }
            }
            else {
                Log.d("ScannerDataManagerStubFragment", "camera not available");
            }
            updateResults();
        });

        view.findViewById(R.id.button_create_documnent).setOnClickListener(v -> {
            Log.d("ScannerDataManagerStubFragment", "button_create_documnent clicked");
            Toast.makeText(getActivity(), "button_create_documnent clicked", Toast.LENGTH_LONG).show();


        });

        view.findViewById(R.id.button_init_document_processor).setOnClickListener(v -> {
            Log.d("ScannerDataManagerStubFragment", "button_init_document_processor clicked");
            Toast.makeText(getActivity(), "button_init_document_processor clicked", Toast.LENGTH_LONG).show();
            // TODO-rca: ここでDocumentProcessorを初期化する
            DocumentProcessor documentProcessor = documentProcessorFactory.create();
        });
    }

    public DocumentDetail createSampleDocumentDetail() {

        String id = UUID.randomUUID().toString();

        Toast.makeText(getActivity(), "Generated id: " + id, Toast.LENGTH_LONG).show();
        logger.debug("CreateSample", "Generated id: " + id);

        DocumentMeta meta = new DocumentMeta(
                id,
                "Sample" + DateTimeFormatter.ofPattern("yyyyMMddHHmmss").format(LocalDateTime.now()),
                new Date(),
                new Date());

        return new DocumentDetail(meta, null, "SampleAuthor", "SampleDefaultBranch");
    }

    @Override
    public void onResume() {
        super.onResume();
        Log.d("ScannerDataManagerStubFragment", "onResume");
        updateResults();
    }

    public void updateResults() {
        Log.d("ScannerDataManagerStubFragment", "updateResults");

        // TODO-rca: エラーハンドリング
        RecyclerView recyclerView = getView().findViewById(R.id.result_recycler_view);

        recyclerView.setLayoutManager(new androidx.recyclerview.widget.LinearLayoutManager(getContext()));
        recyclerView.setAdapter(new CaptureResultAdapter(this.results));


    }

}