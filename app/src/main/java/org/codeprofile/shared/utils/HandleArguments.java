package org.codeprofile.shared.utils;

import java.util.Map;
import java.util.HashMap;

public class HandleArguments {
    public Map<String, String> createMap(String[] parameters, String[] arguments) {
        Map<String, String> map = new HashMap<>();

        for (int i = 0; i < arguments.length; i++) {
            map.put(parameters[i], arguments[i]);
        }

        return map;
    }

    public String[] extractArguments(String requestPath, String contextPath) {
        return requestPath.replace(contextPath + "/", "").split("/");
    }
}
