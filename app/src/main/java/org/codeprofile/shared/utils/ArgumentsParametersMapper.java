package org.codeprofile.shared.utils;

import java.util.Map;
import java.util.HashMap;

public class ArgumentsParametersMapper {
    private String[] parameters;
    private String[] arguments;
    private Map<String, String> map;

    public Map<String, String> getMap() {
        return map;
    }

    public ArgumentsParametersMapper(String[] parameters, String[] arguments) {
        this.parameters = parameters;
        this.arguments = arguments;

        checkExpectedArgumentsCount();

        this.map = createMap(this.parameters, this.arguments);
    }

    public void checkExpectedArgumentsCount() {
        if (this.parameters.length != this.arguments.length) {
            throw new IllegalArgumentException("Parameters and Arguments");
        }
    }

    public Map<String, String> createMap(String[] parameters, String[] arguments) {
        Map<String, String> map = new HashMap<>();

        for (int i = 0; i < arguments.length; i++) {
            map.put(parameters[i], arguments[i]);
        }

        return map;
    }

    public String formatPath(String path) {
        for (Map.Entry<String, String> entry : this.map.entrySet()) {
            String target = "{" + entry.getKey() + "}";
            path = path.replace(target, entry.getValue());
        }

        return path;
    }
}
