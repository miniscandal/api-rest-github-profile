/**
 * Responsibility:
 * 
 * Initializes an HTTP server (singleton) within the application's lifecycle.
 * Validates, cleans, and configures endpoints (URIs) along with their respective controllers.
 * Assigns properties to controllers to handle HTTP requests.
 */

package org.example.core;

import java.io.IOException;

import java.util.List;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import java.net.InetSocketAddress;

import com.sun.net.httpserver.HttpServer;

public final class Route {
    private static final HttpServer SERVER;
    private static final String SERVER_HOSTNAME = "localhost";
    private static final int SERVER_PORT = 8080;
    private static final String URI_PARAM_REGEX = "\\{(.*?)\\}";
    private static final String URI_CLEANUP_REGEX = "/\\{.*?\\}/?$";
    private static final String URI_VALID_REGEX = "^(/\\w+)+(/\\{[a-zA-Z]+\\})*$";

    static {
        SERVER = setupHttpServer();
    }

    private static HttpServer setupHttpServer() {
        HttpServer server = null;

        try {
            server = HttpServer.create();
            server.bind(new InetSocketAddress(SERVER_HOSTNAME, SERVER_PORT), 0);
        } catch (IOException e) {
            System.err.println("Error creating HTTP server: " + e.getMessage());
        }

        return server;
    }

    private static List<String> parseUriParameters(String uri) {
        Pattern pattern = Pattern.compile(URI_PARAM_REGEX);
        Matcher matcher = pattern.matcher(uri);
        List<String> params = new ArrayList<String>();

        while (matcher.find()) {
            params.add(matcher.group(1));
        }

        return params;
    }

    private static String cleanUri(String uri) {
        Pattern pattern = Pattern.compile(URI_CLEANUP_REGEX);
        Matcher matcher = pattern.matcher(uri);
        String cleanUri = matcher.replaceAll("");

        return cleanUri;
    }

    public static void verifyUri(String uri) {
        boolean isValid = uri.matches(URI_VALID_REGEX);

        if (!isValid) {
            throw new IllegalArgumentException("Invalid URI: " + uri);
        }
    }

    public static Controller configureController(String uri, Controller controller) {
        List<String> uriParams = parseUriParameters(uri);

        controller.setUriContextParams(uriParams);
        controller.setEndpointPath(cleanUri(uri));

        return controller;
    }

    public static void registerEndpoint(String uri, Controller controller) {
        verifyUri(uri);
        controller = configureController(uri, controller);

        SERVER.createContext(cleanUri(uri), controller);
    }

    public static void launchServer() {
        SERVER.start();

        System.out.println("Server started");
        System.out.println("SERVER_HOSTNAME: " + SERVER_HOSTNAME);
        System.out.println("SERVER_PORT:" + SERVER_PORT);
    }
}
