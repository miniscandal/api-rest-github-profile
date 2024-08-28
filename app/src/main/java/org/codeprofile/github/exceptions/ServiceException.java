package org.codeprofile.github.exceptions;

import org.codeprofile.github.enums.ExceptionCode;
import org.codeprofile.shared.contracts.CustomException;

public class ServiceException extends Exception implements CustomException {
    private static final String MAIN_MESSAGE;
    private final int EXCEPTION_CODE = ExceptionCode.SERVICE_EXCEPTION.getCode();

    private String contextInformation = "No context information";

    static {
        MAIN_MESSAGE = "Exception: The connection to the GitHub API service could not be established";
    }

    public ServiceException(Throwable cause) {
        super("", cause);
    }

    public ServiceException(String contextInformation, Throwable cause) {
        super(contextInformation, cause);
        this.contextInformation = contextInformation;
    }

    public ServiceException(String contextInformation) {
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
