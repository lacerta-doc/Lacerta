package one.nem.lacerta.component.viewer;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import javax.inject.Inject;

import dagger.hilt.android.AndroidEntryPoint;
import one.nem.lacerta.data.Document;
import one.nem.lacerta.data.LacertaLibrary;
import one.nem.lacerta.utils.LacertaLogger;
import one.nem.lacerta.vcs.LacertaVcs;
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

    // Variables
    private String documentId;
    private String documentName;
    private String revisionId;

    public ViewerBodyFragment() {
        // Required empty public constructor
    }

    public static ViewerBodyFragment newInstance(String documentId, String documentName) {
        ViewerBodyFragment fragment = new ViewerBodyFragment();
        Bundle args = new Bundle();
        args.putString("documentId", documentId);
        args.putString("documentName", documentName);
        fragment.setArguments(args);
        return fragment;
    }

    public static ViewerBodyFragment newInstance(String documentId, String documentName, String revisionId) {
        ViewerBodyFragment fragment = new ViewerBodyFragment();
        Bundle args = new Bundle();
        args.putString("documentId", documentId);
        args.putString("documentName", documentName);
        args.putString("revisionId", revisionId);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            documentId = getArguments().getString("documentId");
            documentName = getArguments().getString("documentName");
            revisionId = getArguments().getString("revisionId");
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_viewer_body, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        logger.debug("ViewerBodyFragment", "ViewerBodyFragment.onViewCreated");

        RecyclerView recyclerView = view.findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        ViewerBodyAdapter viewerBodyAdapter = new ViewerBodyAdapter(new ItemClickListener() {
            @Override
            public void onItemClick(String fileName) {

            }

            @Override
            public void onItemLongClick(String fileName, int position) {

            }
        });
        recyclerView.setAdapter(viewerBodyAdapter);

        loadDocument(viewerBodyAdapter, documentId, revisionId);
    }

    private void loadDocument(ViewerBodyAdapter adapter, String documentId, String revisionId) {
        if (revisionId == null) { // load latest revision
            document.getDocument(documentId).thenAccept(document -> {
                getActivity().runOnUiThread(() -> {
                    adapter.setPages(document.getPages());
                    adapter.notifyDataSetChanged();
                });
            });
        } else { // load specified revision
            LacertaVcs vcs = lacertaVcsFactory.create(documentId);
            document.getDocumentPageListByFileNameList(documentId, vcs.getDocumentPagePathListRev(revisionId).join()).thenAccept(documentPageList -> {
                getActivity().runOnUiThread(() -> {
                    adapter.setPages(documentPageList);
                    adapter.notifyDataSetChanged();
                });
            });
        }
    }
}