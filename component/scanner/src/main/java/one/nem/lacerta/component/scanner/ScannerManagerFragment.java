package one.nem.lacerta.component.scanner;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ScannerManagerFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ScannerManagerFragment extends Fragment {

    private static final boolean DEFAULT_SINGLE_PAGE = false;
    private boolean singlePage;

    public ScannerManagerFragment() {
        // Required empty public constructor
    }

    public static ScannerManagerFragment newInstance(boolean singlePage) {
        ScannerManagerFragment fragment = new ScannerManagerFragment();
        Bundle args = new Bundle();
        args.putBoolean("singlePage", singlePage);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            singlePage = getArguments().getBoolean("singlePage", DEFAULT_SINGLE_PAGE);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_scanner_manager, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        view.findViewById(R.id.button_start_scan).setOnClickListener(v -> {

        });
    }
}