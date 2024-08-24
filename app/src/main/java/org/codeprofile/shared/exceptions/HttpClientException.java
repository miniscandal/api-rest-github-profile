package org.codeprofile.shared.exceptions;

import org.codeprofile.shared.contracts.CustomException;

public class HttpClientException extends Exception implements CustomException {
    private static final String MAIN_MESSAGE;
    private final int EXCEPTION_CODE = 51;

    private String contextInformation = "No context information";

    static {
        MAIN_MESSAGE = "Exception: The request executer to the service was not successful";
    }

    public HttpClientException(Throwable cause) {
        super("", cause);
    }

    public HttpClientException(String contextInformation, Throwable cause) {
        super(contextInformation, cause);
        this.contextInformation = contextInformation;
    }

    public HttpClientException(String contextInformation) {
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
