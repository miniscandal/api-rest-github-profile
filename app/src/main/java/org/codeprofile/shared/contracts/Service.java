package org.codeprofile.shared.contracts;

import org.codeprofile.shared.network.Request;
import org.codeprofile.shared.network.Response;

public interface Service {
    void execute(Request request, Response response);
}
