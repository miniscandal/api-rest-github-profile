package org.codeprofile.shared.exceptions;

public class ExpectedBasePathException extends Exception {
    private static final String DEFAULT_MESSAGE;
    private static final int ERROR_CODE = 03;

    private final String contextInfo;

    static {
        DEFAULT_MESSAGE = "Exception Message: The format of the base path is not as expected base on the context path";
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

    public ExpectedBasePathException(Throwable cause) {
        super("Exception Message: " + DEFAULT_MESSAGE, cause);
        this.contextInfo = "Context Information: " + DEFAULT_MESSAGE;
    }

    public ExpectedBasePathException(String contextInfo, Throwable cause) {
        super("Context Information: " + contextInfo, cause);
        this.contextInfo = "Context Information: " + DEFAULT_MESSAGE;
    }

    public ExpectedBasePathException(String contextInfo) {
        super("Context Information: " + contextInfo);
        this.contextInfo = "Context Information: " + contextInfo;
    }
}
