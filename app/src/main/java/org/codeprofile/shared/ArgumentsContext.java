package org.codeprofile.shared;

import java.util.List;
import java.util.ArrayList;
import java.util.Map;
import java.util.HashMap;

public class ArgumentsContext {
    private List<String> parameters = new ArrayList<>();
    private List<String> arguments = new ArrayList<>();

    public ArgumentsContext(List<String> parameters, List<String> arguments) {
        this.parameters = parameters;
        this.arguments = arguments;
    }

    public boolean isValidRequestArgumentsCount() {
        return this.parameters.size() == this.arguments.size();
    }

    public Map<String, String> getMatchParametersArguments() {
        Map<String, String> map = new HashMap<>();

        if (isValidRequestArgumentsCount()) {
            for (int i = 0; i < this.arguments.size(); i++) {
                map.put(parameters.get(i), arguments.get(i));
            }
        }

        return map;
    }

    public void iterateArgumentContext() {
        System.out.println("Argument Context");

        for (Map.Entry<String, String> argument : getMatchParametersArguments().entrySet()) {
            System.out.println("key: " + argument.getKey() + ", value: " + argument.getValue());
        }
    }

    public String applyArgumentsInPath(String path) {
        String pathWithArguments = path;

        for (Map.Entry<String, String> element : getMatchParametersArguments().entrySet()) {
            pathWithArguments = pathWithArguments.replace("{" + element.getKey() + "}", element.getValue());
        }

        return pathWithArguments;
    }
}
