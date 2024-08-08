package org.codeprofile.core;

import org.codeprofile.shared.http.Controller;
import org.codeprofile.shared.interfaces.Service;
import org.codeprofile.shared.interfaces.ServiceStrategy;
import org.codeprofile.shared.utils.BasePath;

import com.sun.net.httpserver.HttpServer;

public final class Route {
    private static final HttpServer httpServer;

    static {
        httpServer = Server.getHttpServer();
        Server.start();
    }

    public static void get(String path, Controller controller) {
        if (path == null || controller == null) {
            throw new IllegalArgumentException("Path and controller cannot be null");
        }
        BasePath basePath = new BasePath(path);
        setControllerProperties(basePath, controller);
        setConfigureService(controller);

        httpServer.createContext(basePath.getPath(), controller);
    }

    private static void setControllerProperties(BasePath basePath, Controller controller) {
        controller.setPath(basePath.getPath());
        controller.setParameters(basePath.getParameters());
    }

    private static void setConfigureService(Controller controller) {
        if (controller instanceof ServiceStrategy) {
            Service service = ((ServiceStrategy) controller).getService();
            controller.setService(service);
        }
    }
}
