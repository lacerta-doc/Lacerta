package one.nem.lacerta.model.document;

import java.nio.file.Path;
import java.util.Date;

import one.nem.lacerta.model.document.path.DocumentPath;
import one.nem.lacerta.model.document.DocumentMeta;

public class DocumentDetail {

    DocumentMeta meta;
    DocumentPath path;
    String author;
    String defaultBranch;
    Path fullPath;


}
