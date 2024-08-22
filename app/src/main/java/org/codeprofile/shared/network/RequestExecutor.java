package org.codeprofile.shared.network;

import java.io.IOException;
import java.io.InputStream;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

import org.codeprofile.shared.exceptions.HttpClientException;

public class RequestExecutor {
    private static final HttpClient httpClient = HttpClient.newHttpClient();

    public static HttpResponse<InputStream> get(String uri) throws HttpClientException {
        HttpRequest request = createGet(uri);

        try {
            return httpClient.send(request, HttpResponse.BodyHandlers.ofInputStream());
        } catch (IOException | InterruptedException e) {
            throw new HttpClientException(e);
        }
    }

    private static HttpRequest createGet(String uri) {
        return HttpRequest.newBuilder()
                .uri(URI.create(uri))
                .GET()
                .build();
    }
}
