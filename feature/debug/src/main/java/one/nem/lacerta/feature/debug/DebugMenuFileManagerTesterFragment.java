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

//            FileManager fileManager = fileManagerFactory.create(deviceInfoUtils.getExternalStorageDirectory());
//            fileManager.createDir(dirName);
        });

        view.findViewById(R.id.button_save_item).setOnClickListener(v -> {
//            FileManager fileManager = fileManagerFactory.create(deviceInfoUtils.getExternalStorageDirectory());
//            fileManager.createDir("test");
//            fileManager.changeDir("test");
//            Bitmap bitmap = Bitmap.createBitmap(1000, 1000, Bitmap.Config.ARGB_8888);
//            // Bitmapに描画処理を行う
//            Canvas canvas = new Canvas(bitmap);
//            // 大きな山の形状を作成
//            android.graphics.Path bigMountainPath = new android.graphics.Path();
//            bigMountainPath.moveTo(100, 800); // 左下の開始点
//            bigMountainPath.lineTo(500, 300); // 頂点
//            bigMountainPath.lineTo(900, 800); // 右下
//            bigMountainPath.close(); // パスを閉じる
//
//            // 山の描画設定
//            Paint mountainPaint = new Paint();
//            mountainPaint.setColor(Color.GREEN);
//            mountainPaint.setStyle(Paint.Style.FILL);
//
//            // 大きな山を描画
//            canvas.drawPath(bigMountainPath, mountainPaint);
//
//            // 小さな山の形状を作成
//            android.graphics.Path smallMountainPath = new android.graphics.Path();
//            smallMountainPath.moveTo(400, 800); // 左下の開始点
//            smallMountainPath.lineTo(650, 400); // 頂点
//            smallMountainPath.lineTo(900, 800); // 右下
//            smallMountainPath.close(); // パスを閉じる
//
//            Paint smallMountainPaint = new Paint();
//            smallMountainPaint.setColor(Color.parseColor("#006e54"));
//            smallMountainPaint.setStyle(Paint.Style.FILL);
//
//            // 小さな山を描画
//            canvas.drawPath(smallMountainPath, smallMountainPaint);
//            fileManager.saveBitmapAtCurrent(bitmap, "test.png");
        });

        return view;
    }
}