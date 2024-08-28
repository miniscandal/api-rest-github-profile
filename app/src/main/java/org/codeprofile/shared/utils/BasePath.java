package org.codeprofile.shared.utils;

import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.codeprofile.core.network.Server;
import org.codeprofile.shared.enums.ExceptionMessage;
import org.codeprofile.shared.exceptions.IllegalBasePathFormat;

public class BasePath {
    private static final String BASE_PATH_PARAM_REGEX = "\\{(.*?)\\}";
    private static final String BASE_PATH_CLEAN_REGEX = "/\\{.*?\\}/?$";
    private static final String BASE_PATH_VALID_REGEX = "^(/\\w+)+(/\\{[a-zA-Z]+\\})*$";

    public static void validate(String path) {
        Pattern pattern = Pattern.compile(BASE_PATH_VALID_REGEX);
        Matcher matcher = pattern.matcher(path);

        if (!matcher.matches()) {
            IllegalBasePathFormat exception = new IllegalBasePathFormat(ExceptionMessage.BASE_PATH_FORMAT.getMessage());
            System.out.println(exception.getMainMessage());
            Server.stop(0);
            throw exception;
        }
    }

    public static String removeParameters(String path) {
        Pattern pattern = Pattern.compile(BASE_PATH_CLEAN_REGEX);
        Matcher matcher = pattern.matcher(path);

        return matcher.replaceAll("");
    }

    public static String[] retrieveParameters(String path) {
        Pattern pattern = Pattern.compile(BASE_PATH_PARAM_REGEX);
        Matcher matcher = pattern.matcher(path);

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

    public static String formatPath(String path, Map<String, String> map) {
        for (Map.Entry<String, String> entry : map.entrySet()) {
            String target = "{" + entry.getKey() + "}";
            path = path.replace(target, entry.getValue());
        }

        return path;
    }
}
