package org.codeprofile.shared.contracts;

import org.codeprofile.shared.http.Request;
import org.codeprofile.shared.http.Response;

public interface Service {
    void execute(Request request, Response response);
}
