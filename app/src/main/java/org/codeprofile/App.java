/* 
 * Responsibility:
 *
 * Defines the Rest API endpoints with their respective controllers.
 */
package org.codeprofile;

import org.codeprofile.core.Route;
import org.codeprofile.github.http.controllers.ProfileController;

public class App {
    public static void main(String[] args) {
        Route.get("/profiles/{name}", new ProfileController());
    }
}
