package org.example.core;

import java.io.IOException;
import java.io.OutputStream;

import java.util.ArrayList;
import java.util.Map;

import java.net.URI;

import com.sun.net.httpserver.Headers;
import com.sun.net.httpserver.HttpExchange;

public abstract class Controller implements ConfigHttpHandler {
    public ApiRequestExecuter apiRequestExecuter;
    private String githubApiBaseUri;
    public RequestParameters requestParameters = new RequestParameters();
    private String routePath;
    private ArrayList<String> listParametersUriContext = new ArrayList<String>();
    private ArrayList<String> listParametersUriRequest = new ArrayList<String>();

    public abstract byte[] manageResponse(RequestParameters requestParameters);

    public Controller(String githubBaseUri, Class<?> modelClass) {
        githubApiBaseUri = githubBaseUri;
        apiRequestExecuter = new ApiRequestExecuter(modelClass);
    }

    public void setRoutePath(String path) {
        routePath = path;
    }

    public void setRequestParameterKeys(ArrayList<String> arrayList) {
        for (String string : arrayList) {
            listParametersUriContext.add(string);
        }
    }

    public void setRequestParameterValues(ArrayList<String> arrayList) {
        listParametersUriRequest.clear();
        for (String string : arrayList) {
            listParametersUriRequest.add(string);
        }
    }

    public void setRequestParameter() {
        int index = 0;
        for (String string : listParametersUriContext) {
            requestParameters.addParameters(string, listParametersUriRequest.get(index));
            index = index + 1;
        }
    }

    public byte[] singleObjectResponse() {
        String uri = normalizeUri();
        return apiRequestExecuter.singleObjectResponse(uri);
    }

    public byte[] arrayObjectsResponse() {
        String uri = normalizeUri();
        return apiRequestExecuter.arrayObjectResponse(uri);
    }

    public ArrayList<String> getRequestParameterValues(HttpExchange httpExchange) {
        ArrayList<String> listValues = new ArrayList<String>();

        URI uri = httpExchange.getRequestURI();
        String path = uri.getPath().replace(routePath, "");
        String wordsPath[] = path.split("/");
        for (String string : wordsPath) {
            listValues.add(string);
        }

        listValues.removeIf(s -> s.isEmpty());

        return listValues;
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
        ArrayList<String> listParametersUriRequest = getRequestParameterValues(httpExchange);
        boolean size = listParametersUriRequest.size() == listParametersUriContext.size();

        byte[] response = null;
        if (!size) {
            String message = "{\"message\": \"URI NOT FOUND\"}";
            response = message.getBytes();
        } else {
            setRequestParameterValues(listParametersUriRequest);
            setRequestParameter();
            response = manageResponse(requestParameters);
        }
        sendResponse(httpExchange, response);
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
