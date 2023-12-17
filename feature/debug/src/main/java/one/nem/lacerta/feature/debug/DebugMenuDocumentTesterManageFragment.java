package one.nem.lacerta.feature.debug;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link DebugMenuDocumentTesterManageFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class DebugMenuDocumentTesterManageFragment extends Fragment {
    public DebugMenuDocumentTesterManageFragment() {
        // Required empty public constructor
    }

    public static DebugMenuDocumentTesterManageFragment newInstance() {
        DebugMenuDocumentTesterManageFragment fragment = new DebugMenuDocumentTesterManageFragment();
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
        return inflater.inflate(R.layout.fragment_debug_menu_document_tester_manage, container, false);
    }
}