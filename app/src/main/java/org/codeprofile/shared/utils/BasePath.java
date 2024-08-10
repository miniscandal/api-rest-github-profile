package org.codeprofile.shared.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class BasePath {
    private String path;
    private String[] parameters;
    private static final String BASE_PATH_PARAM_REGEX = "\\{(.*?)\\}";
    private static final String BASE_PATH_CLEAN_REGEX = "/\\{.*?\\}/?$";
    private static final String BASE_PATH_VALID_REGEX = "^(/\\w+)+(/\\{[a-zA-Z]+\\})*$";

    public BasePath(String basePath) {
        this.validateBasePath(basePath);
        this.path = removeBasePathParameters(basePath);
        this.parameters = retrieveBasePathParameters(basePath);
    }

    public String getPath() {
        return this.path;
    }

    public String[] getParameters() {
        return this.parameters;
    }

    private void validateBasePath(String basePath) {
        Pattern pattern = Pattern.compile(BASE_PATH_VALID_REGEX);
        Matcher matcher = pattern.matcher(basePath);

        if (!matcher.matches()) {
            throw new IllegalArgumentException("Invalid base path format");
        }
    }

    public static String removeBasePathParameters(String basePath) {
        Pattern pattern = Pattern.compile(BASE_PATH_CLEAN_REGEX);
        Matcher matcher = pattern.matcher(basePath);

        return matcher.replaceAll("");
    }

    public static String[] retrieveBasePathParameters(String basePath) {
        Pattern pattern = Pattern.compile(BASE_PATH_PARAM_REGEX);
        Matcher matcher = pattern.matcher(basePath);

        int count = 0;

        while (matcher.find()) {
            count++;
        }

        String[] parameters = new String[count];

        matcher.reset();
        int index = 0;
        while (matcher.find()) {
            parameters[index++] = matcher.group(1);
        }

        return parameters;
    }
}
