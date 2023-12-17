package one.nem.lacerta.feature.debug;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import one.nem.lacerta.feature.debug.common.model.SettingMenuItem;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link DocumentManageDebugFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class DocumentManageDebugFragment extends Fragment {
    public DocumentManageDebugFragment() {
        // Required empty public constructor
    }

    public static DocumentManageDebugFragment newInstance() {
        DocumentManageDebugFragment fragment = new DocumentManageDebugFragment();
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
        View view = inflater.inflate(R.layout.fragment_document_manage_debug, container, false);

        RecyclerView recyclerView = view.findViewById(R.id.doc_editor_menu_recycler_view);

        List<SettingMenuItem> menuItems = List.of(
                new SettingMenuItem("test", )
        );

        recyclerView.setAdapter(new DebugDocumentMenuAdapter());

        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        return view;
    }

}