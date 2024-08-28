package org.codeprofile.shared.exceptions;

import org.codeprofile.shared.contracts.CustomException;

public class FailedSendResponse extends RuntimeException implements CustomException {
    private static final String MAIN_MESSAGE;
    private static final int EXCEPTION_CODE = 53;

    private String contextInformation;

    static {
        MAIN_MESSAGE = "Exception: Failed to send response";
    }

    public FailedSendResponse(Throwable cause) {
        super("", cause);
    }

    public FailedSendResponse(String contextInformation, Throwable cause) {
        super(contextInformation, cause);
        this.contextInformation = contextInformation;
    }

    public FailedSendResponse(String contextInformation) {
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
