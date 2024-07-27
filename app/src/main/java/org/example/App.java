/* 
 * Responsibility:
 *
 * Defines the Rest API endpoints with their respective controllers.
 */
package org.example;

import org.example.controllers.CommitController;
import org.example.controllers.ProfileController;
import org.example.controllers.RepoController;
import org.example.core.Route;

public class App {
    public static void main(String... args) {
        Route.get("/profiles/{name}", new ProfileController());
        Route.get("/repos/{name}", new RepoController());
        Route.get("/commits/{name}/{repo}", new CommitController());
    }

    public String descendants() {
        return "descendants";
    }
}
