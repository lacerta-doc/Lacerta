package one.nem.lacerta.data.impl;

import android.nfc.Tag;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;

import javax.inject.Inject;

import one.nem.lacerta.data.LacertaLibrary;
import one.nem.lacerta.model.LibraryItemPage;
import one.nem.lacerta.model.ListItem;
import one.nem.lacerta.model.ListItemType;
import one.nem.lacerta.model.PublicPath;
import one.nem.lacerta.model.document.DocumentDetail;
import one.nem.lacerta.model.document.tag.DocumentTag;
import one.nem.lacerta.model.pref.ToxiDocumentModel;
import one.nem.lacerta.source.database.LacertaDatabase;
import one.nem.lacerta.source.database.common.DateTypeConverter;
import one.nem.lacerta.source.database.entity.DocumentEntity;
import one.nem.lacerta.source.database.entity.FolderEntity;
import one.nem.lacerta.source.database.entity.TagEntity;
import one.nem.lacerta.source.database.entity.ToxiDocumentEntity;
import one.nem.lacerta.source.database.entity.ToxiDocumentTagEntity;
import one.nem.lacerta.utils.FeatureSwitch;
import one.nem.lacerta.utils.LacertaLogger;

public class LacertaLibraryImpl implements LacertaLibrary {

    LacertaLogger logger;
    LacertaDatabase database;

    @Inject
    public LacertaLibraryImpl(LacertaLogger logger, LacertaDatabase database) {
        this.logger = logger;
        this.database = database;
    }

    @Override
    public CompletableFuture<ArrayList<ListItem>> getRecentDocument(int limit) {
        return CompletableFuture.supplyAsync(() -> {
            List<DocumentEntity> documentEntities = database.documentDao().getRecentDocument(limit);

            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
            ArrayList<ListItem> listItems = new ArrayList<>();
            for (DocumentEntity documentEntity : documentEntities) {
                ListItem listItem = new ListItem();
                listItem.setItemType(ListItemType.ITEM_TYPE_DOCUMENT);
                listItem.setTitle(documentEntity.title);
                listItem.setDescription(simpleDateFormat.format(documentEntity.updatedAt));
                listItem.setItemId(documentEntity.id);
                listItem.setHasCombined(documentEntity.isCombineParent);
                listItems.add(listItem);
            }

            return listItems;
        });
    }

    @Override
    public CompletableFuture<ArrayList<ListItem>> getRecentDocument(int limit, int offset) {
        return null; // TODO-rca: Implement
    }

