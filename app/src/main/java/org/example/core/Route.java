/**
 * Responsibility:
 * 
 * Initializes an HTTP server (singleton) within the application's lifecycle.
 * Configures and registers endpoints (URIs) along with their respective controllers.
 * Assigns properties to controllers to handle HTTP requests.
 * 
 * Usage:
 * 
 * Call `registerEndpoint` to register a new endpoint with its controller.
 * Call `startServer` to start the HTTP server.
 * 
 * Example:
 * 
 * Route.registerEndpoint("/example", new ExampleController());
 * Route.startServer();
 */

package org.example.core;

import java.io.IOException;

import java.net.InetSocketAddress;

import com.sun.net.httpserver.HttpServer;

public final class Route {
    private static final HttpServer SERVER;
    private static final String SERVER_HOSTNAME = "localhost";
    private static final int SERVER_PORT = 8080;

    static {
        try {
            SERVER = HttpServer.create();
            SERVER.bind(new InetSocketAddress(SERVER_HOSTNAME, SERVER_PORT), 0);

        } catch (IOException e) {
            throw new RuntimeException("Error creating HTTP server: " + e.getMessage(), e);
        }
    }

    public static void registerEndpoint(String uri, Controller controller) {
        AccessPoint.validateUri(uri);
        SERVER.createContext(AccessPoint.cleanUri(uri), configureController(uri, controller));
    }

    public static void startServer() {
        SERVER.start();
        System.out.println("Server started");
        System.out.println("SERVER_HOSTNAME: " + SERVER_HOSTNAME);
        System.out.println("SERVER_PORT:" + SERVER_PORT);
    }

    private static Controller configureController(String uri, Controller controller) {
        controller.setUriContextParams(AccessPoint.parseUriParameters(uri));
        controller.setEndpointPath(AccessPoint.cleanUri(uri));

        return controller;
    }
}
