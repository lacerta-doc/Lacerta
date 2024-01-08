package one.nem.lacerta.model;

public class KeyValueLog {

    String key;
    String value;

    public KeyValueLog(String key, String value) {
        this.key = key;
        this.value = value;
    }

    // Getter
    public String getKey() {
        return key;
    }

    public String getValue() {
        return value;
    }

    // Setter
    public void setKey(String key) {
        this.key = key;
    }

    public void setValue(String value) {
        this.value = value;
    }

}
