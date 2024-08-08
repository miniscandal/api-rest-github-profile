package org.codeprofile.shared.http;

import org.codeprofile.shared.interfaces.ServiceStrategy;

public class HandleService {
    public static Response execute(Controller controller, Request request, Response response) {
        if (controller instanceof ServiceStrategy) {
        }

        return response;
    }
}
