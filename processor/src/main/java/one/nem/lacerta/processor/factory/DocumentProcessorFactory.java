package one.nem.lacerta.processor.factory;

import dagger.assisted.AssistedFactory;
import one.nem.lacerta.model.document.DocumentDetail;
import one.nem.lacerta.processor.impl.DocumentProcessorImpl;

@AssistedFactory
public interface DocumentProcessorFactory {
    DocumentProcessorImpl create(DocumentDetail documentDetail);
}
