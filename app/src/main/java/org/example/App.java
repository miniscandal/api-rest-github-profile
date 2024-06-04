/*
 */
package org.example;

import org.example.controllers.ProfileController;
import org.example.controllers.RepoController;
import org.example.core.Routing;

public class App {
    public static void main(String... args) {
        Routing.createContext("/user", new ProfileController());
        Routing.createContext("/repo", new RepoController());
        Routing.start();
    }
}
