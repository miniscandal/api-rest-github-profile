package org.codeprofile.shared.enums;

public enum ExceptionCode {
    EXPECTED_BASE_PATH(51),
    HTTP_CLIENT_EXCEPTION(52),
    FAILED_SEND_RESPONSE(53),
    ILLEGAL_BASE_PATH_FORMAT(54);

    private final int code;

    ExceptionCode(int code) {
        this.code = code;
    }

    public int getCode() {
        return this.code;
    }
}
