/* 
 * Responsibility:
 *
 * Defines the Rest API endpoints with their respective controllers.
 */
package org.codeprofile;

import org.codeprofile.core.Route;
import org.codeprofile.about.http.controllers.AboutController;
import org.codeprofile.apigithub.http.controllers.CommitController;
import org.codeprofile.apigithub.http.controllers.ProfileController;
import org.codeprofile.apigithub.http.controllers.RepoController;

public class App {
    public static void main(String... args) {
        Route.get("/about", new AboutController());
        Route.get("/profiles/{name}", new ProfileController());
        Route.get("/repos/{name}", new RepoController());
        Route.get("/commits/{name}/{repo}", new CommitController());
    }

    public String descendants() {
        return "descendants";
    }
}
