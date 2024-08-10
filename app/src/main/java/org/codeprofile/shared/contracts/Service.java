package org.codeprofile.shared.contracts;

import org.codeprofile.shared.http.Request;
import org.codeprofile.shared.http.Response;
import org.codeprofile.shared.utils.ArgumentsBasePathBinder;

public interface Service {
    void execute(Request request, Response response, ArgumentsBasePathBinder argumentsBasepathBinder);
}
