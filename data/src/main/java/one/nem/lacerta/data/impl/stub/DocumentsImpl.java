package one.nem.lacerta.data.impl.stub;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.UUID;

import java.util.Date;

import javax.inject.Inject;

import one.nem.lacerta.data.model.documents.DocumentMeta;

import one.nem.lacerta.data.model.documents.enums.DocumentType;

import one.nem.lacerta.data.repository.Documents;

public class DocumentsImpl implements Documents {

        @Inject
        public DocumentsImpl() {
        }

        public ArrayList<DocumentMeta> getRecentDocuments(int limit) { // Generate dummy data
            ArrayList<DocumentMeta> documentMetaList = new ArrayList<>();
            for (int i = 0; i < limit; i++) {
                DocumentMeta documentMeta = new DocumentMeta();
                documentMeta.id = UUID.randomUUID().toString();
                documentMeta.name = "Document " + i;
                documentMeta.created = new Date();
                documentMeta.type = new Random(i).nextInt()/2 == 0 ? DocumentType.OTHER : DocumentType.NOTEBOOK;
                documentMeta.tags = new String[] {"tag1", "tag2", "tag3"};
                documentMeta.categories = new String[] {"category1", "category2", "category3"};
                documentMetaList.add(documentMeta);
            }

            return documentMetaList;
        }

        public ArrayList<DocumentMeta> getStarredDocuments(int limit) { // Generate dummy data
            ArrayList<DocumentMeta> documentMetaList = new ArrayList<>();
            for (int i = 0; i < limit; i++) {
                DocumentMeta documentMeta = new DocumentMeta();
                documentMeta.id = UUID.randomUUID().toString();
                documentMeta.name = "Document " + i;
                documentMeta.created = new Date();
                documentMeta.type = new Random(i).nextInt()/2 == 0 ? DocumentType.OTHER : DocumentType.NOTEBOOK;
                documentMeta.tags = new String[] {"tag1", "tag2", "tag3"};
                documentMeta.categories = new String[] {"category1", "category2", "category3"};
                documentMetaList.add(documentMeta);
            }

            return documentMetaList;
        }

}
