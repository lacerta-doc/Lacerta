package one.nem.lacerta.model.document;

import org.eclipse.jgit.lib.Repository;

import java.nio.file.Path;
import java.util.Date;

import one.nem.lacerta.model.document.path.DocumentPath;
import one.nem.lacerta.model.document.DocumentMeta;

/**
 * ドキュメントの詳細データ
 */
public class DocumentDetail {

    /**
     * ドキュメントのメタデータ(DocumentMetaインスタンス)
     */
    DocumentMeta meta;

    // Constructor
    public DocumentDetail() {
    }

    public DocumentDetail(DocumentMeta meta) {
        this.meta = meta;
    }

    // Getter

    /**
     * ドキュメントのメタデータ(DocumentMetaインスタンス)を取得する
     */
    public DocumentMeta getMeta() {
        return meta;
    }

    // Setter

    /**
     * ドキュメントのメタデータ(DocumentMetaインスタンス)を設定する
     * @param meta DocumentMetaインスタンス
     */
    public void setMeta(DocumentMeta meta) {
        this.meta = meta;
    }

}
