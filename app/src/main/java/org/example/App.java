/*
 */
package org.example;

import org.example.controllers.CommitController;
import org.example.controllers.ProfileController;
import org.example.controllers.RepoController;
import org.example.core.Route;

public class App {
    public static void main(String... args) {
        Route.registerEndPoint("/profile/{name}", new ProfileController());
        Route.registerEndPoint("/repos/{name}", new RepoController());
        Route.registerEndPoint("/commits/{name}/{repo}", new CommitController());
        Route.launchServer();
    }
}
