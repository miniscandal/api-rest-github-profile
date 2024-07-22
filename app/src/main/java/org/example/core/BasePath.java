package org.example.core;

import java.util.List;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class BasePath {
    private String path;
    private List<String> parameters = new ArrayList<String>();
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

    public List<String> getParameters() {
        return this.parameters;
    }

    private void validateBasePath(String basePath) {
        Pattern pattern = Pattern.compile(BASE_PATH_VALID_REGEX);
        Matcher matcher = pattern.matcher(basePath);

        if (!matcher.matches()) {
            throw new IllegalArgumentException("Invalid base path format");
        }
    }

    public static List<String> retrieveBasePathParameters(String basePath) {
        Pattern pattern = Pattern.compile(BASE_PATH_PARAM_REGEX);
        Matcher matcher = pattern.matcher(basePath);
        List<String> parameters = new ArrayList<String>();

        while (matcher.find()) {
            parameters.add(matcher.group(1));
        }

        return parameters;
    }

    public static String removeBasePathParameters(String basePath) {
        Pattern pattern = Pattern.compile(BASE_PATH_CLEAN_REGEX);
        Matcher matcher = pattern.matcher(basePath);

        return matcher.replaceAll("");
    }
}
