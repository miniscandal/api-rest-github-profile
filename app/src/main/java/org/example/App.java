/*
 */
package org.example;

import org.example.controllers.CommitController;
import org.example.controllers.ProfileController;
import org.example.controllers.RepoController;
import org.example.core.Routing;

public class App {
    public static void main(String... args) {
        Routing.createContext("/profile", new ProfileController());
        Routing.createContext("/repos", new RepoController());
        Routing.createContext("/commits", new CommitController());
        Routing.start();
    }
}
