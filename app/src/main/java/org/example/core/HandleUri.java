package org.example.core;

import java.util.List;
import java.util.ArrayList;

import java.net.URI;

import com.sun.net.httpserver.HttpExchange;

public class HandleUri {

    public static void validateUrl(String url) {
        try {
            new URI(url).parseServerAuthority();
        } catch (Exception e) {
            String message = "\nInvalid URL: " + url + "\nmessage: " + e.getMessage();
            throw new IllegalArgumentException(message);
        }
    }

    public static List<String> extractParameters(HttpExchange httpExchange, String endpointPath) {
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
}
