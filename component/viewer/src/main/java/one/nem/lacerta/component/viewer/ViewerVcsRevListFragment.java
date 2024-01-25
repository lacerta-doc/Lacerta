package one.nem.lacerta.component.viewer;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import javax.inject.Inject;

import dagger.hilt.android.AndroidEntryPoint;
import one.nem.lacerta.utils.LacertaLogger;
import one.nem.lacerta.vcs.LacertaVcs;
import one.nem.lacerta.vcs.factory.LacertaVcsFactory;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ViewerVcsRevListFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
@AndroidEntryPoint
public class ViewerVcsRevListFragment extends Fragment {

    @Inject
    LacertaVcsFactory lacertaVcsFactory;

    @Inject
    LacertaLogger logger;

    LacertaVcs lacertaVcs;

    private String documentId;
    private String documentName;

    public ViewerVcsRevListFragment() {
        // Required empty public constructor
    }

    public static ViewerVcsRevListFragment newInstance(String documentId, String documentName) {
        ViewerVcsRevListFragment fragment = new ViewerVcsRevListFragment();
        Bundle args = new Bundle();
        args.putString("documentId", documentId);
        args.putString("documentName", documentName);
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
        return inflater.inflate(R.layout.fragment_viewer_vcs_rev_list, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        // Init arg
        if (getArguments() != null) {
            this.documentId = getArguments().getString("documentId");
            logger.debug("ViewerVcsRevListFragment", "documentId: " + this.documentId);
        }

        // Init vcs
        lacertaVcs = lacertaVcsFactory.create(this.documentId);

        // Init view
        RecyclerView recyclerView = view.findViewById(R.id.rev_list);

        // Init adapter
        RevAdapter revAdapter = new RevAdapter(revisionId -> {
            logger.debug("ViewerVcsRevListFragment", "Selected revisionId: " + revisionId);
            getParentFragmentManager().beginTransaction()
                    .replace(R.id.nav_host_fragment, ViewerListFragment.newInstance(this.documentId, this.documentName, revisionId))
                    .commit();
        });

        // Set adapter
        recyclerView.setAdapter(revAdapter);

        // Set layout manager
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        lacertaVcs.getRevisionHistory().thenAccept(revModels -> {
            logger.debug("ViewerVcsRevListFragment", "revModels.size(): " + revModels.size());
            getActivity().runOnUiThread(() -> {
                revAdapter.setRevModels(revModels);
                revAdapter.notifyDataSetChanged();
            });
        });
    }
}