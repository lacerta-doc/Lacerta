package one.nem.lacerta.component.viewer;

import android.graphics.Bitmap;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

import javax.inject.Inject;

import dagger.hilt.android.AndroidEntryPoint;
import one.nem.lacerta.data.Document;
import one.nem.lacerta.model.document.DocumentDetail;
import one.nem.lacerta.model.document.page.Page;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ComponentViewerTopFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
@AndroidEntryPoint
public class ComponentViewerTopFragment extends Fragment {

    @Inject
    Document document;

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
        View view = inflater.inflate(R.layout.fragment_component_viewer_top, container, false);

        DocumentDetail documentDetail = document.getDocument(documentId);

        ArrayList<Page> documentPages = new ArrayList<>();
        documentPages = documentDetail.getPages();

        RecyclerView recyclerView = view.findViewById(R.id.body_recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        ViewerBodyAdapter viewerBodyAdapter = new ViewerBodyAdapter(documentPages);
        recyclerView.setAdapter(viewerBodyAdapter);

        return view;
    }
}