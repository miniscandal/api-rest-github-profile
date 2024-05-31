package org.example.core;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.util.Map;

import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpServer;

public class HttpServerHandler {
    private static HttpServer httpServer;
    private static final String hostname = "localhost";
    private static final int port = 8080;

    static {
        try {
            httpServer = HttpServer.create();
            httpServer.bind(new InetSocketAddress(hostname, port), 0);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void createContexts(Map<String, HttpHandler> httpHandlers) {
        for (Map.Entry<String, HttpHandler> route : httpHandlers.entrySet()) {
            String path = route.getKey();
            HttpHandler httpHandler = route.getValue();

            httpServer.createContext(path, httpHandler);
        }

    }

    public static void start(Map<String, HttpHandler> httpHandlers) {
        createContexts(httpHandlers);
        httpServer.start();

        System.out.println("Server started");
        System.out.println("hostname: " + hostname);
        System.out.println("port:" + port);
    }
}
