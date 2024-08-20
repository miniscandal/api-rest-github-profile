package org.codeprofile.shared.exceptions;

public class ServerException extends RuntimeException {
    private static final String DEFAULT_MESSAGE;
    private static final int ERROR_CODE = 02;

    private final String contextInfo;

    static {
        DEFAULT_MESSAGE = "Exception Message: The server could not be created or bind";
    }

    public String getDefaultMessage() {
        return DEFAULT_MESSAGE;
    }

    public int getErrorCode() {
        return ERROR_CODE;
    }

    public String getContextInfo() {
        return this.contextInfo;
    }

    public ServerException(Throwable cause) {
        super("Exception Message: " + DEFAULT_MESSAGE, cause);
        this.contextInfo = "Context Information: " + DEFAULT_MESSAGE;
    }

    public ServerException(String contextInfo, Throwable cause) {
        super("Context Information: " + contextInfo, cause);
        this.contextInfo = "Context Information: " + DEFAULT_MESSAGE;
    }

    public ServerException(String contextInfo) {
        super("Context Information: " + contextInfo);
        this.contextInfo = "Context Information: " + contextInfo;
    }
}
