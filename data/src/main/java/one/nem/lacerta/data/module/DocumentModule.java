package one.nem.lacerta.data.module;

import android.content.Context;

import dagger.Binds;
import dagger.Module;
import dagger.hilt.InstallIn;
import dagger.hilt.components.SingletonComponent;

import one.nem.lacerta.data.Document;
import one.nem.lacerta.data.impl.DocumentImpl;

@Module
@InstallIn(SingletonComponent.class) // TODO-rca: Singletonであるべきか考える
abstract public class DocumentModule {

    @Binds
    public abstract Document bindDocument(DocumentImpl documentImpl);

}
