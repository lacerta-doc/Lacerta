package one.nem.lacerta.feature.debug;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;
import android.widget.Toolbar;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.util.UUID;

import dagger.hilt.android.AndroidEntryPoint;
import javax.inject.Inject;

import one.nem.lacerta.utils.repository.DeviceInfoUtils;

import one.nem.lacerta.data.DocumentDebug; // Debug

import one.nem.lacerta.model.document.DocumentMeta;
import one.nem.lacerta.model.document.DocumentDetail;
import one.nem.lacerta.model.document.path.DocumentPath;
import one.nem.lacerta.model.document.tag.DocumentTag;


/**
 * A simple {@link Fragment} subclass.
 * Use the {@link DebugMenuFragment#newInstance} factory method to
 * create an instance of this fragment.
 */

@AndroidEntryPoint
public class DebugMenuFragment extends Fragment {
    @Inject
    DeviceInfoUtils deviceInfoUtils;

    @Inject
    DocumentDebug documentDebug; // Debug

    public DebugMenuFragment() {
        // Required empty public constructor
    }

    public static DebugMenuFragment newInstance() {
        DebugMenuFragment fragment = new DebugMenuFragment();
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
        return inflater.inflate(R.layout.fragment_debug_menu, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        // Toolbarの設定
        // TODO-rca: Toolbarの設定を共通化する
        androidx.appcompat.widget.Toolbar toolbar = view.findViewById(R.id.toolbar);
        toolbar.setTitle("DebugMenu");

        view.findViewById(R.id.btn_debug_menu_scan).setOnClickListener( v -> {
            // スキャン機能呼び出し
            Toast.makeText(getContext(), "testMessage", Toast.LENGTH_SHORT).show();
        });

        view.findViewById(R.id.btn_debug_menu_shared_pref_editor).setOnClickListener( v -> {
            // Fragment移動
            Navigation.findNavController(view).navigate(R.id.action_debugMenuFragment_to_debugSharedPrefEditorFragment);
        });

        view.findViewById(R.id.btn_debug_menu_clear_pref).setOnClickListener( v -> {
            // SharedPrefClear
        });

        view.findViewById(R.id.btn_debug_menu_call_play_ground).setOnClickListener( v -> {
            Navigation.findNavController(view).navigate(R.id.action_debugMenuFragment_to_debugPlayGroundFragment);
        });

        view.findViewById(R.id.btn_debug_menu_get_external_path).setOnClickListener( v -> {
            Toast.makeText(getContext(), deviceInfoUtils.getExternalStorageDirectory().toString(), Toast.LENGTH_SHORT).show();
        });

        view.findViewById(R.id.btn_debug_menu_file_write_test).setOnClickListener( v -> {
            File file = getContext().getExternalFilesDir(null);
            // ファイル書き込みテスト
            try {
                File testFile = new File(file, "test.txt");
                testFile.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        });

        view.findViewById(R.id.btn_debug_menu_go_to_repository_debug).setOnClickListener(v -> {
            Navigation.findNavController(view).navigate(R.id.action_debugMenuFragment_to_debugRepositoryDebuggerFragment);
        });

        view.findViewById(R.id.btn_debug_menu_insert_test_data).setOnClickListener(v -> {
            // テストデータ挿入
            DocumentMeta meta = new DocumentMeta();
            DocumentDetail detail = new DocumentDetail();

            meta.setId(UUID.randomUUID().toString());
            meta.setTitle("testTitle");
            meta.setCreatedAt(new java.util.Date());
            meta.setUpdatedAt(new java.util.Date());
            meta.setTags(new java.util.ArrayList<DocumentTag>());

            detail.setAuthor("testAuthor");
            detail.setDefaultBranch("testDefaultBranch");
            detail.setMeta(meta);
            detail.setPath(new DocumentPath("testRootPath", "testPath"));

            documentDebug.insertDocument(meta, detail);
        });

        view.findViewById(R.id.btn_debug_menu_select_test_data).setOnClickListener(v -> {
            // Placeholder
        });
    }
}