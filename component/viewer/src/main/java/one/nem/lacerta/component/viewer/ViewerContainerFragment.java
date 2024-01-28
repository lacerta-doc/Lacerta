package one.nem.lacerta.component.viewer;

import android.os.Bundle;

import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.viewpager2.widget.ViewPager2;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import javax.inject.Inject;

import dagger.hilt.android.AndroidEntryPoint;
import one.nem.lacerta.data.LacertaLibrary;
import one.nem.lacerta.model.document.page.Page;
import one.nem.lacerta.model.pref.ToxiDocumentModel;
import one.nem.lacerta.utils.LacertaLogger;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ViewerContainerFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
@AndroidEntryPoint
public class ViewerContainerFragment extends Fragment {

    // Inject

    @Inject
    LacertaLogger logger;

    @Inject
    LacertaLibrary lacertaLibrary;

    // Variables
    private String documentId;
    private String documentName;
    private boolean hasCombined = false;

    public ViewerContainerFragment() {
        // Required empty public constructor
    }

    public static ViewerContainerFragment newInstance(String documentId, String documentName, boolean hasCombined) {
        ViewerContainerFragment fragment = new ViewerContainerFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        args.putString("documentId", documentId);
        args.putString("documentName", documentName);
        args.putBoolean("hasCombined", hasCombined);
        return fragment;
    }

    public static ViewerContainerFragment newInstance(String documentId, String documentName) {
        ViewerContainerFragment fragment = new ViewerContainerFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        args.putString("documentId", documentId);
        args.putString("documentName", documentName);
        args.putBoolean("hasCombined", false);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null){
            documentId = getArguments().getString("documentId");
            documentName = getArguments().getString("documentName");
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_viewer_container, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        // Init view pager
        ViewPager2 viewPager = view.findViewById(R.id.view_pager);

        // Init view pager adapter
        ViewerViewPagerAdapter viewerViewPagerAdapter = new ViewerViewPagerAdapter(requireActivity());
        viewPager.setAdapter(viewerViewPagerAdapter);

        if (this.hasCombined) {
            lacertaLibrary.getCombinedDocumentToxiList(documentId).thenAccept(combinedDocumentToxiList -> {
                for (ToxiDocumentModel toxiDocumentModel : combinedDocumentToxiList) {
                    viewerViewPagerAdapter
                            .addFragment(ViewerBodyFragment.newInstance(toxiDocumentModel.getChildDocumentId(), toxiDocumentModel.getTitleCache()),
                                    toxiDocumentModel.getTitleCache());
                }
            });
        } else {
            viewerViewPagerAdapter.addFragment(ViewerBodyFragment.newInstance(documentId, documentName), documentName);
        }
    }

    /**
     * Toolbarをinitする
     *
     * @param toolbar Toolbar
     * @param showCloseButton Closeボタンを表示するかどうか
     * @param title タイトル
     */
    private void initToolbar(Toolbar toolbar, boolean showCloseButton, String title) {
        getActivity().runOnUiThread(() -> {
            // Close button
            if (showCloseButton) {
                toolbar.setNavigationIcon(one.nem.lacerta.shared.ui.R.drawable.close_24px);
                toolbar.setNavigationOnClickListener(v -> {
                    getActivity().finish();
                });
            } else {
                toolbar.setNavigationIcon(null);
            }

            // Title
            toolbar.setTitle(title);

            // Menu
            toolbar.inflateMenu(R.menu.viewer_menu);
            toolbar.setOnMenuItemClickListener(item -> {
                if (item.getItemId() == R.id.action_open_vcs_rev_list) {
                    Toast.makeText(getContext(), "Work in progress", Toast.LENGTH_SHORT).show();
                    return true;
                } else if (item.getItemId() == R.id.action_rename) {
                    Toast.makeText(getContext(), "Work in progress", Toast.LENGTH_SHORT).show();
                    return true;
                } else if (item.getItemId() == R.id.action_delete) {
                    Toast.makeText(getContext(), "Work in progress", Toast.LENGTH_SHORT).show();
                    return true;
                } else if (item.getItemId() == R.id.action_move) {
                    Toast.makeText(getContext(), "Work in progress", Toast.LENGTH_SHORT).show();
                    return true;
                } else {
                    return false;
                }
            });
        });
    }
}