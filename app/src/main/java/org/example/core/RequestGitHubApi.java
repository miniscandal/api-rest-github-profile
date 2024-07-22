package org.example.core;

import java.io.IOException;

public class RequestGitHubApi {
    private static final String BASE_URL = "https://api.github.com";
    private final String uri;
    private final ApiRequestExecutor apiRequestExecutor;

    public RequestGitHubApi(String path, ApiRequestExecutor apiRequestExecutor) {
        this.uri = BASE_URL + "/" + path;
        this.apiRequestExecutor = apiRequestExecutor;
    }

    public String getUri() {
        return uri;
    }

    public byte[] requestSingleObject() {
        return executeRequest(() -> apiRequestExecutor.executeSingleObjectResponse(uri));
    }

    public byte[] requestObjectList(String uri) {
        return executeRequest(() -> apiRequestExecutor.executeArrayObjectResponse(uri));

    }

    private byte[] executeRequest(RequestExecuter executer) {
        try {
            return executer.execute();
        } catch (IOException | InterruptedException e) {
            throw new RuntimeException("Error executing request", e);
        }
    }

    @FunctionalInterface
    private interface RequestExecuter {
        byte[] execute() throws IOException, InterruptedException;
    }
}
