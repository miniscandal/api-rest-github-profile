package org.codeprofile;

import org.codeprofile.core.network.Route;
import org.codeprofile.github.http.controllers.CommitController;
import org.codeprofile.github.http.controllers.ProfileController;
import org.codeprofile.github.http.controllers.RepoController;
import org.codeprofile.internal.http.controllers.AboutController;
import org.codeprofile.internal.http.controllers.DocumentationController;

public class App {
    public static void main(String[] args) {
        Route.get("/about", new AboutController());
        Route.get("/documentation", new DocumentationController());
        Route.get("/profiles/{name}", new ProfileController());
        Route.get("/repos/{profile}/{name}", new RepoController());
        Route.get("/commits/{profile}/{name}", new CommitController());
    }
}
