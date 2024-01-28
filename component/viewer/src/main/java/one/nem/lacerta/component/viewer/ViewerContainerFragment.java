package one.nem.lacerta.component.viewer;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ViewerContainerFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ViewerContainerFragment extends Fragment {

    public ViewerContainerFragment() {
        // Required empty public constructor
    }

    public static ViewerContainerFragment newInstance() {
        ViewerContainerFragment fragment = new ViewerContainerFragment();
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
        return inflater.inflate(R.layout.fragment_viewer_container, container, false);
    }
}