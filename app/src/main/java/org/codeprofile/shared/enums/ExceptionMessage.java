package org.codeprofile.shared.enums;

public enum ExceptionMessage {
    EXPECTED_ARGUMENT("Expected base path does not contain parameter"),
    REQUEST_NOT_SUCCESSFUL("Send request not successful");

    private final String message;

    ExceptionMessage(String message) {
        this.message = message;
    }

    public String getMessage() {
        return this.message;
    }
}
