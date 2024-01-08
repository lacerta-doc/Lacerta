package one.nem.lacerta.utils.impl;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

import java.util.ArrayList;

import javax.inject.Inject;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import one.nem.lacerta.model.document.internal.XmlMetaModel;
import one.nem.lacerta.model.document.internal.XmlMetaPageModel;
import one.nem.lacerta.utils.XmlMetaParser;

import one.nem.lacerta.utils.LacertaLogger;

public class XmlMetaParserImpl implements XmlMetaParser{

    @Inject
    LacertaLogger logger;

    @Inject
    public XmlMetaParserImpl() {
    }

    @Override
    public XmlMetaModel deserialize(Document document) {
        logger.debug("deserialize", "called");
        try {
            Element rootElement = document.getDocumentElement();

            XmlMetaModel meta = new XmlMetaModel();

            meta.setTitle(rootElement.getElementsByTagName("title").item(0).getTextContent());
            meta.setAuthor(rootElement.getElementsByTagName("author").item(0).getTextContent());
            meta.setDescription(rootElement.getElementsByTagName("description").item(0).getTextContent());
            meta.setDefaultBranch(rootElement.getElementsByTagName("defaultBranch").item(0).getTextContent());

            ArrayList<XmlMetaPageModel> pages = new ArrayList<>();
            for(int i = 0; i < rootElement.getElementsByTagName("pages").getLength(); i++) {
                Element pageElement = (Element) rootElement.getElementsByTagName("page").item(i);
                XmlMetaPageModel page = new XmlMetaPageModel();
                page.setIndex(Integer.parseInt(pageElement.getElementsByTagName("index").item(0).getTextContent()));
                page.setFilename(pageElement.getElementsByTagName("filename").item(0).getTextContent());
                pages.add(page);
            }

            meta.setPages(pages);

            return meta;
        } catch (Exception e) {
            logger.error("deserialize", "something wrong");
            logger.trace("deserialize", e.getMessage());
        }
        return null;
    }
    @Override
    public Document serialize(XmlMetaModel meta) {
        logger.debug("serialize", "called");
        try {
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document document = builder.newDocument();

            Element rootElement = document.createElement("meta");

            appendElement(document, rootElement, "title", meta.getTitle());
            appendElement(document, rootElement, "author", meta.getAuthor());
            appendElement(document, rootElement, "description", meta.getDescription());
            appendElement(document, rootElement, "defaultBranch", meta.getDefaultBranch());

            Element pagesElement = document.createElement("pages");
            for(XmlMetaPageModel page : meta.getPages()) {
                Element pageElement = document.createElement("page");
                appendElement(document, pageElement, "index", String.valueOf(page.getIndex()));
                appendElement(document, pageElement, "filename", page.getFilename());
                pagesElement.appendChild(pageElement);
            }

            rootElement.appendChild(pagesElement);

            document.appendChild(rootElement);

            return document;
        } catch (Exception e) {
            logger.error("serialize", "something wrong");
            logger.trace("serialize", e.getMessage());
        }
        return null;
    }
    // Internal Methods
    private void appendElement(Document document, Element rootElement, String name, String textContent) {
        Element element = document.createElement(name);
        element.setTextContent(textContent);
        rootElement.appendChild(element);
    }
}
