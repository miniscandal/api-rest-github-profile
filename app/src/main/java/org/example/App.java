/*
 */
package org.example;

import org.example.controllers.CommitController;
import org.example.controllers.ProfileController;
import org.example.controllers.RepoController;
import org.example.core.Routing;

public class App {
    public static void main(String... args) {
        Routing.setupContext("/profile/{name}", new ProfileController());
        Routing.setupContext("/repos/{name}", new RepoController());
        Routing.setupContext("/commits/{name}/{repo}", new CommitController());
        Routing.start();
    }
}
