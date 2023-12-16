package one.nem.lacerta.utils.impl;

import android.util.Log;

import one.nem.lacerta.utils.LacertaLogger;
public class LacertaLoggerImpl implements LacertaLogger{

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
}
