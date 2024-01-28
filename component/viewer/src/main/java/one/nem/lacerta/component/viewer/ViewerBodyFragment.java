package one.nem.lacerta.component.viewer;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import javax.inject.Inject;

import dagger.hilt.android.AndroidEntryPoint;
import one.nem.lacerta.data.Document;
import one.nem.lacerta.data.LacertaLibrary;
import one.nem.lacerta.utils.LacertaLogger;
import one.nem.lacerta.vcs.factory.LacertaVcsFactory;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ViewerBodyFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
@AndroidEntryPoint
public class ViewerBodyFragment extends Fragment {

    @Inject
    Document document;

    @Inject
    LacertaLibrary lacertaLibrary;

    @Inject
    LacertaLogger logger;

    @Inject
    LacertaVcsFactory lacertaVcsFactory;

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