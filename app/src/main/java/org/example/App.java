/*
 */
package org.example;

import org.example.controllers.CommitController;
import org.example.controllers.ProfileController;
import org.example.controllers.RepoController;
import org.example.core.Route;

public class App {
    public static void main(String... args) {
        Route.registerEndpoint("/profile/{name}", new ProfileController());
        Route.registerEndpoint("/repos/{name}", new RepoController());
        Route.registerEndpoint("/commits/{name}/{repo}", new CommitController());
        Route.launchServer();
    }

    public String descendants() {
        return "descendants";
    }
}
