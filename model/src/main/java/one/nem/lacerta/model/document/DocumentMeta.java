package one.nem.lacerta.model.document;

import java.util.ArrayList;
import one.nem.lacerta.model.document.tag.DocumentTag;
import java.util.Date;

public class DocumentMeta {

        String id;
        String title;
        Date updatedAt;
        Date createdAt;
        ArrayList<DocumentTag> tags;

        // TODO-rca: Getter, Setter
}
