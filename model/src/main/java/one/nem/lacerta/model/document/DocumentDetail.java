package one.nem.lacerta.model.document;

import org.eclipse.jgit.lib.Repository;

import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Date;

import one.nem.lacerta.model.document.path.DocumentPath;
import one.nem.lacerta.model.document.DocumentMeta;

import one.nem.lacerta.model.document.page.Page;

/**
 * ドキュメントの詳細データ
 */
public class DocumentDetail {

    /**
     * ドキュメントのメタデータ(DocumentMetaインスタンス)
     */
    DocumentMeta meta;

    /**
     * ドキュメントのページ(Pageインスタンスのリスト)
     */
    ArrayList<Page> pages;

    // Constructor
    public DocumentDetail() {
    }

    public DocumentDetail(DocumentMeta meta) {
        this.meta = meta;
    }

    public DocumentDetail(DocumentMeta meta, ArrayList<Page> pages) {
        this.meta = meta;
        this.pages = pages;
    }

    // Getter

    /**
     * ドキュメントのメタデータ(DocumentMetaインスタンス)を取得する
     */
    public DocumentMeta getMeta() {
        return meta;
    }

    /**
     * ドキュメントのページ(Pageインスタンスのリスト)を取得する
     */
    public ArrayList<Page> getPages() {
        return pages;
    }

    // Setter

    /**
     * ドキュメントのメタデータ(DocumentMetaインスタンス)を設定する
     * @param meta DocumentMetaインスタンス
     */
    public void setMeta(DocumentMeta meta) {
        this.meta = meta;
    }

    /**
     * ドキュメントのページ(Pageインスタンスのリスト)を設定する
     * @param pages Pageインスタンスのリスト
     */
    public void setPages(ArrayList<Page> pages) {
        this.pages = pages;
    }


    }

