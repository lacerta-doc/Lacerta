package one.nem.lacerta.processor.module;

import dagger.assisted.Assisted;
import dagger.assisted.AssistedInject;
import one.nem.lacerta.model.document.DocumentDetail;

public class DocumentProcessorModule {

    private final DocumentDetail documentDetail;

    @AssistedInject
    public DocumentProcessorModule(@Assisted DocumentDetail documentDetail) {
        this.documentDetail = documentDetail;
    }

}
