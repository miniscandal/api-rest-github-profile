package org.example.core;

import java.io.IOException;
import java.io.OutputStream;

import java.util.List;
import java.util.ArrayList;
import java.util.Map;

import java.net.URI;

import com.sun.net.httpserver.Headers;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpExchange;

public abstract class Controller implements HttpHandler {
    public ApiRequestExecutor apiRequestExecuter;
    private final String apiBaseUri;
    private String endpointPath;
    private List<String> uriContextParams = new ArrayList<String>();
    private List<String> uriRequestParams = new ArrayList<String>();
    public HttpRequestParameters httpRequestParameters = new HttpRequestParameters();

    public Controller(String apiBaseUri, Class<?> modelClass) {
        this.apiBaseUri = apiBaseUri;
        this.apiRequestExecuter = new ApiRequestExecutor(modelClass);
    }

    public abstract byte[] processResponse(HttpRequestParameters httpRequestParameters);

    public void setEndpointPath(String path) {
        this.endpointPath = path;
    }

    public void setUriContextParams(List<String> params) {
        this.uriContextParams = List.copyOf(params);
    }

    public void setUriRequestParams(List<String> params) {
        this.uriRequestParams = List.copyOf(params);
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

    public List<String> extractRequestParams(HttpExchange httpExchange) {
        List<String> values = new ArrayList<String>();
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
                httpExchange.sendResponseHeaders(404, -1);
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
        List<String> requestParams = extractRequestParams(httpExchange);
        byte[] response;

        if (requestParams.size() == uriContextParams.size()) {
            setUriRequestParams(requestParams);
            mapRequestParameters();
            response = processResponse(httpRequestParameters);
        } else {
            response = "{\"message\": \"URI NOT FOUND\"}".getBytes();
        }

        responseToClient(httpExchange, response);
    }

    private String normalizeUri() {
        String uri = apiBaseUri;
        Map<String, String> parameters = httpRequestParameters.getParameters();

        for (Map.Entry<String, String> parameter : parameters.entrySet()) {
            uri = uri.replace("{" + parameter.getKey() + "}", parameter.getValue());
        }

        return uri;
    }
}
