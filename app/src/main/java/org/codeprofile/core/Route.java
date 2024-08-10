package org.codeprofile.core;

import org.codeprofile.shared.http.Controller;
import org.codeprofile.shared.strategies.ServiceStrategy;
import org.codeprofile.shared.utils.BasePath;

import com.sun.net.httpserver.HttpServer;

public final class Route {
    private static final HttpServer httpServer;

    static {
        httpServer = Server.getHttpServer();
        Server.start();
    }

    public static void get(String path, Controller controller) {
        BasePath basePath = new BasePath(path);

        configureController(basePath, controller);

        httpServer.createContext(basePath.getPath(), controller);
    }

    public static void configureController(BasePath basePath, Controller controller) {
        controller.setPath(basePath.getPath());
        controller.setParameters(basePath.getParameters());

        if (controller instanceof ServiceStrategy) {
            controller.setService(((ServiceStrategy) controller).newService());
        }
    }
}
