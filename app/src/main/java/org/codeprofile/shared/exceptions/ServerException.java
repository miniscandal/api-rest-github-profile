package org.codeprofile.shared.exceptions;

public class ServerException extends RuntimeException {
    private static final String MESSAGE = "Error creating Http server";

    public ServerException(Throwable cause) {
        super(MESSAGE, cause);
    }
}
