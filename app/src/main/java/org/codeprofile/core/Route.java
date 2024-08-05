package org.codeprofile.core;

import org.codeprofile.core.http.Controller;
import org.codeprofile.shared.BasePath;

import com.sun.net.httpserver.HttpServer;

public final class Route {
    private static final HttpServer httpServer = Server.getHttpServer();

    static {
        Server.start();
    }

    public static void get(String path, Controller controller) {
        BasePath basePath = new BasePath(path);
        httpServer.createContext(basePath.getPath(), configureController(basePath, controller));
    }

    private static Controller configureController(BasePath basePath, Controller controller) {
        controller.setPath(basePath.getPath());
        controller.setParameters(basePath.getParameters());

        return controller;
    }
}
