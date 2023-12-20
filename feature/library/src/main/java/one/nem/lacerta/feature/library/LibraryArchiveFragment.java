package one.nem.lacerta.feature.library;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class LibraryArchiveFragment extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_library_top);

        TextView textView = findViewById(R.id.document_list);

        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 文字をタップしたときの処理
                Intent intent = new Intent(LibraryArchiveFragment.this, LibraryArchiveFragment.class);
                startActivity(intent);
            }
        });
    }
}
