package org.codeprofile.github.exceptions;

import org.codeprofile.shared.contracts.CustomException;

public class ServiceException extends Exception implements CustomException {
    private static final String MAIN_MESSAGE;
    private final int EXCEPTION_CODE = 101;

    private String contextInformation = "No context information";

    static {
        MAIN_MESSAGE = "Exception: La conexion con se serviico de la api de github no logro ralizar";
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
