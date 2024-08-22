package org.codeprofile.core.network;

import java.io.IOException;

import java.net.InetSocketAddress;

import org.codeprofile.core.enums.ExceptionMessage;
import org.codeprofile.core.exceptions.ServerException;

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
            ServerException exception = new ServerException(ExceptionMessage.SERVER_BIND.getMessage(), e);
            System.out.println(exception.getMainMessage());
            throw exception;
        }
    }

    public static HttpServer getHttpServer() {
        return httpServer;
    }

    public static void start() {
        httpServer.start();
        System.out.printf("Local: http://%s:%d/%n", SERVER_HOSTNAME, SERVER_PORT);
    }

    public static void stop(int delay) {
        httpServer.stop(delay);
    }
}
