package org.codeprofile.core.network;

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
        BasePath.validate(path);
        configureController(path, controller);
        httpServer.createContext(BasePath.removeParameters(path), controller);
    }

    public static void configureController(String path, Controller controller) {
        controller.setPath(BasePath.removeParameters(path));
        controller.setParameters(BasePath.retrieveParameters(path));

        if (controller instanceof ServiceStrategy) {
            controller.setService(((ServiceStrategy) controller).newService());
        }
    }
}
