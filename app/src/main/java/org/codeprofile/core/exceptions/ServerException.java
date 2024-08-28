package org.codeprofile.core.exceptions;

import org.codeprofile.core.enums.ExceptionCode;
import org.codeprofile.shared.contracts.CustomException;

public class ServerException extends RuntimeException implements CustomException {
    private static final String MAIN_MESSAGE;
    private static final int EXCEPTION_CODE = ExceptionCode.SERVER_EXCEPTION.getCode();

    private String contextInformation;

    static {
        MAIN_MESSAGE = "Exception: The server could not be created or bind";
    }

    public ServerException(Throwable cause) {
        super("", cause);
    }

    public ServerException(String contextInformation, Throwable cause) {
        super(contextInformation, cause);
        this.contextInformation = contextInformation;
    }

    public ServerException(String contextInformation) {
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
