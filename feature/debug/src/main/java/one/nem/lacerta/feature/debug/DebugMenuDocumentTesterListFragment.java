package one.nem.lacerta.feature.debug;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link DebugMenuDocumentTesterListFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class DebugMenuDocumentTesterListFragment extends Fragment {
    public DebugMenuDocumentTesterListFragment() {
        // Required empty public constructor
    }

    public static DebugMenuDocumentTesterListFragment newInstance() {
        DebugMenuDocumentTesterListFragment fragment = new DebugMenuDocumentTesterListFragment();
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
        return inflater.inflate(R.layout.fragment_debug_menu_document_tester_list, container, false);
    }
}