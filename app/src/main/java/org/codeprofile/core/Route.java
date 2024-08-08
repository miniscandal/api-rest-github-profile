package org.codeprofile.core;

import org.codeprofile.shared.http.Controller;
import org.codeprofile.shared.contracts.Service;
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

        controller.setPath(basePath.getPath());
        controller.setParameters(basePath.getParameters());

        System.out.println(basePath.getPath());
        for (String element : basePath.getParameters()) {
            System.out.println(element);
        }

        if (controller instanceof ServiceStrategy) {
            Service service = ((ServiceStrategy) controller).getService();
            controller.setService(service);
        }

        httpServer.createContext(basePath.getPath(), controller);
    }
}
