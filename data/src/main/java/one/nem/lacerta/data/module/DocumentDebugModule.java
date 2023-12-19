package one.nem.lacerta.data.module;

import dagger.Binds;
import dagger.Module;
import dagger.hilt.InstallIn;
import dagger.hilt.components.SingletonComponent;
import one.nem.lacerta.data.DocumentDebug;
import one.nem.lacerta.data.impl.DocumentDebugImpl;

@Module
@InstallIn(SingletonComponent.class) // TODO-rca: Singletonでいいのか検討する
abstract public class DocumentDebugModule {

    @Binds
    public abstract DocumentDebug bindDocumentDebug(DocumentDebugImpl documentDebugImpl);
}
