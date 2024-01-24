package one.nem.lacerta.component.viewer.BookMark;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import javax.inject.Inject;

import one.nem.lacerta.component.viewer.R;

// BookMarkListFragment.java
public class BookMarkListFragment extends Fragment {
    @Inject
    BookmarkRepository bookmarkRepository;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_bookmark_list, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        // ブックマーク一覧を取得
        List<Bookmark> bookmarks = bookmarkRepository.getAllBookmarks();

        // ブックマーク一覧を表示するRecyclerViewのセットアップ
        RecyclerView recyclerView = view.findViewById(R.id.recycler_view);
        BookmarkAdapter adapter = new BookmarkAdapter(bookmarks, new OnBookmarkClickListener() {
            @Override
            public void onBookmarkClick(Bookmark selectedBookmark) {
                // ブックマークが選択された時の処理
                // 例: ブックマークに関連するページを表示する
            }
        });

        recyclerView.setLayoutManager(new LinearLayoutManager(requireContext()));
        recyclerView.setAdapter(adapter);
    }
}
