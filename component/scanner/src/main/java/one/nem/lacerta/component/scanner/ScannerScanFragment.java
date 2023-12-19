package one.nem.lacerta.component.scanner;

import android.graphics.BitmapFactory;
import android.os.Bundle;

import androidx.constraintlayout.utils.widget.ImageFilterView;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.websitebeaver.documentscanner.DocumentScanner;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ScannerScanFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ScannerScanFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
//    private static final String MAX_SCAN_COUNT = "max_scan_count"; // 規定値

    // TODO: Rename and change types of parameters
    private String mParam1;


    public ScannerScanFragment() {
        // Required empty public constructor
    }

    public static ScannerScanFragment newInstance(String param1) {
        ScannerScanFragment fragment = new ScannerScanFragment();
        Bundle args = new Bundle();
//        args.putString(MAX_SCAN_COUNT, param1);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
//            mParam1 = getArguments().getString(MAX_SCAN_COUNT);
//            scan(Integer.parseInt(mParam1));
        }
    }


    public DocumentScanner getDocumentScanner() {
        return new DocumentScanner(
                this,
                (croppedImageResults) -> {
                    // display the first cropped image
                    croppedImageView.setImageBitmap(
                            BitmapFactory.decodeFile(croppedImageResults.get(0))
                    );
                    return null;
                },
                (errorMessage) -> {
                    // an error happened
                    return null;
                },
                () -> {
                    // user canceled document scan
                    return null;
                },
                null,
                null,
                null
        );
    }
}