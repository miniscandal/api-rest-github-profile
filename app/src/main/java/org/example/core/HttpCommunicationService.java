package org.example.core;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

import com.google.gson.Gson;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

public class HttpCommunicationService implements HttpHandler {
    private Class<?> classOft;
    private String url;

    public HttpCommunicationService(Class<?> classOft, String url) {
        this.classOft = classOft;
        this.url = url;
    }

    @Override
    public void handle(HttpExchange httpExchange) {
        HttpResponse<InputStream> response = performHttpRequest(url);
        InputStreamReader inputStreamReader = new InputStreamReader(response.body());
        int statusCode = response.statusCode();

        if (statusCode >= 200 && statusCode <= 299) {
            byte[] responseBytes = getResponseBytes(inputStreamReader, classOft);
            sendResponseBody(200, httpExchange, "application/json", responseBytes);
            return;
        }

        if (statusCode >= 400 && statusCode <= 499) {
            String message = "Error 404, Page not found";
            sendResponseBody(404, httpExchange, "text/plain", message.getBytes());
            return;
        }

        if (statusCode >= 500 && statusCode <= 599) {
            String message = "Error 500, Internal Server Error";
            sendResponseBody(404, httpExchange, "text/plain", message.getBytes());
        }
    }

    private HttpResponse<InputStream> performHttpRequest(String url) {
        HttpClient client = HttpClient.newBuilder().build();
        HttpRequest request = HttpRequest.newBuilder().uri(URI.create(url)).GET().build();
        HttpResponse<InputStream> response = null;

        try {
            response = client.send(request, HttpResponse.BodyHandlers.ofInputStream());
        } catch (IOException e) {
            System.out.println("IOException: " + e);
        } catch (InterruptedException e) {
            System.out.println("InterruptedException: " + e);
        }

        return response;
    }

    private static byte[] getResponseBytes(InputStreamReader reader, Class<?> classOft) {
        Gson gson = new Gson();
        Object object = gson.fromJson(reader, classOft);
        String jsonResponse = gson.toJson(object);

        return jsonResponse.getBytes();
    }

    public static void sendResponseBody(int statusCode, HttpExchange httpExchange, String contentType, byte[] bytes) {
        try {
            httpExchange.getResponseHeaders().set("Content-Type", contentType);
            httpExchange.sendResponseHeaders(statusCode, bytes.length);
            OutputStream outputStream = httpExchange.getResponseBody();
            outputStream.write(bytes);
            outputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
