package one.nem.lacerta.feature.debug;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import one.nem.lacerta.feature.debug.common.adapter.DebugMenuListItemAdapter;
import one.nem.lacerta.feature.debug.common.model.DebugMenuListItem;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link DebugMenuDocumentTesterTopFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class DebugMenuDocumentTesterTopFragment extends Fragment {
    public DebugMenuDocumentTesterTopFragment() {
        // Required empty public constructor
    }
    public static DebugMenuDocumentTesterTopFragment newInstance() {
        DebugMenuDocumentTesterTopFragment fragment = new DebugMenuDocumentTesterTopFragment();
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
        View view = inflater.inflate(R.layout.fragment_debug_menu_document_tester_top, container, false);

        RecyclerView recyclerView = view.findViewById(R.id.debug_menu_document_tester_recycler_view);
        recyclerView.setLayoutManager(new androidx.recyclerview.widget.LinearLayoutManager(getContext()));

        List<DebugMenuListItem> debugMenuDocTesterListItems = new ArrayList<>();

        debugMenuDocTesterListItems.add(new DebugMenuListItem("DocumentListFragment", "DocumentListFragment", R.id.action_debugMenuDocumentTesterTopFragment_to_debugMenuDocumentTesterListFragment, true));
        debugMenuDocTesterListItems.add(new DebugMenuListItem("DocumentManagerFragment", "DocumentManagerFragment", R.id.action_debugMenuDocumentTesterTopFragment_to_debugMenuDocumentTesterManageFragment, true));


        DebugMenuListItemAdapter adapter = new DebugMenuListItemAdapter(debugMenuDocTesterListItems);
        recyclerView.setAdapter(adapter);
        return view;

    }
}