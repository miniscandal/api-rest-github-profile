package org.example.core;

import java.io.IOException;

import java.net.InetSocketAddress;

import com.sun.net.httpserver.HttpServer;

public class Server {
    private static final HttpServer httpServer;
    private static final String SERVER_HOSTNAME = "127.0.0.1";
    private static final int SERVER_PORT = 3001;

    static {
        try {
            httpServer = HttpServer.create();
            httpServer.bind(new InetSocketAddress(SERVER_HOSTNAME, SERVER_PORT), 0);

        } catch (IOException e) {
            throw new RuntimeException("Error creating HTTP server: " + e.getMessage(), e);
        }
    }

    public static HttpServer getHttpServer() {
        return httpServer;
    }

    public static void start() {
        httpServer.start();
        System.out.printf("Local: http://%s:%d/%n", SERVER_HOSTNAME, SERVER_PORT);
    }
}
