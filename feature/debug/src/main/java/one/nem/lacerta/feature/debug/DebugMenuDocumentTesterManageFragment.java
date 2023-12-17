package one.nem.lacerta.feature.debug;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.UUID;

import javax.inject.Inject;

import dagger.multibindings.IntKey;
import one.nem.lacerta.data.DocumentDebug;

import one.nem.lacerta.model.document.DocumentMeta;
import one.nem.lacerta.model.document.DocumentDetail;

import one.nem.lacerta.model.document.path.DocumentPath;
import one.nem.lacerta.model.document.tag.DocumentTag;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link DebugMenuDocumentTesterManageFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class DebugMenuDocumentTesterManageFragment extends Fragment {

    @Inject
    DocumentDebug documentDebug;

    public DebugMenuDocumentTesterManageFragment() {
        // Required empty public constructor
    }

    public static DebugMenuDocumentTesterManageFragment newInstance() {
        DebugMenuDocumentTesterManageFragment fragment = new DebugMenuDocumentTesterManageFragment();
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
        View view = inflater.inflate(R.layout.fragment_debug_menu_document_tester_manage, container, false);

        view.findViewById(R.id.button_insert_test_data).setOnClickListener( v -> {
            DocumentMeta meta = new DocumentMeta("test_title");
            DocumentPath path = new DocumentPath("root", "test_path");
            DocumentDetail detail = new DocumentDetail(meta, path, "test_author", "test_default_branch");

            documentDebug.insertDocument(meta, detail);
        });

        return view;
    }
}