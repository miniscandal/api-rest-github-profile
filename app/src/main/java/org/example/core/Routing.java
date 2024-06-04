package org.example.core;

import java.io.IOException;
import java.net.InetSocketAddress;

import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpServer;

public class Routing {
    private static HttpServer httpServer;
    private static final String hostname = "localhost";
    private static final int port = 8080;

    static {
        try {
            httpServer = HttpServer.create();
            httpServer.bind(new InetSocketAddress(hostname, port), 0);
        } catch (IOException e) {
            System.err.println("Error: " + e);
        }
    }

    public static void createContext(String uri, HttpHandler httpHandler) {
        httpServer.createContext(uri, httpHandler);
    }

    public static void start() {
        httpServer.start();

        System.out.println("Server started");
        System.out.println("hostname: " + hostname);
        System.out.println("port:" + port);
    }
}