    @Override
    public CompletableFuture<LibraryItemPage> getLibraryPage(String pageId, int limit) {
        return CompletableFuture.supplyAsync(() -> {
            LibraryItemPage libraryItemPage = new LibraryItemPage();

            List<FolderEntity> folderEntities;
            List<DocumentEntity> documentEntities;

            if (pageId == null) { // When root folder
                libraryItemPage.setPageTitle("ライブラリ");
                libraryItemPage.setPageId(null);
                libraryItemPage.setParentId(null);

                folderEntities = database.folderDao().findRootFolders();
                documentEntities = database.documentDao().findRootDocuments();
            } else {
                FolderEntity folderEntity = database.folderDao().findById(pageId);
                if (folderEntity == null) {
                    logger.warn("LacertaLibraryImpl", pageId + " is not found.");
                    return null;
                }
                libraryItemPage.setPageTitle(folderEntity.name);
                libraryItemPage.setPageId(folderEntity.id);
                libraryItemPage.setParentId(folderEntity.parentId);

                folderEntities = database.folderDao().findByParentId(pageId);
                documentEntities = database.documentDao().findByParentId(pageId);
            }

            ArrayList<ListItem> listItems = new ArrayList<>();
            for (FolderEntity childFolderEntity : folderEntities) {
                logger.debug("LacertaLibraryImpl", "childFolderEntity.name: " + childFolderEntity.name);
                ListItem listItem = new ListItem();
                listItem.setItemType(ListItemType.ITEM_TYPE_FOLDER);
                listItem.setTitle(childFolderEntity.name);
                listItem.setDescription("フォルダ"); // TODO-rca: ハードコーディングやめる
                listItem.setItemId(childFolderEntity.id);
                listItem.setHasCombined(false);
                listItems.add(listItem);
            }

            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");

            for (DocumentEntity documentEntity : documentEntities) {
                logger.debug("LacertaLibraryImpl", "documentEntity.title: " + documentEntity.title);
                ListItem listItem = new ListItem();
                listItem.setItemType(ListItemType.ITEM_TYPE_DOCUMENT);
                listItem.setTitle(documentEntity.title);
                listItem.setDescription(simpleDateFormat.format(documentEntity.updatedAt));
                listItem.setItemId(documentEntity.id);
                listItem.setHasCombined(documentEntity.isCombineParent);
                // タグを取得して関連付ける処理 TODO-rca: わかりにくい + 責任がめちゃくちゃ + めちゃくちゃ重いのでなんとかする
                List<ToxiDocumentTagEntity> documentTagEntities = database.toxiDocumentTagDao().findByDocumentId(documentEntity.id);
                ArrayList<DocumentTag> documentTags = new ArrayList<>();
                for (ToxiDocumentTagEntity toxiDocumentTagEntity : documentTagEntities) {
                    logger.debug("LacertaLibraryImpl", "toxiDocumentTagEntity.tagId: " + toxiDocumentTagEntity.tagId);
                    TagEntity tagEntity = database.tagDao().findById(toxiDocumentTagEntity.tagId);
                    if (tagEntity != null) {
                        logger.debug("LacertaLibraryImpl", "tagEntity.tagName: " + tagEntity.tagName);
                        documentTags.add(convertTagEntityToDocumentTag(tagEntity));
                    }
                }
                listItem.setTagList(documentTags);
                logger.debug("LacertaLibraryImpl", "documentTags.size(): " + documentTags.size());
                listItems.add(listItem);
            }

            libraryItemPage.setListItems(listItems);

            logger.debug("LacertaLibraryImpl", "libraryItemPage.getListItems().size(): " + libraryItemPage.getListItems().size());

            return libraryItemPage;
        });
    }

    @Override
    public CompletableFuture<LibraryItemPage> getLibraryPage(String pageId, int limit, int offset) {
        return CompletableFuture.supplyAsync(() -> {
            return null;
        });
    }

    @Override
    public CompletableFuture<LibraryItemPage> getFolderList(String targetDirId) {
        return CompletableFuture.supplyAsync(() -> {
            LibraryItemPage libraryItemPage = new LibraryItemPage();

            List<FolderEntity> folderEntities;
            if (targetDirId == null) { // When root folder
                folderEntities = database.folderDao().findRootFolders();
                libraryItemPage.setParentId(null);
                libraryItemPage.setPageId(null);
                libraryItemPage.setPageTitle("ライブラリ");
            } else {
                folderEntities = database.folderDao().findByParentId(targetDirId);
                FolderEntity folderEntity = database.folderDao().findById(targetDirId);
                if (folderEntity == null) {
                    logger.warn("LacertaLibraryImpl", targetDirId + " is not found.");
                    return null;
                }
                libraryItemPage.setParentId(folderEntity.parentId);
                libraryItemPage.setPageId(folderEntity.id);
                libraryItemPage.setPageTitle(folderEntity.name);
            }

            ArrayList<ListItem> listItems = new ArrayList<>();
            for (FolderEntity childFolderEntity : folderEntities) {
                logger.debug("LacertaLibraryImpl", "childFolderEntity.name: " + childFolderEntity.name);
                ListItem listItem = new ListItem();
                listItem.setItemType(ListItemType.ITEM_TYPE_FOLDER);
                listItem.setTitle(childFolderEntity.name);
                listItem.setDescription("フォルダ"); // TODO-rca: ハードコーディングやめる
                listItem.setItemId(childFolderEntity.id);
                listItem.setHasCombined(false);
                listItems.add(listItem);
            }


            libraryItemPage.setListItems(listItems);

            return libraryItemPage;
        });
    }

    @Override
    public CompletableFuture<String> createFolder(String parentId, String name) {
        return CompletableFuture.supplyAsync(() -> {
            FolderEntity folderEntity = new FolderEntity();
            folderEntity.id = UUID.randomUUID().toString();
            folderEntity.name = name;
            folderEntity.parentId = parentId;
            database.folderDao().insert(folderEntity);
            return folderEntity.id;
        });
    }

