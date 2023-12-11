package one.nem.lacerta.data.repository;

import java.util.ArrayList;
import java.util.List;

import one.nem.lacerta.data.model.documents.DocumentDetail;
import one.nem.lacerta.data.model.documents.DocumentMeta;

public interface Documents {

    ArrayList<DocumentMeta> getRecentDocuments(int limit);

    ArrayList<DocumentMeta> getStarredDocuments(int limit);

}
