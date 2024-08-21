package org.codeprofile.shared.contracts;

public interface CustomException {
    String getMainMessage();

    String getContextInformation();

    int getExceptionCode();
}
