package one.nem.lacerta.data.processor;

import org.eclipse.jgit.lib.Repository;

import one.nem.lacerta.model.document.DocumentDetail;

public interface DocumentProcessor {
    // TODO-rca: Initをここでやるべきか検討する？, Documentモデルを作るべきか検討する？

    void setDocumentDetail(DocumentDetail documentDetail);


}
