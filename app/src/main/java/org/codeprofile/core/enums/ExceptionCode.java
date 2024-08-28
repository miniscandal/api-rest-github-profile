package org.codeprofile.core.enums;

public enum ExceptionCode {
    SERVER_EXCEPTION(1);

    private final int code;

    ExceptionCode(int code) {
        this.code = code;
    }

    public int getCode() {
        return this.code;
    }
}
