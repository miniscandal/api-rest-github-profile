/* 
 * Responsibility:
 *
 * Defines the Rest API endpoints with their respective controllers.
 * Start the Rest API server.
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
        Route.startServer();
    }

    public String descendants() {
        return "descendants";
    }
}
