package src.sra.logging;

import src.sra.core.ApplicationSystem;
import src.sra.core.SystemManager;
import src.sra.utils.types.Singleton;

public class Log extends ApplicationSystem {

    @Singleton
    private static Log instance;

    public Log(SystemManager systemManager) {
        super(systemManager);
    }

    public static void error(Object message, Throwable cause) {
        instance.logMessage(System.Logger.Level.ERROR, withThrowable(message, cause));
    }

    public static void info(Object message) {
        instance.logMessage(System.Logger.Level.INFO, message);
    }

    private void logMessage(System.Logger.Level level, Object message) {
        System.out.println(level.toString() + ": " + message);
    }

    private static String withThrowable(Object message, Throwable throwable) {
        return message + getThrowableString(throwable);
    }

    private static String getThrowableString(Throwable throwable) {
        StringBuilder sb = new StringBuilder();
        Throwable cause = throwable;
        while (cause != null) {
            sb.append('\n').append('\t').append("cause: ");
            sb.append(cause);
            sb.append('\n').append(getStackTrace(cause.getStackTrace(), 0));
            cause = cause.getCause();
        }
        return sb.toString();
    }

    private static String getStackTrace(StackTraceElement[] stackTrace, int startIndex) {
        StringBuilder sb = new StringBuilder();
        for (int i = startIndex; i < stackTrace.length; i++) {
            sb.append('\t').append("at: ").append(stackTrace[i]).append('\n');
        }
        return sb.toString();
    }

    @Override
    protected void terminate() {

    }

    @Override
    protected void init() {

    }
}
