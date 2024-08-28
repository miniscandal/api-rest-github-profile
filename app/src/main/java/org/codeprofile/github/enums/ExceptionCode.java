package org.codeprofile.github.enums;

public enum ExceptionCode {
    SERVICE_EXCEPTION(101);

    private final int code;

    ExceptionCode(int code) {
        this.code = code;
    }

    public int getCode() {
        return this.code;
    }
}
