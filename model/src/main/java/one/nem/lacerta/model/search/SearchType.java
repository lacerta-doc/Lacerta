package one.nem.lacerta.model.search;

public enum SearchType {

    TITLE("title"),
    DESCRIPTION("description"),
    TAG("tag"),
    PATH("path"),
    CONTENT("content");

    private final String value;

    private SearchType(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

}