    @Override
    public CompletableFuture<PublicPath> getPublicPath(String itemId, ListItemType itemType) {
        return CompletableFuture.supplyAsync(() -> {
            if (itemType == ListItemType.ITEM_TYPE_DOCUMENT) {
                DocumentEntity documentEntity = database.documentDao().findById(itemId);
                if (documentEntity == null) {
                    logger.warn("LacertaLibraryImpl", itemId + " is not found.");
                    return null;
                }
                PublicPath publicPath = recursiveResolve(documentEntity.parentId);
                publicPath.resolve(documentEntity.title);
                return publicPath;
            } else if (itemType == ListItemType.ITEM_TYPE_FOLDER) {
                FolderEntity folderEntity = database.folderDao().findById(itemId);
                if (folderEntity == null) {
                    return null;
                }
                return recursiveResolve(folderEntity.id);
            } else {
                logger.warn("LacertaLibraryImpl", "Unknown ListItemType: " + itemType);
                return null;
            }
        });
    }

    // Converter
    private DocumentTag convertTagEntityToDocumentTag(TagEntity tagEntity) {
        DocumentTag documentTag = new DocumentTag();
        documentTag.setId(tagEntity.id);
        documentTag.setName(tagEntity.tagName);
        documentTag.setColor(tagEntity.color);
        return documentTag;
    }

    private TagEntity convertDocumentTagToTagEntity(DocumentTag documentTag) {
        TagEntity tagEntity = new TagEntity();
        tagEntity.id = documentTag.getId();
        tagEntity.tagName = documentTag.getName();
        tagEntity.color = documentTag.getColor();
        return tagEntity;
    }

    @Override
    public CompletableFuture<ArrayList<DocumentTag>> getTagList() {
        return CompletableFuture.supplyAsync(() -> {
            List<TagEntity> tagEntities = database.tagDao().findAll();
            logger.debug("LacertaLibraryImpl", "Database Query: Get TagEntity List (Size: " + tagEntities.size() + ")");
            ArrayList<DocumentTag> documentTags = new ArrayList<>();
            for (TagEntity tagEntity : tagEntities) {
                documentTags.add(convertTagEntityToDocumentTag(tagEntity));
            }
            return documentTags;
        });
    }

    @Override
    public CompletableFuture<ArrayList<DocumentTag>> getAppliedTagList(String documentId) {
        return CompletableFuture.supplyAsync(() -> {
            List<ToxiDocumentTagEntity> toxiDocumentTagEntities = database.toxiDocumentTagDao().findByDocumentId(documentId);
            logger.debug("LacertaLibraryImpl", "Database Query: Get ToxiDocumentTagEntity List (Size: " + toxiDocumentTagEntities.size() + ")");
            ArrayList<DocumentTag> documentTags = new ArrayList<>();
            for (ToxiDocumentTagEntity toxiDocumentTagEntity : toxiDocumentTagEntities) {
                TagEntity tagEntity = database.tagDao().findById(toxiDocumentTagEntity.tagId);
                if (tagEntity != null) {
                    documentTags.add(convertTagEntityToDocumentTag(tagEntity));
                }
            }
            return documentTags;
        });
    }

    @Override
    public CompletableFuture<Void> createTag(DocumentTag tag) {
        return CompletableFuture.supplyAsync(() -> {
            TagEntity tagEntity = convertDocumentTagToTagEntity(tag);
            if (Objects.isNull(tagEntity.id) || tagEntity.id.isEmpty()) {
                tagEntity.id = UUID.randomUUID().toString();
            }
            database.tagDao().insert(tagEntity);
            logger.debug("LacertaLibraryImpl", "Database Query: Inserted TagEntity (" + tag.getId() + ")");
            return null;
        });
    }

    @Override
    public CompletableFuture<Void> updateTag(DocumentTag tag) {
        return CompletableFuture.supplyAsync(() -> {
            TagEntity tagEntity = convertDocumentTagToTagEntity(tag);
            database.tagDao().update(tagEntity);
            logger.debug("LacertaLibraryImpl", "Database Query: Updated TagEntity (" + tag.getId() + ")");
            return null;
        });
    }

