package one.nem.lacerta.component.scanner;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.websitebeaver.documentscanner.DocumentScanner;

import javax.inject.Inject;

import dagger.hilt.android.AndroidEntryPoint;
import one.nem.lacerta.utils.LacertaLogger;
import one.nem.lacerta.vcs.LacertaVcs;
import one.nem.lacerta.vcs.factory.LacertaVcsFactory;


@AndroidEntryPoint
public class ScannerManagerFragment extends Fragment {

    String TAG = getClass().getSimpleName();

    @Inject
    LacertaLogger logger;

    @Inject
    LacertaVcsFactory vcsFactory;

    DocumentScanner documentScanner = new DocumentScanner(
            getActivity(),
            (croppedImage) -> {
                // TODO-rca: 画像を保存する
                return null;
            },
            (error) -> {
                // TODO-rca: エラー処理
                return null;
            },
            () -> {
                // TODO-rca: キャンセル処理
                return null;
            },
            null,
            null,
            null
    );

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

        // Init
        logger.debug(TAG, "called");

        view.findViewById(R.id.button_start_scan).setOnClickListener(v -> {
            documentScanner.startScan();
        });
    }
}