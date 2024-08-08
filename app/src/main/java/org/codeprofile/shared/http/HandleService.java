package org.codeprofile.shared.http;

import org.codeprofile.github.contracts.ApiGitHub;

public class HandleService {
    public static Controller execute(Controller controller) {
        if (controller instanceof ApiGitHub) {
        }

        return controller;
    }
}
