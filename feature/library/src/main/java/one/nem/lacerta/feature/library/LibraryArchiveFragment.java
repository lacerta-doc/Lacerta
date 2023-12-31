package one.nem.lacerta.feature.library;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

// LibraryArchiveFragment.java

//画面変移用のコード
//Fragmentへのデータの受け渡し機能
public class LibraryArchiveFragment extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_library_top);

        // ここに入力された内容を表示する機能
        TextView textView = findViewById(R.id.document_list);

        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 文字をタップしたときの処理

                // 移動先のアクティビティを指定
                Intent intent = new Intent(LibraryArchiveFragment.this, LibraryDocFragment.class);

                // データを付加する
                intent.putExtra("key_name", "value_data");

                startActivity(intent);
            }
        });
    }
}
