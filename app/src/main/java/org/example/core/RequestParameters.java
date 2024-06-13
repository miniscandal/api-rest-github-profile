package org.example.core;

import java.util.HashMap;
import java.util.Map;

public class RequestParameters {
    private final Map<String, String> parameters = new HashMap<>();

    public Map<String, String> getParameters() {
        return parameters;
    }

    public void addParameters(String key, String value) {
        parameters.put(key, value);
    }

    public void displayParameters() {
        for (Map.Entry<String, String> parameter : parameters.entrySet()) {
            String key = parameter.getKey();
            String value = parameter.getValue();
            System.out.println("key: " + key + ", value: " + value);
        }
    }

    public int size() {
        return parameters.size();
    }
}
