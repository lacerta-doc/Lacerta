package one.nem.lacerta.data.impl;

import one.nem.lacerta.data.LacertaLibrary;
import one.nem.lacerta.model.LibraryItemPage;
import one.nem.lacerta.model.ListItem;
import one.nem.lacerta.model.ListItemType;
import one.nem.lacerta.model.document.DocumentDetail;

import one.nem.lacerta.model.document.DocumentMeta;
import one.nem.lacerta.model.document.path.DocumentPath;
import one.nem.lacerta.utils.LacertaLogger;

import com.github.javafaker.Faker;

import java.util.ArrayList;
import java.util.Objects;
import java.util.UUID;

import javax.inject.Inject;

public class LacertaLibraryStubImpl implements LacertaLibrary {

    @Inject
    LacertaLogger logger;

    Faker faker;

    public LacertaLibraryStubImpl() {
        faker = new Faker(); // Init Faker
        logger.debug("LibraryStub", "Initialized");
    }

    // Internal Methods

    // Generate Stub Data
    private LibraryItemPage generateStubLibraryItemPage(int limit, String pageId) {
        logger.debug("LibraryStub", "generateStubLibraryItemPage");
        ArrayList<ListItem> listItems = new ArrayList<>();
        int itemTotal = faker.number().numberBetween(1, limit); // 実際に返却するアイテム数を決定
        int folderTotal;
        // フォルダ数の抽選
        if (itemTotal > 4) {
            folderTotal = faker.number().numberBetween(1, itemTotal - 2);
        }
        else {
            if (itemTotal > 2) {
                folderTotal = 1;
            }
            else { // ドキュメント数がゼロにならないように
                folderTotal = 0;
            }
        }
        int documentTotal = itemTotal - folderTotal; // ドキュメント数を決定
        logger.debug("LibraryStub", "itemTotal: " + itemTotal);
        logger.debug("LibraryStub", "folderTotal: " + folderTotal);
        logger.debug("LibraryStub", "documentTotal: " + documentTotal);

        // フォルダを生成
        for (int i = 0; i < folderTotal; i++) {
            listItems.add(generateStubListItem(ListItemType.ITEM_TYPE_FOLDER));
        }
        // ドキュメントを生成
        for (int i = 0; i < documentTotal; i++) {
            listItems.add(generateStubListItem(ListItemType.ITEM_TYPE_DOCUMENT));
        }

        LibraryItemPage libraryItemPage = new LibraryItemPage();
        libraryItemPage.setListItems(listItems);
        if (pageId == null) {
            libraryItemPage.setPageId(UUID.randomUUID().toString());
        } else {
            libraryItemPage.setPageId(pageId);
        }
        libraryItemPage.setPageTitle("FakePage" + faker.number().digits(3));

        return libraryItemPage;
    }

    private ListItem generateStubListItem(ListItemType itemType) {
        if (itemType == ListItemType.ITEM_TYPE_FOLDER) {
            ListItem listItem = new ListItem();
            listItem.setTitle("FakeFolder" + faker.number().digits(3));
            listItem.setDescription("Updated at " + faker.date().toString());
            listItem.setItemType(ListItemType.ITEM_TYPE_FOLDER);
            listItem.setItemId(UUID.randomUUID().toString());
            return listItem;
        } else if (itemType == ListItemType.ITEM_TYPE_DOCUMENT) {
            ListItem listItem = new ListItem();
            listItem.setTitle("FakeDocument" + faker.book().title());
            listItem.setDescription("Updated at " + faker.date().toString());
            listItem.setItemType(ListItemType.ITEM_TYPE_DOCUMENT);
            listItem.setItemId(UUID.randomUUID().toString());
            return listItem;
        } else {
            return null;
        }
    }

    private LibraryItemPage getRecentDocumentPage(int limit) {
        int itemTotal = faker.number().numberBetween(1, limit);
        ArrayList<ListItem> listItems = new ArrayList<>();
        for (int i = 0; i < itemTotal; i++) {
            listItems.add(generateStubListItem(ListItemType.ITEM_TYPE_DOCUMENT));
        }
        // DescriptionからDateを抽出して新しい順にソート
        listItems.sort((a, b) -> {
            String aDate = a.getDescription().substring(11);
            String bDate = b.getDescription().substring(11);
            return bDate.compareTo(aDate);
        });
        LibraryItemPage libraryItemPage = new LibraryItemPage();
        libraryItemPage.setListItems(listItems);
        libraryItemPage.setPageId(UUID.randomUUID().toString());
        libraryItemPage.setPageTitle("RecentDocument");
        return libraryItemPage;
    }

    private DocumentDetail generateStubDocumentDetail(String id) throws IllegalArgumentException {

        if (Objects.isNull(id)) {
            throw new IllegalArgumentException("id is null");
        }

        DocumentMeta documentMeta = new DocumentMeta();
        documentMeta.setId(id);
        documentMeta.setTitle("FakeDocument" + faker.book().title());
        documentMeta.setCreatedAt(faker.date().birthday());
        documentMeta.setUpdatedAt(faker.date().birthday()); // TODO-rca: 更新日のほうが古くなることがあるのでなんとかする？
        ArrayList<String> tagIds = new ArrayList<>();

        DocumentDetail documentDetail = new DocumentDetail();
        documentDetail.setMeta(documentMeta);
        documentDetail.setPath(null); // TODO-rca: なんとかする
        documentDetail.setAuthor(faker.name().fullName());
        documentDetail.setRepository(null); // TODO-rca: なんとかする
        return documentDetail;
    }
    @Override
    public LibraryItemPage getRecentDocument(int limit) {
        return getRecentDocumentPage(limit);
    }

    @Override
    public LibraryItemPage getRecentDocument(int limit, int offset) {
        return getRecentDocumentPage(limit);
    }

    @Override
    public LibraryItemPage getLibraryPage(int limit) {
        return generateStubLibraryItemPage(limit, null);
    }

    @Override
    public LibraryItemPage getLibraryPage(int limit, int offset) {
        return generateStubLibraryItemPage(limit, null);
    }

    @Override
    public LibraryItemPage getLibraryPage(String pageId, int limit) {
        return generateStubLibraryItemPage(limit, pageId);
    }

    @Override
    public LibraryItemPage getLibraryPage(String pageId, int limit, int offset) {
        return generateStubLibraryItemPage(limit, pageId);
    }

    @Override
    public DocumentDetail getDocumentDetailById(String id) throws IllegalArgumentException {
        return generateStubDocumentDetail(id);
    }
}
