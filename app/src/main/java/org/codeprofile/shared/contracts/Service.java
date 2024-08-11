package org.codeprofile.shared.contracts;

import org.codeprofile.shared.integration.Request;
import org.codeprofile.shared.integration.Response;

public interface Service {
    void execute(Request request, Response response);
}
