/*
 */
package org.example;

import org.example.controllers.ProfileController;
import org.example.core.Routing;

public class App {
    public static void main(String... args) {
        Routing.setupContext("/profile/{name}", new ProfileController());
        Routing.start();
    }
}