    @Override
    public CompletableFuture<Void> deleteTag(String tagId) {
        return CompletableFuture.supplyAsync(() -> {
            database.tagDao().deleteById(tagId);
            logger.debug("LacertaLibraryImpl", "Database Query: Deleted TagEntity (" + tagId + ")");
            return null;
        });
    }

    @Override
    public CompletableFuture<Void> addTagToDocument(String documentId, String tagId) {
        return CompletableFuture.supplyAsync(() -> {
            ToxiDocumentTagEntity toxiDocumentTagEntity = new ToxiDocumentTagEntity();
            toxiDocumentTagEntity.documentId = documentId;
            toxiDocumentTagEntity.tagId = tagId;
            database.toxiDocumentTagDao().insert(toxiDocumentTagEntity);
            logger.debug("LacertaLibraryImpl", "Database Query: Inserted ToxiDocumentTagEntity");
            return null;
        });
    }

    @Override
    public CompletableFuture<Void> applyTagListToDocument(String documentId, ArrayList<DocumentTag> tagArrayList) {
        return CompletableFuture.supplyAsync(() -> { // TODO-rca: 必要なものだけInsertするべき, 時間があれば...
            // 一旦全てのタグを削除
            database.toxiDocumentTagDao().deleteByDocumentId(documentId);
            logger.debug("LacertaLibraryImpl", "Database Query: Deleted ToxiDocumentTagEntity");
            // タグを追加
            for (DocumentTag documentTag : tagArrayList) {
                addTagToDocument(documentId, documentTag.getId());
            }
            return null;
        });
    }

    @Override
    public CompletableFuture<Void> removeTagFromDocument(String documentId, String tagId) {
        return CompletableFuture.supplyAsync(() -> {
            database.toxiDocumentTagDao().deleteByDocumentIdAndTagId(documentId, tagId);
            logger.debug("LacertaLibraryImpl", "Database Query: Deleted ToxiDocumentTagEntity");
            return null;
        });
    }

    @Override
    public CompletableFuture<Void> combineDocument(String targetId1, String tagetId2) {
        return CompletableFuture.supplyAsync(() -> {
            DocumentEntity target1DocumentEntity = database.documentDao().findById(targetId1);
            DocumentEntity target2DocumentEntity = database.documentDao().findById(tagetId2);
            if (target1DocumentEntity == null || target2DocumentEntity == null) {
                logger.warn("LacertaLibraryImpl", "DocumentEntity is not found.");
                return null;
            }
            target1DocumentEntity.isCombineChild = true;
            target2DocumentEntity.isCombineChild = true;
            database.documentDao().update(target1DocumentEntity);
            database.documentDao().update(target2DocumentEntity);
            logger.debug("LacertaLibraryImpl", "Database Query: Updated DocumentEntity");

            DocumentEntity parentDocumentEntity = new DocumentEntity();
            parentDocumentEntity.id = UUID.randomUUID().toString();
            parentDocumentEntity.title = target1DocumentEntity.title + "(Combined)";
            parentDocumentEntity.parentId = target1DocumentEntity.parentId;
            parentDocumentEntity.author = target1DocumentEntity.author; // TODO-rca: Target1とTarget2の作者が異なる場合の処理
            // CombinedなDocumentはいま作成された と考える
            parentDocumentEntity.createdAt = new Date();
            parentDocumentEntity.updatedAt = new Date();
            parentDocumentEntity.isCombineParent = true;
            parentDocumentEntity.isCombineChild = false;
            database.documentDao().insert(parentDocumentEntity);
            logger.debug("LacertaLibraryImpl", "Database Query: Inserted DocumentEntity");

            insertCombineDocumentToxi(parentDocumentEntity.id, target1DocumentEntity.id, target1DocumentEntity.title);
            insertCombineDocumentToxi(parentDocumentEntity.id, target2DocumentEntity.id, target2DocumentEntity.title);
            return null;
        });
    }

