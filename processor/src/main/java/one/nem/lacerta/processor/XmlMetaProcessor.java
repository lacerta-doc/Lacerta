package one.nem.lacerta.processor;

import one.nem.lacerta.processor.model.XmlMetaModel;
import one.nem.lacerta.processor.model.XmlMetaPageModel;

public interface XmlMetaProcessor {

    void saveXmlMeta(XmlMetaModel xmlMetaModel);
    XmlMetaModel loadXmlMeta();
}
