package org.example.core;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import java.net.URI;

public class AccessPoint {
    private static final String URI_PARAM_REGEX = "\\{(.*?)\\}";
    private static final String URI_CLEANUP_REGEX = "/\\{.*?\\}/?$";
    private static final String URI_VALID_REGEX = "^(/\\w+)+(/\\{[a-zA-Z]+\\})*$";

    public static void validateUrl(String url) {
        try {
            new URI(url).parseServerAuthority();
        } catch (Exception e) {
            String message = "\nInvalid URL: " + url + "\nmessage: " + e.getMessage();

            throw new IllegalArgumentException(message);
        }
    }

    public static void validateUri(String uri) {
        boolean isValid = uri.matches(URI_VALID_REGEX);

        if (!isValid) {
            throw new IllegalArgumentException("Invalid URI: " + uri);
        }
    }

    public static List<String> parseUriParameters(String uri) {
        Pattern pattern = Pattern.compile(URI_PARAM_REGEX);
        Matcher matcher = pattern.matcher(uri);
        List<String> params = new ArrayList<String>();

        while (matcher.find()) {
            params.add(matcher.group(1));
        }

        return params;
    }

    public static String cleanUri(String uri) {
        Pattern pattern = Pattern.compile(URI_CLEANUP_REGEX);
        Matcher matcher = pattern.matcher(uri);
        String cleanUri = matcher.replaceAll("");

        return cleanUri;
    }
}