    private void insertCombineDocumentToxi(String parentId, String childId, String titleCache) {
        ToxiDocumentEntity toxiDocumentEntity = new ToxiDocumentEntity();
        toxiDocumentEntity.parentDocumentId = parentId;
        toxiDocumentEntity.childDocumentId = childId;
        toxiDocumentEntity.order = 0; // TODO-rca: 並び順の概念をもたせる
        toxiDocumentEntity.isActive = true; // TODO-rca: タブから非表示にできるようにする
        toxiDocumentEntity.titleCache = titleCache;
        database.toxiDocumentDao().insert(toxiDocumentEntity);
        logger.debug("LacertaLibraryImpl", "Database Query: Inserted ToxiDocumentEntity");
    }

    @Override
    public CompletableFuture<Void> updateTitleCache(String parentId, String documentId, String titleCache) {
        return CompletableFuture.supplyAsync(() -> {
            ToxiDocumentEntity toxiDocumentEntity = database.toxiDocumentDao().findByParentIdAndChildId(parentId, documentId);
            if (toxiDocumentEntity == null) {
                logger.warn("LacertaLibraryImpl", "ToxiDocumentEntity is not found.");
                return null;
            }
            toxiDocumentEntity.titleCache = titleCache;
            database.toxiDocumentDao().update(toxiDocumentEntity);
            logger.debug("LacertaLibraryImpl", "Database Query: Updated ToxiDocumentEntity");
            return null;
        });
    }

    @Override
    public CompletableFuture<Void> uncombineDocument(String parentId, String childId) {
        return CompletableFuture.supplyAsync(() -> {
            DocumentEntity parentDocumentEntity = database.documentDao().findById(parentId);
            DocumentEntity childDocumentEntity = database.documentDao().findById(childId);
            if (parentDocumentEntity == null || childDocumentEntity == null) {
                logger.warn("LacertaLibraryImpl", "DocumentEntity is not found.");
                return null;
            }
            parentDocumentEntity.isCombineParent = false;
            childDocumentEntity.isCombineChild = false;
            database.documentDao().update(parentDocumentEntity);
            database.documentDao().update(childDocumentEntity);
            logger.debug("LacertaLibraryImpl", "Database Query: Updated DocumentEntity");

            database.toxiDocumentDao().deleteByParentIdAndChildId(parentId, childId);
            logger.debug("LacertaLibraryImpl", "Database Query: Deleted ToxiDocumentEntity");
            return null;
        });
    }

    @Override
    public CompletableFuture<ArrayList<ToxiDocumentModel>> getCombinedDocumentToxiList(String parentId) {
        return CompletableFuture.supplyAsync(() -> {
            List<ToxiDocumentEntity> toxiDocumentEntities = database.toxiDocumentDao().findByParentId(parentId);
            ArrayList<ToxiDocumentModel> toxiDocumentModels = new ArrayList<>();
            for (ToxiDocumentEntity toxiDocumentEntity : toxiDocumentEntities) {
                ToxiDocumentModel toxiDocumentModel = new ToxiDocumentModel();
                toxiDocumentModel.setParentDocumentId(toxiDocumentEntity.parentDocumentId);
                toxiDocumentModel.setChildDocumentId(toxiDocumentEntity.childDocumentId);
                toxiDocumentModel.setOrder(toxiDocumentEntity.order);
                toxiDocumentModel.setActive(toxiDocumentEntity.isActive);
                toxiDocumentModel.setTitleCache(toxiDocumentEntity.titleCache);
                toxiDocumentModels.add(toxiDocumentModel);
            }
            return toxiDocumentModels;
        });
    }

    /**
     * 再帰的にパスを解決する
     *
     * @param folderId
     * @return
     */
    private PublicPath recursiveResolve(String folderId) {
        String current = folderId;
        boolean continueFlag = true;
        ArrayList<String> folderNames = new ArrayList<>();
        while (continueFlag) {
            FolderEntity folderEntity = database.folderDao().findById(current);
            if (folderEntity == null) { // 存在しないフォルダIDが指定された場合
                continueFlag = false;
            } else {
                folderNames.add(folderEntity.name);
                current = folderEntity.parentId;
                if (current == null) { // ルートフォルダに到達した場合
                    continueFlag = false;
                }
            }
        }

        // フォルダ名を逆順にしてListに変換
        Collections.reverse(folderNames);
        List<String> folderNamesReversed = new ArrayList<>(folderNames);

        return new PublicPath(folderNamesReversed);
    }
}
