package org.codeprofile.shared.utils;

import java.util.Map;
import java.util.HashMap;

public class HandleArguments {
    public static String[] extractArguments(String requestPath, String contextPath) {
        String[] items = requestPath.replace(contextPath, "").split("/");

        int count = 0;

        for (String item : items) {
            if (!item.isEmpty()) {
                count++;
            }
        }

        String[] result = new String[count];
        int index = 0;

        for (String item : items) {
            if (!item.isEmpty()) {
                result[index++] = item;
            }
        }

        return result;
    }

    public static Map<String, String> createRelationship(String[] parameters, String[] arguments) {
        Map<String, String> map = new HashMap<>();

        for (int i = 0; i < arguments.length; i++) {
            map.put(parameters[i], arguments[i]);
        }

        return map;
    }
}
