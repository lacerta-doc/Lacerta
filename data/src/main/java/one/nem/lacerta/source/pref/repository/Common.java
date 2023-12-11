package one.nem.lacerta.source.pref.repository;

import java.util.List;

public interface Common {
    // さまざまな用途で使うPref

    String getStringValue(String key);

    void setStringValue(String key, String value);

    boolean isExist(String key);

    void remove(String key);

    List<String> getExistKeys();
}
