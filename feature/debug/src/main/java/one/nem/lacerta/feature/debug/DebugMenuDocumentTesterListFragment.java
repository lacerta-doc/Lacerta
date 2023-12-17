package one.nem.lacerta.feature.debug;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.text.SimpleDateFormat;
import java.util.ArrayList;

import javax.inject.Inject;

import dagger.hilt.android.AndroidEntryPoint;
import one.nem.lacerta.data.Document;
import one.nem.lacerta.feature.debug.common.adapter.DebugMenuDocumentListItemAdapter;
import one.nem.lacerta.feature.debug.common.model.DebugMenuDocumentListItem;
import one.nem.lacerta.model.document.DocumentMeta;
import one.nem.lacerta.utils.LacertaLogger;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link DebugMenuDocumentTesterListFragment#newInstance} factory method to
 * create an instance of this fragment.
 */

@AndroidEntryPoint
public class DebugMenuDocumentTesterListFragment extends Fragment {

    @Inject
    Document document;

    @Inject
    LacertaLogger logger;

    public DebugMenuDocumentTesterListFragment() {
        // Required empty public constructor
    }

    public static DebugMenuDocumentTesterListFragment newInstance() {
        DebugMenuDocumentTesterListFragment fragment = new DebugMenuDocumentTesterListFragment();
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
        View view = inflater.inflate(R.layout.fragment_debug_menu_document_tester_list, container, false);

        RecyclerView recyclerView = view.findViewById(R.id.recycler_view_document_list);
        recyclerView.setLayoutManager(new androidx.recyclerview.widget.LinearLayoutManager(getContext()));

        ArrayList<DocumentMeta> documentMetas = document.getAllDocumentMetas(100);



        ArrayList<DebugMenuDocumentListItem> debugMenuDocumentListItems = new ArrayList<>();

        for (DocumentMeta documentMeta : documentMetas) {
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            debugMenuDocumentListItems.add(new DebugMenuDocumentListItem(documentMeta.getTitle(), "Internal Id: " + documentMeta.getId(), simpleDateFormat.format(documentMeta.getUpdatedAt())));
        }

        DebugMenuDocumentListItemAdapter adapter = new DebugMenuDocumentListItemAdapter(debugMenuDocumentListItems);
        recyclerView.setAdapter(adapter);

        return view;
    }
}