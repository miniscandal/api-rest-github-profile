package org.codeprofile.shared.contracts;

import org.codeprofile.shared.http.Request;
import org.codeprofile.shared.http.Response;

public interface Service {
    Response execute(Request request, Response response);
}
