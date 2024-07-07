package org.example.core;

import java.io.IOException;
import java.io.OutputStream;

import java.util.ArrayList;
import java.util.Map;

import java.net.URI;

import com.sun.net.httpserver.Headers;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpExchange;

public abstract class Controller implements HttpHandler {
    public ApiRequestExecutor apiRequestExecuter;
    private String apiBaseUri;
    public HttpRequestParameters httpRequestParameters = new HttpRequestParameters();
    private String endpointPath;
    private ArrayList<String> uriContextParams = new ArrayList<String>();
    private ArrayList<String> uriRequestParams = new ArrayList<String>();

    public abstract byte[] processResponse(HttpRequestParameters httpRequestParameters);

    public Controller(String apiBaseUri, Class<?> modelClass) {
        this.apiBaseUri = apiBaseUri;
        apiRequestExecuter = new ApiRequestExecutor(modelClass);
    }

    public void setEndpointPath(String path) {
        endpointPath = path;
    }

    public void setUriContextParams(ArrayList<String> params) {
        uriContextParams.addAll(params);
    }

    public void setUriRequestParams(ArrayList<String> params) {
        uriRequestParams.clear();
        uriRequestParams.addAll(params);
    }

    public void mapRequestParameters() {
        for (int i = 0; i < uriContextParams.size(); i++) {
            httpRequestParameters.addParameters(uriContextParams.get(i), uriRequestParams.get(i));
        }
    }

    public byte[] singleObjectResponse() {
        byte[] response = null;

        try {
            response = apiRequestExecuter.executeSingleObjectResponse(normalizeUri());
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }

        return response;
    }

    public byte[] arrayObjectsResponse() {
        byte[] response = null;

        try {
            response = apiRequestExecuter.executeArrayObjectResponse(normalizeUri());
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }

        return response;
    }

    public ArrayList<String> extractRequestParams(HttpExchange httpExchange) {
        ArrayList<String> values = new ArrayList<String>();

        URI uri = httpExchange.getRequestURI();
        String path = uri.getPath().replace(endpointPath, "");
        String pathSegments[] = path.split("/");
        for (String segment : pathSegments) {
            if (!segment.isEmpty()) {
                values.add(segment);
            }
        }

        return values;
    }

    public void responseToClient(HttpExchange httpExchange, byte[] responseData) {
        Headers headers = httpExchange.getResponseHeaders();

        headers.set("Content-Type", "application/json; charset=utf-8");

        try (OutputStream outputStream = httpExchange.getResponseBody()) {
            String method = httpExchange.getRequestMethod();
            if (method.equalsIgnoreCase("HEAD")) {
                httpExchange.sendResponseHeaders(204, -1);
                return;
            }
            if (method.equalsIgnoreCase("GET")) {
                httpExchange.sendResponseHeaders(200, responseData.length);
                outputStream.write(responseData);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void handle(HttpExchange httpExchange) throws IOException {
        ArrayList<String> requestParams = extractRequestParams(httpExchange);

        byte[] response = null;
        if (requestParams.size() == uriContextParams.size()) {
            setUriRequestParams(requestParams);
            mapRequestParameters();
            response = processResponse(httpRequestParameters);
        } else {
            String message = "{\"message\": \"URI NOT FOUND\"}";
            response = message.getBytes();
        }

        responseToClient(httpExchange, response);
    }

    private String normalizeUri() {
        String uri = apiBaseUri;
        Map<String, String> parameters = httpRequestParameters.getParameters();

        for (Map.Entry<String, String> parameter : parameters.entrySet()) {
            String key = parameter.getKey();
            String value = parameter.getValue();

            uri = uri.replace("{" + key + "}", value);
        }

        return uri;
    }
}
