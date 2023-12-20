package one.nem.lacerta.component.scanner;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import android.provider.MediaStore;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ScannerDataManagerStubFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ScannerDataManagerStubFragment extends Fragment {

    // Results
    private ArrayList<CapturedData> results = new ArrayList<>();
    private final ActivityResultLauncher<Intent> cameraLauncher = registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult(),
            result -> {
                if (result.getResultCode() == Activity.RESULT_OK) {
                    Intent data = result.getData();
                    Bundle extras = data.getExtras();
                    Bitmap imageBitmap = (Bitmap) extras.get("data");
                    // TODO-rca: エラーハンドリング
                    results.add(new CapturedData("Placeholder", Integer.toString(imageBitmap.getHeight()), Integer.toString(imageBitmap.getWidth()), "Placeholder", imageBitmap));
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
                Log.d("ScannerDataManagerStubFragment", "camera available");
                cameraLauncher.launch(takePictureIntent);
            }
            else {
                Log.d("ScannerDataManagerStubFragment", "camera not available");
            }
        });
    }

    @Override
    public void onResume() {
        super.onResume();
        Log.d("ScannerDataManagerStubFragment", "onResume");


    }

    public void updateResults() {
        Log.d("ScannerDataManagerStubFragment", "updateResults");

        // TODO-rca: エラーハンドリング
        RecyclerView recyclerView = getView().findViewById(R.id.result_recycler_view);

        recyclerView.setLayoutManager(new androidx.recyclerview.widget.LinearLayoutManager(getContext()));
        recyclerView.setAdapter(new ResultAdapter(this.results));


    }

}