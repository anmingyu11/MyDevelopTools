// TODO Copyright
package org.videolan.vlc.util;

import android.util.Log;

import java.util.Locale;

public class LogUtil {
    public static boolean ENABLE_DEBUG = true;


    private static String getTag() {
        StackTraceElement[] trace = new Throwable().fillInStackTrace()
                .getStackTrace();
        String callingClass = "";
        for (int i = 2; i < trace.length; i++) {
            Class<?> clazz = trace[i].getClass();
            if (!clazz.equals(LogUtil.class)) {
                callingClass = trace[i].getClassName();
                callingClass = callingClass.substring(callingClass
                        .lastIndexOf('.') + 1);
                break;
            }
        }
        return callingClass;
    }

    private static String buildMessage(String msg) {
        StackTraceElement[] trace = new Throwable().fillInStackTrace()
                .getStackTrace();
        String caller = "";
        for (int i = 2; i < trace.length; i++) {
            Class<?> clazz = trace[i].getClass();
            if (!clazz.equals(LogUtil.class)) {
                caller = trace[i].getMethodName();
                break;
            }
        }
        return String.format(Locale.US, "[%d] %s: %s", Thread.currentThread()
                .getId(), caller, msg);
    }

    public static void v(String msg) {
        if (ENABLE_DEBUG) {
            Log.v(getTag(), buildMessage(msg));
        }
    }

    public static void d(String msg) {
        if (ENABLE_DEBUG) {
            Log.d(getTag(), buildMessage(msg));
        }
    }

    public static void i(String msg) {
        if (ENABLE_DEBUG) {
            Log.i(getTag(), buildMessage(msg));
        }
    }

    public static void w(String msg) {
        if (ENABLE_DEBUG) {
            Log.w(getTag(), buildMessage(msg));
        }
    }

    public static void e(String msg) {
        if (ENABLE_DEBUG) {
            Log.e(getTag(), buildMessage(msg));
        }
    }

    public static void printTraceStack(String msg){
        Exception e = (msg != null) ? new Exception(msg) : new Exception("Stack state");
        e.printStackTrace();
    }
}
