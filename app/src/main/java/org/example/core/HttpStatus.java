package org.example.core;

import java.util.Map;
import java.util.HashMap;

public enum HttpStatus {
    OK(200, "OK"),
    NOT_FOUND(404, "Not Found"),
    NOT_FOUND_CONTEXT(405, "Not Found Context"),
    INTERNAL_SERVER_ERROR(500, "Internal Server Error"),
    INTERNAL_API_GIT_HUB_SERVER_ERROR(502, "Internal APi GitHub Server Error"),
    SERVICE_UNAVAILABLE(503, "Service Unavailable Due To Configuration Issues");

    private final int code;
    private final String message;

    private static final Map<Integer, HttpStatus> CODE_TO_STATUS_MAP = new HashMap<>();

    static {
        for (HttpStatus status : values()) {
            CODE_TO_STATUS_MAP.put(status.code, status);
        }
    }

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
        return CODE_TO_STATUS_MAP.getOrDefault(code, SERVICE_UNAVAILABLE);
    }
}
