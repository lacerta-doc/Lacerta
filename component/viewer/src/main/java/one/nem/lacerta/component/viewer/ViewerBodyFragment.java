package one.nem.lacerta.component.viewer;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ViewerBodyFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ViewerBodyFragment extends Fragment {


    public ViewerBodyFragment() {
        // Required empty public constructor
    }

    public static ViewerBodyFragment newInstance() {
        ViewerBodyFragment fragment = new ViewerBodyFragment();
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
        return inflater.inflate(R.layout.fragment_viewer_body, container, false);
    }
}