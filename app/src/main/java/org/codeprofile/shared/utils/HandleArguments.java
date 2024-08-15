package org.codeprofile.shared.utils;

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
}
