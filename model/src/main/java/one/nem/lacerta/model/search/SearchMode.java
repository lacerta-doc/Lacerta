package one.nem.lacerta.model.search;

public enum SearchMode {
    AUTO("auto"),
    EXACT("exact"),
    LIKE("like"),
    FULLTEXT("fulltext");

    private final String value;

    private SearchMode(String value) {
        this.value = value;
    }
}
