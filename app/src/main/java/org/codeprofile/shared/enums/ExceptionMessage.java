package org.codeprofile.shared.enums;

public enum ExceptionMessage {
    EXPECTED_ARGUMENT("Expected base path does not contain parameter"),
    REQUEST_NOT_SUCCESSFUL("Send request not successful"),
    BASE_PATH_FORMAT("The path format for an http server context is not valid"),
    SEND_RESPONSE("The response could not be sent to the client");

    private final String message;

    ExceptionMessage(String message) {
        this.message = message;
    }

    public String getMessage() {
        return this.message;
    }
}
