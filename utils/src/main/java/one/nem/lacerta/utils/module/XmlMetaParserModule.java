package one.nem.lacerta.utils.module;


import dagger.Binds;
import dagger.Module;
import dagger.hilt.InstallIn;
import dagger.hilt.components.SingletonComponent;

import one.nem.lacerta.utils.XmlMetaParser;
import one.nem.lacerta.utils.impl.XmlMetaParserImpl;
@Module
@InstallIn(SingletonComponent.class)
abstract public class XmlMetaParserModule {

    @Binds
    public abstract XmlMetaParser bindXmlMetaParser(XmlMetaParserImpl impl);
}
