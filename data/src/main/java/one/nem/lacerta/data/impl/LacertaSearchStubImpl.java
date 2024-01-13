package one.nem.lacerta.data.impl;

import java.util.ArrayList;

import javax.inject.Inject;

import one.nem.lacerta.data.LacertaSearch;
import one.nem.lacerta.model.ListItem;

import one.nem.lacerta.data.LacertaLibrary;
import one.nem.lacerta.utils.LacertaLogger;

public class LacertaSearchStubImpl implements LacertaSearch {

    private LacertaLibrary library;
    private LacertaLogger logger;


    @Inject
    public LacertaSearchStubImpl(LacertaLibrary library, LacertaLogger logger) {
        this.library = library;
        this.logger = logger;
    }

    /**
     * 検索
     * @param query 検索クエリ
     * @param limit 最大取得件数
     * @return 検索結果
     */
    @Override
    public ArrayList<ListItem> autoSearch(String query, int limit) {
        logger.debug("SearchStub", "autoSearch");
        logger.debug("SearchStub", "query: " + query);
        return library.getLibraryPage(limit).getListItems();
    }

    /**
     * 検索
     * @param query 検索クエリ
     * @param limit 最大取得件数
     * @param offset オフセット
     * @return 検索結果
     */
    @Override
    public ArrayList<ListItem> autoSearch(String query, int limit, int offset) {
        logger.debug("SearchStub", "autoSearch");
        logger.debug("SearchStub", "query: " + query);
        return library.getLibraryPage(limit, offset).getListItems();
    }
}
