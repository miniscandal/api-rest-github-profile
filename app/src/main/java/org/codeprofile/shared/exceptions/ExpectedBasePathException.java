package org.codeprofile.shared.exceptions;

import org.codeprofile.shared.contracts.CustomException;

public class ExpectedBasePathException extends Exception implements CustomException {
    private static final String MAIN_MESSAGE;
    private final int EXCEPTION_CODE = 3;

    private String contextInformation = "No context information";

    static {
        MAIN_MESSAGE = "Exception: The format of the base path is not as expected base on the context path";
    }

    public ExpectedBasePathException(Throwable cause) {
        super("", cause);
    }

    public ExpectedBasePathException(String contextInformation, Throwable cause) {
        super(contextInformation, cause);
        this.contextInformation = contextInformation;
    }

    public ExpectedBasePathException(String contextInformation) {
        super(contextInformation);
        this.contextInformation = contextInformation;
    }

    public String getMainMessage() {
        return MAIN_MESSAGE;
    }

    public String getContextInformation() {
        return this.contextInformation;
    }

    public int getExceptionCode() {
        return EXCEPTION_CODE;
    }
}
