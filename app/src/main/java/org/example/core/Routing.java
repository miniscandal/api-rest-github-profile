package org.example.core;

import java.io.IOException;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import java.net.InetSocketAddress;

import com.sun.net.httpserver.HttpServer;

public final class Routing {
    private static final HttpServer HTTP_SERVER;
    private static final String HOSTNAME = "localhost";
    private static final int PORT = 8080;

    static {
        HTTP_SERVER = initializeHttpServer();
    }

    private static HttpServer initializeHttpServer() {
        try {
            HttpServer server = HttpServer.create();
            server.bind(new InetSocketAddress(HOSTNAME, PORT), 0);

            return server;
        } catch (IOException e) {
            throw new RuntimeException("Error creating HTTP server: " + e.getMessage(), e);
        }
    }

    private static RequestParameters extractRequestParameters(String uri) {
        RequestParameters requestParameters = new RequestParameters();
        Pattern pattern = Pattern.compile("\\{(.*?)\\}");
        Matcher matcher = pattern.matcher(uri);

        while (matcher.find()) {
            String key = matcher.group(1);
            requestParameters.addParameters(key, null);
        }

        return requestParameters;
    }

    private static String normalizeUri(String uri) {
        Pattern pattern = Pattern.compile("/\\{.*?\\}/?$");
        Matcher matcher = pattern.matcher(uri);
        String normalizeUri = matcher.replaceAll("");

        return normalizeUri;
    }

    public static Controller setupController(String uri, Controller controller) {
        RequestParameters requestParameters = extractRequestParameters(uri);
        controller.setRequestParameters(requestParameters);
        controller.setRoutePath(normalizeUri(uri));

        return controller;
    }

    public static void setupContext(String uri, Controller controller) {
        String path = normalizeUri(uri);
        controller = setupController(uri, controller);

        HTTP_SERVER.createContext(path, controller);
    }

    public static void start() {
        HTTP_SERVER.start();

        System.out.println("Server started");
        System.out.println("HOSTNAME: " + HOSTNAME);
        System.out.println("PORT:" + PORT);
    }
}
