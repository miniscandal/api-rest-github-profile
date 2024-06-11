package org.example.core;

import java.io.IOException;
import java.io.OutputStream;

import java.util.Map;

import java.net.URI;

import com.sun.net.httpserver.Headers;
import com.sun.net.httpserver.HttpExchange;

public abstract class Controller implements ConfigHttpHandler {
    private String ROUTE_PATH;
    private static String BASE_URI;
    public final ApiRequestExecuter apiRequest;
    public RequestParameters requestParameters = new RequestParameters();

    public abstract byte[] manageResponse(RequestParameters requestParameters);

    public Controller(Class<?> classOft, String baseUri) {
        BASE_URI = baseUri;
        apiRequest = new ApiRequestExecuter(classOft);
    }

    public void setRoutePath(String path) {
        ROUTE_PATH = path;
    }

    public String normalizeUriPath() {
        String uri = BASE_URI;
        Map<String, String> parameters = requestParameters.getParameters();

        for (Map.Entry<String, String> parameter : parameters.entrySet()) {
            String key = parameter.getKey();
            String value = parameter.getValue();

            uri = uri.replace("{" + key + "}", value);
        }

        return uri;
    }

    public byte[] singleObjectResponse() {
        String uri = normalizeUriPath();
        return apiRequest.singleObjectResponse(uri);
    }

    public byte[] arrayObjectsResponse() {
        String uri = normalizeUriPath();
        return apiRequest.arrayObjectResponse(uri);
    }

    public RequestParameters getRequestParameters(HttpExchange httpExchange) {
        URI uri = httpExchange.getRequestURI();
        String path = uri.getPath().replace(ROUTE_PATH, "");
        String words[] = path.split("/");
        Map<String, String> parameters = requestParameters.getParameters();
        int index = 1;

        for (Map.Entry<String, String> parameter : parameters.entrySet()) {
            String key = parameter.getKey();
            String value = words[index];
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
        requestParameters.displayParameters();
        byte[] response = manageResponse(requestParameters);
        sendResponse(httpExchange, response);
    }

    @Override
    public void setRequestParameters(RequestParameters requestParameters) {
        this.requestParameters = requestParameters;
    }
}
