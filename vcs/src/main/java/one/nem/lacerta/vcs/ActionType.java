package one.nem.lacerta.vcs;

public enum ActionType {

    INSERT_PAGE("insert_page"),
    UPDATE_PAGE("update_page"),
    DELETE_PAGE("delete_page"),
    UPDATE_PAGE_ORDER("update_page_order"),
    INSERT_PAGE_CONTENT("insert_page_content"),
    UPDATE_PAGE_CONTENT("update_page_content"),
    DELETE_PAGE_CONTENT("delete_page_content"),

    CREATE_BRANCH("create_branch"),
    DROP_BRANCH("drop_branch"),
    REBASE_BRANCH("rebase_branch"),

    CREATE_DOCUMENT("create_document"),
    DROP_DOCUMENT("drop_document"),
    UPDATE_DOCUMENT_META("update_document_meta"),
    OTHER("other");

    private final String value;

    ActionType(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public static ActionType fromValue(String value) {
        for (ActionType actionType : ActionType.values()) {
            if (actionType.getValue().equals(value)) {
                return actionType;
            }
        }
        return ActionType.OTHER;
    }
}
