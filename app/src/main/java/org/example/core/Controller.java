package org.example.core;

import java.io.IOException;
import java.io.OutputStream;

import java.util.Map;

import java.net.URI;

import com.sun.net.httpserver.Headers;
import com.sun.net.httpserver.HttpExchange;

public abstract class Controller implements ConfigHttpHandler {
    private static String githubApiBaseUri;
    private String routePath;
    public ApiRequestExecuter apiRequestExecuter;
    public RequestParameters requestParameters = new RequestParameters();

    public abstract byte[] manageResponse(RequestParameters requestParameters);

    public Controller(Class<?> modelClass, String githubBaseUri) {
        apiRequestExecuter = new ApiRequestExecuter(modelClass);
        githubApiBaseUri = githubBaseUri;
    }

    public void setRoutePath(String path) {
        routePath = path;
    }

    public byte[] singleObjectResponse() {
        String uri = normalizeUri();
        return apiRequestExecuter.singleObjectResponse(uri);
    }

    public byte[] arrayObjectsResponse() {
        String uri = normalizeUri();
        return apiRequestExecuter.arrayObjectResponse(uri);
    }

    public RequestParameters getRequestParameters(HttpExchange httpExchange) {
        URI uri = httpExchange.getRequestURI();
        String path = uri.getPath().replace(routePath, "");
        String wordsPath[] = path.split("/");
        Map<String, String> parameters = requestParameters.getParameters();
        int index = 1;

        if (wordsPath.length - 1 != parameters.size()) {
            return null;
        }

        for (Map.Entry<String, String> parameter : parameters.entrySet()) {
            String key = parameter.getKey();
            String value = wordsPath[index];
            requestParameters.addParameters(key, value);
            index++;
        }

        return requestParameters;
    }

    public void sendResponse(HttpExchange httpExchange, byte[] bytes) {
        Headers headers = httpExchange.getResponseHeaders();

        headers.set("Content-Type", "application/json; charset=utf-8");

        try (OutputStream outputStream = httpExchange.getResponseBody()) {
            String method = httpExchange.getRequestMethod();
            if (method.equalsIgnoreCase("HEAD")) {
                httpExchange.sendResponseHeaders(204, -1);
                return;
            }
            if (method.equalsIgnoreCase("GET")) {
                httpExchange.sendResponseHeaders(200, bytes.length);
                outputStream.write(bytes);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void handle(HttpExchange httpExchange) throws IOException {
        requestParameters = getRequestParameters(httpExchange);
        byte[] response = null;
        if (requestParameters == null) {
            String message = "{\"message\": \"URI NOT FOUND\"}";
            response = message.getBytes();
        } else {
            response = manageResponse(requestParameters);
        }
        sendResponse(httpExchange, response);
    }

    @Override
    public void setRequestParameters(RequestParameters requestParameters) {
        this.requestParameters = requestParameters;
    }

    private String normalizeUri() {
        String uri = githubApiBaseUri;
        Map<String, String> parameters = requestParameters.getParameters();

        for (Map.Entry<String, String> parameter : parameters.entrySet()) {
            String key = parameter.getKey();
            String value = parameter.getValue();

            uri = uri.replace("{" + key + "}", value);
        }

        return uri;
    }
}
