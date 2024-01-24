package one.nem.lacerta.component.viewer.BookMark;

// BookmarkRepository.java
import java.util.ArrayList;
import java.util.List;

public class BookmarkRepository {
    private List<Bookmark> bookmarks = new ArrayList<>();

    public List<Bookmark> getAllBookmarks() {
        return bookmarks;
    }

    public void addBookmark(Bookmark bookmark) {
        bookmarks.add(bookmark);
    }

    public void removeBookmark(Bookmark bookmark) {
        bookmarks.remove(bookmark);
    }
}
