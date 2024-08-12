package org.codeprofile.shared.network;

import java.io.IOException;
import java.io.InputStream;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class RequestExecutor {
    private static final HttpClient httpClient = HttpClient.newHttpClient();

    public static HttpResponse<InputStream> sendRequest(String uri) throws IOException, InterruptedException {
        HttpRequest request = createRequest(uri);
        return httpClient.send(request, HttpResponse.BodyHandlers.ofInputStream());
    }

    private static HttpRequest createRequest(String uri) {
        return HttpRequest.newBuilder()
                .uri(URI.create(uri))
                .GET()
                .build();
    }
}
