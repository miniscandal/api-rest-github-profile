package org.codeprofile.shared.exceptions;

import org.codeprofile.shared.contracts.CustomException;
import org.codeprofile.shared.enums.ExceptionCode;

public class IllegalBasePathFormat extends RuntimeException implements CustomException {
    private static final String MAIN_MESSAGE;
    private static final int EXCEPTION_CODE = ExceptionCode.ILLEGAL_BASE_PATH_FORMAT.getCode();

    private String contextInformation;

    static {
        MAIN_MESSAGE = "Exception: Invalid base path format";
    }

    public IllegalBasePathFormat(Throwable cause) {
        super("", cause);
    }

    public IllegalBasePathFormat(String contextInformation, Throwable cause) {
        super(contextInformation, cause);
        this.contextInformation = contextInformation;
    }

    public IllegalBasePathFormat(String contextInformation) {
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
