package one.nem.lacerta.feature.debug;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link DebugMenuVcsRevRecordFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class DebugMenuVcsRevRecordFragment extends Fragment {
    public DebugMenuVcsRevRecordFragment() {
        // Required empty public constructor
    }

    public static DebugMenuVcsRevRecordFragment newInstance() {
        DebugMenuVcsRevRecordFragment fragment = new DebugMenuVcsRevRecordFragment();
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
        View view = inflater.inflate(R.layout.fragment_debug_menu_vcs_rev_record, container, false);

        return view;
    }
}