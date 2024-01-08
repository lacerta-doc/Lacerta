package one.nem.lacerta.utils;

public interface LacertaLogger {

    void info(String tag, String message);
    void warn(String tag, String message);
    void error(String tag, String message);
    void debug(String tag, String message);
    void trace(String tag, String message);
    void fatal(String tag, String message);

}
