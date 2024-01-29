package one.nem.lacerta.component.viewer;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.widget.ViewPager2;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.google.android.material.dialog.MaterialAlertDialogBuilder;

import javax.inject.Inject;

import dagger.hilt.android.AndroidEntryPoint;
import one.nem.lacerta.component.scanner.ScannerManagerActivity;
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

    private SharedViewModel sharedViewModel;

    private RecyclerView recyclerView;

    private int maxPage = 0;

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

        this.recyclerView = view.findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        sharedViewModel = new ViewModelProvider(requireActivity()).get(SharedViewModel.class);

        ViewPager2 viewPager2 = getActivity().findViewById(R.id.view_pager);
        viewPager2.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageSelected(int position) {
                super.onPageSelected(position);
                sharedViewModel.setCurrentFragmentPosition(position);

                if (maxPage > 0 && position >= maxPage) {
                    // out of range
                } else {
                    try {
                        recyclerView.scrollToPosition(position);
                    } catch (Exception e) {
                        logger.error("ViewerBodyFragment", "recyclerView.scrollToPosition");
                        logger.error("ViewerBodyFragment", e.getMessage());
                        logger.e_code("8beec84b-1f7d-4e1e-9364-9fcf00f3509a");

                    }
                }
            }
        });

        ViewerBodyAdapter viewerBodyAdapter = new ViewerBodyAdapter(new ItemClickListener() {
            @Override
            public void onItemClick(String fileName) {

            }

            @Override
            public void onItemLongClick(String fileName, int position) {
                MaterialAlertDialogBuilder builder = new MaterialAlertDialogBuilder(getActivity());
                builder.setTitle("ページを更新しますか？");

                builder.setPositiveButton("更新", (dialog, which) -> {
                    // ScannerをIntent
                    Intent intent = new Intent(getActivity(), ScannerManagerActivity.class);
                    intent.putExtra("update", true);
                    intent.putExtra("documentId", documentId);
                    intent.putExtra("index", position);
                    startActivity(intent);
                });
                builder.setNegativeButton("キャンセル", (dialog, which) -> {
                    // cancel
                    dialog.dismiss();
                });

                builder.show();
            }
        });
        recyclerView.setAdapter(viewerBodyAdapter);

        loadDocument(viewerBodyAdapter, documentId, revisionId);
    }

    @Override
    public void onResume() {
        super.onResume();
        logger.debug("ViewerBodyFragment", "ViewerBodyFragment.onResume");

        this.sharedViewModel = new ViewModelProvider(requireActivity()).get(SharedViewModel.class);
        this.recyclerView.scrollToPosition(sharedViewModel.getCurrentFragmentPosition());
    }

    private void loadDocument(ViewerBodyAdapter adapter, String documentId, String revisionId) {
        if (revisionId == null) { // load latest revision
            document.getDocument(documentId).thenAccept(document -> {
                this.maxPage = document.getPages().size();
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