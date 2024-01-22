package one.nem.lacerta.component.viewer;

import android.graphics.Bitmap;
import android.os.Bundle;

import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.concurrent.CompletableFuture;

import javax.inject.Inject;

import dagger.hilt.android.AndroidEntryPoint;
import one.nem.lacerta.data.Document;
import one.nem.lacerta.model.document.DocumentDetail;
import one.nem.lacerta.model.document.page.Page;
import one.nem.lacerta.utils.FeatureSwitch;
import one.nem.lacerta.utils.LacertaLogger;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ComponentViewerTopFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
@AndroidEntryPoint
public class ComponentViewerTopFragment extends Fragment {

    @Inject
    Document document;

    @Inject
    LacertaLogger logger;

    private static final String TAG = "ComponentViewerTopFragment";

    private String documentId;
    private String documentName;

    public ComponentViewerTopFragment() {
        // Required empty public constructor
    }

    public static ComponentViewerTopFragment newInstance(String documentId, String documentName) {
        ComponentViewerTopFragment fragment = new ComponentViewerTopFragment();
        Bundle args = new Bundle();
        args.putString("documentId", documentId);
        args.putString("documentName", documentName);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            documentId = getArguments().getString("documentId");
            String documentName = getArguments().getString("documentName");
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_component_viewer_top, container, false);

        // Toolbar
        Toolbar toolbar = view.findViewById(R.id.toolbar);
        toolbarSetup(toolbar, true, this.documentName == null ? "Document" : this.documentName);

        RecyclerView recyclerView = view.findViewById(R.id.body_recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        ViewerBodyAdapter viewerBodyAdapter = new ViewerBodyAdapter(fileName -> {
            Toast.makeText(getContext(), fileName, Toast.LENGTH_SHORT).show();
        });
        recyclerView.setAdapter(viewerBodyAdapter);

        if (FeatureSwitch.Viewer.showProgressBarWhenLoading) view.findViewById(R.id.loading_progress_bar).setVisibility(View.VISIBLE);
        document.getDocument(documentId).thenAccept(documentDetail -> {
            ArrayList<Page> pages = documentDetail.getPages();
            logger.debug(TAG, "pages.size(): " + pages.size());
            viewerBodyAdapter.setPages(pages);
            getActivity().runOnUiThread(() -> {
                viewerBodyAdapter.notifyItemRangeChanged(0, pages.size());
                if (FeatureSwitch.Viewer.showProgressBarWhenLoading) view.findViewById(R.id.loading_progress_bar).setVisibility(View.GONE);
            });
        });

        return view;
    }

    /**
     * ToolbarをInitする
     *
     * @param toolbar Toolbar
     * @param showBackButton 戻るボタンを表示するか
     * @param title タイトル
     */
    private void toolbarSetup(Toolbar toolbar, boolean showBackButton, String title) {
        getActivity().runOnUiThread(() -> {
            if (showBackButton) {
                toolbar.setNavigationIcon(one.nem.lacerta.shared.ui.R.drawable.arrow_back_24px);
                toolbar.setNavigationOnClickListener(v -> {
                    // Stop Activity
                    getActivity().finish();
                });
            } else {
                toolbar.setNavigationIcon(null);
            }
            toolbar.setTitle(title);
            toolbar.inflateMenu(R.menu.viewer_menu);
            toolbar.setOnMenuItemClickListener(item -> {
                if (item.getItemId() == R.id.action_open_vcs_rev_list) {
                    // Open vcs rev list
                    getParentFragmentManager().beginTransaction()
                            .replace(R.id.nav_host_fragment, ViewerVcsRevListFragment.newInstance(documentId))
                            .commit();
                    return true;
                } else {
                    return false;
                }
            });
        });
    }
}