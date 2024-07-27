package org.example.core;

public enum HttpStatus {
    OK(200, "OK"),
    NOT_FOUND(404, "OK"),
    INTERNAL_SERVER_ERROR(500, "OK");

    private final int code;
    private final String message;

    HttpStatus(int code, String message) {
        this.code = code;
        this.message = message;
    }

    public int getCode() {
        return this.code;
    }

    public String getMessage() {
        return this.message;
    }

    public static HttpStatus fromCode(int code) {
        for (HttpStatus status : values()) {
            if (status.getCode() == code) {
                return status;
            }
        }

        return null;
    }
}
