package one.nem.lacerta.feature.debug;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import dagger.hilt.android.AndroidEntryPoint;
import one.nem.lacerta.source.file.FileManager;
import one.nem.lacerta.source.file.factory.FileManagerFactory;

import one.nem.lacerta.utils.repository.DeviceInfoUtils;

import javax.inject.Inject;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link DebugMenuFileManagerTesterFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
@AndroidEntryPoint
public class DebugMenuFileManagerTesterFragment extends Fragment {

    public DebugMenuFileManagerTesterFragment() {
        // Required empty public constructor
    }

    @Inject
    FileManagerFactory fileManagerFactory;

    @Inject
    DeviceInfoUtils deviceInfoUtils;

    // TODO: Rename and change types and number of parameters
    public static DebugMenuFileManagerTesterFragment newInstance() {
        DebugMenuFileManagerTesterFragment fragment = new DebugMenuFileManagerTesterFragment();
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
        View view = inflater.inflate(R.layout.fragment_debug_menu_file_manager_tester, container, false);

        view.findViewById(R.id.button_create_directory).setOnClickListener(v -> {
            EditText editText = view.findViewById(R.id.edit_text_dir_name);
            String dirName = editText.getText().toString();

            FileManager fileManager = fileManagerFactory.create(deviceInfoUtils.getExternalStorageDirectory());
            fileManager.createDir(dirName);
        });

        view.findViewById(R.id.button_save_item).setOnClickListener(v -> {
            FileManager fileManager = fileManagerFactory.create(deviceInfoUtils.getExternalStorageDirectory());
            fileManager.createDir("test");
            fileManager.changeDir("test");
            Bitmap bitmap = Bitmap.createBitmap(100, 100, Bitmap.Config.ARGB_8888);
            // Bitmapに描画処理を行う
            Canvas canvas = new Canvas(bitmap);
            Paint paint = new Paint();
            paint.setColor(Color.RED);
            canvas.drawCircle(50, 50, 50, paint);

            fileManager.saveBitmapAtCurrent(bitmap, "test.png");
        });

        return view;
    }
}