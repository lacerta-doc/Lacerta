package one.nem.lacerta.component.viewer;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ComponentViewerTopFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ComponentViewerTopFragment extends Fragment {

    private static final String TAG = "ComponentViewerTopFragment";

    private String documentId;

    public ComponentViewerTopFragment() {
        // Required empty public constructor
    }

    public static ComponentViewerTopFragment newInstance(String documentId) {
        ComponentViewerTopFragment fragment = new ComponentViewerTopFragment();
        Bundle args = new Bundle();
        args.putString("documentId", documentId);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            documentId = getArguments().getString("documentId");
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_component_viewer_top, container, false);
    }
}