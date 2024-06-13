package org.example.core;

import java.io.IOException;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import java.net.InetSocketAddress;
import com.sun.net.httpserver.HttpServer;

public final class Routing {
    private static final HttpServer HTTP_SERVER;
    private static final String HOSTNAME = "localhost";
    private static final int PORT = 8080;
    private static final String PARAMETER_PATTERN = "\\{(.*?)\\}";
    private static final String NORMALIZE_URI_PATTERN = "/\\{.*?\\}/?$";

    static {
        HTTP_SERVER = initializeHttpServer();
    }

    private static HttpServer initializeHttpServer() {
        HttpServer server = null;
        try {
            server = HttpServer.create();
            server.bind(new InetSocketAddress(HOSTNAME, PORT), 0);

            return server;
        } catch (IOException e) {
            System.err.println("Error creating HTTP server: " + e.getMessage());
        }

        return server;
    }

    private static ArrayList<String> extractRequestParameters(String uri) {
        Pattern pattern = Pattern.compile(PARAMETER_PATTERN);
        Matcher matcher = pattern.matcher(uri);

        ArrayList<String> listKeys = new ArrayList<String>();

        while (matcher.find()) {
            String key = matcher.group(1);
            listKeys.add(key);
        }

        return listKeys;
    }

    private static String normalizeUri(String uri) {
        Pattern pattern = Pattern.compile(NORMALIZE_URI_PATTERN);
        Matcher matcher = pattern.matcher(uri);
        String normalizeUri = matcher.replaceAll("");

        return normalizeUri;
    }

    public static Controller setupController(String uri, Controller controller) {
        ArrayList<String> listKeys = extractRequestParameters(uri);

        controller.setRequestParameterKeys(listKeys);
        controller.setRoutePath(normalizeUri(uri));

        return controller;
    }

    public static void setupContext(String uri, Controller controller) {
        controller = setupController(uri, controller);

        HTTP_SERVER.createContext(normalizeUri(uri), controller);
    }

    public static void start() {
        HTTP_SERVER.start();

        System.out.println("Server started");
        System.out.println("HOSTNAME: " + HOSTNAME);
        System.out.println("PORT:" + PORT);
    }
}
