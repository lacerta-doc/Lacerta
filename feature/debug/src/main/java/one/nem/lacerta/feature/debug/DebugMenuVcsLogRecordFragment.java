package one.nem.lacerta.feature.debug;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import javax.inject.Inject;

import dagger.hilt.android.AndroidEntryPoint;
import one.nem.lacerta.source.database.LacertaDatabase;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link DebugMenuVcsLogRecordFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
@AndroidEntryPoint
public class DebugMenuVcsLogRecordFragment extends Fragment {

    @Inject
    LacertaDatabase database;


    public DebugMenuVcsLogRecordFragment() {
        // Required empty public constructor
    }

    public static DebugMenuVcsLogRecordFragment newInstance() {
        DebugMenuVcsLogRecordFragment fragment = new DebugMenuVcsLogRecordFragment();
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
        View view = inflater.inflate(R.layout.fragment_debug_menu_vcs_log_record, container, false);

        return view;
    }
}