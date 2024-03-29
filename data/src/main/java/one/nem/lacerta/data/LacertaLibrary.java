package one.nem.lacerta.data;

import java.util.ArrayList;
import java.util.concurrent.CompletableFuture;

import one.nem.lacerta.model.ListItem;
import one.nem.lacerta.model.ListItemType;

import one.nem.lacerta.model.LibraryItemPage;
import one.nem.lacerta.model.PublicPath;
import one.nem.lacerta.model.document.DocumentDetail;
import one.nem.lacerta.model.document.tag.DocumentTag;
import one.nem.lacerta.model.pref.ToxiDocumentModel;

public interface LacertaLibrary {

    // Get History
    CompletableFuture<ArrayList<ListItem>> getRecentDocument(int limit);
    CompletableFuture<ArrayList<ListItem>> getRecentDocument(int limit, int offset);

    // Get Library Page
    CompletableFuture<LibraryItemPage> getLibraryPage(String pageId, int limit);
    CompletableFuture<LibraryItemPage> getLibraryPage(String pageId, int limit, int offset);

    // Get Folder List
    CompletableFuture<LibraryItemPage> getFolderList(String parentId);

    // Create Folder
    CompletableFuture<String> createFolder(String parentId, String name);

    // delete Folder
    CompletableFuture<Void> deleteFolder(String folderId);

    // Get Public Path
    CompletableFuture<PublicPath> getPublicPath(String itemId, ListItemType itemType);


    // Tag
    CompletableFuture<ArrayList<DocumentTag>> getTagList();

    CompletableFuture<ArrayList<DocumentTag>> getAppliedTagList(String documentId);

    CompletableFuture<Void> createTag(DocumentTag tag);

    CompletableFuture<Void> updateTag(DocumentTag tag);

    CompletableFuture<Void> deleteTag(String tagId);

    CompletableFuture<Void> addTagToDocument(String documentId, String tagId);

    CompletableFuture<Void> applyTagListToDocument(String documentId, ArrayList<DocumentTag> tagList);

    CompletableFuture<Void> removeTagFromDocument(String documentId, String tagId);

    // Combined Document

    CompletableFuture<Void> combineDocument(String parentId, String childId);

    CompletableFuture<Void> updateTitleCache(String parentId, String childId, String titleCache);

    CompletableFuture<Void> uncombineDocument(String parentId, String childId);

//    CompletableFuture<Void> combineDocument(String parentId, ArrayList<String> childIdList);
//
//    CompletableFuture<Void> uncombineDocument(String parentId, ArrayList<String> childIdList);

    CompletableFuture<ArrayList<ToxiDocumentModel>> getCombinedDocumentToxiList(String parentId);
}
