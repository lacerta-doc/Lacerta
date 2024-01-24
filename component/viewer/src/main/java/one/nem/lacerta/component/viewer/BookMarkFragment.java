package one.nem.lacerta.component.viewer;

import android.os.Bundle;
import android.view.GestureDetector;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import java.util.UUID;

import javax.inject.Inject;

// BookMarkFragment.java
public class BookMarkFragment extends Fragment {

    @Inject
    BookmarkRepository bookmarkRepository;

    private GestureDetector gestureDetector;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_bookmark, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        gestureDetector = new GestureDetector(getContext(), new GestureDetector.SimpleOnGestureListener() {
            @Override
            public boolean onDoubleTap(MotionEvent e) {
                // ダブルタップが検出された時の処理
                handleDoubleTap();
                return true;
            }
        });

        // ブックマークボタンがダブルタップされた時の処理
        view.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                return gestureDetector.onTouchEvent(event);
            }
        });
    }

    private void handleDoubleTap() {
        // ダブルタップが検出された時の処理
        // ブックマークを作成
        String pageId = "current_page_id";
        String title = "current_page_title";

        Bookmark bookmark = new Bookmark(
                UUID.randomUUID().toString(),
                title,
                pageId,
                System.currentTimeMillis()
        );

        // ブックマークを保存
        bookmarkRepository.addBookmark(bookmark);

        // ユーザーに成功メッセージを表示
        Toast.makeText(getContext(), "ダブルタップでブックマークが追加されました", Toast.LENGTH_SHORT).show();
    }
}
