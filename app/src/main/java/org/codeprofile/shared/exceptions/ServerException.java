package org.codeprofile.shared.exceptions;

public class ServerException extends RuntimeException {
    private static final String MESSAGE = "The server could not be created or bind";

    public ServerException(Throwable cause) {
        super(MESSAGE, cause);
    }
}
