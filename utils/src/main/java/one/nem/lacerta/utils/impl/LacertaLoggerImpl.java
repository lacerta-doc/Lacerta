package one.nem.lacerta.utils.impl;

import android.util.Log;

import javax.inject.Inject;

import one.nem.lacerta.utils.LacertaLogger;
import one.nem.lacerta.utils.model.KeyValueLog;

public class LacertaLoggerImpl implements LacertaLogger{

    @Inject
    public LacertaLoggerImpl() {
    }

    @Override
    public void info(String tag, String message) {
        Log.i(tag, message);
    }

    @Override
    public void warn(String tag, String message) {
        Log.w(tag, message);
    }

    @Override
    public void error(String tag, String message) {
        Log.e(tag, message);
    }

    @Override
    public void debug(String tag, String message) {
        Log.d(tag, message);
    }

    @Override
    public void trace(String tag, String message) {
        Log.v(tag, message);
    }

    @Override
    public void fatal(String tag, String message) {
        Log.wtf(tag, message);
    }

    @Override
    public String buildMessageByObject(KeyValueLog... logs) {
        StringBuilder builder = new StringBuilder();
        for (KeyValueLog log : logs) {
            builder.append(log.getKey());
            builder.append(": ");
            builder.append(log.getValue());
            builder.append("\n");
        }
        return builder.toString();
    }

    @Override
    public String buildMessageByObject(String name, KeyValueLog... logs) {
        StringBuilder builder = new StringBuilder();
        builder.append(name);
        builder.append("\n");
        for (KeyValueLog log : logs) {
            builder.append(log.getKey());
            builder.append(": ");
            builder.append(log.getValue());
            builder.append("\n");
        }
        return builder.toString();
    }
}
