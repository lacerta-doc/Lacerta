package one.nem.lacerta.data.module;

import dagger.Binds;
import dagger.Module;
import dagger.hilt.InstallIn;
import dagger.hilt.components.SingletonComponent;

import one.nem.lacerta.data.repository.Documents;
import one.nem.lacerta.data.impl.DocumentsImpl;

@Module
@InstallIn(SingletonComponent.class)
abstract public class DocumentsModule {

    @Binds
    public abstract Documents bindDocuments(DocumentsImpl documentsImpl);

}
