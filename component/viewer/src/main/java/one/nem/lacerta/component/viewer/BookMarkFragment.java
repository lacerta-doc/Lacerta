package one.nem.lacerta.component.viewer;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import java.util.UUID;

import javax.inject.Inject;

// BookMarkFragment.java
public class BookMarkFragment extends Fragment {
    @Inject
    BookmarkRepository bookmarkRepository;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_bookmark, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        // ブックマーク追加ボタンがクリックされた時の処理
        view.findViewById(R.id.AddBookmark).setOnClickListener(new View.OnClickListener() {
        @Override
            public void onClick(View v) {
                String pageId = "current_page_id";
                String title = "current_page_title";

                // ブックマークを作成
            Bookmark bookmark = new Bookmark(
                    UUID.randomUUID().toString(),
                    title,
                    pageId,
                    System.currentTimeMillis()
            );

                // ブックマークを保存
                bookmarkRepository.addBookmark(bookmark);

                // ユーザーに成功メッセージを表示
            Toast.makeText(getContext(), "ブックマークが追加されました", Toast.LENGTH_SHORT).show();
        }
        });
    }
}
