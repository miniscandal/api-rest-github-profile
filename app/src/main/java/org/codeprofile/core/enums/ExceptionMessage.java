package org.codeprofile.core.enums;

public enum ExceptionMessage {
    SERVER_BIND("Error creating and binding server");

    private final String message;

    ExceptionMessage(String message) {
        this.message = message;
    }

    public String getMessage() {
        return this.message;
    }
}
