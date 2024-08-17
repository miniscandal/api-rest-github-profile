package org.codeprofile.github.contracts;

import org.codeprofile.github.services.ApiGitHubClient;
import org.codeprofile.shared.contracts.Service;
import org.codeprofile.shared.database.Model;
import org.codeprofile.shared.http.Controller;
import org.codeprofile.shared.strategies.ServiceStrategy;

public interface ApiGitHub<T extends Model> extends ServiceStrategy {
    String getBasePath();

    Class<T> getModel();

    default void validateExpectedArgumentsBasePath() {
        for (String element : ((Controller) this).getParameters()) {
            if (!getBasePath().contains(element)) {
                throw new IllegalArgumentException("Base path does not contain parameter: " + element);
            }
        }
    }

    @Override
    default Service newService() {
        validateExpectedArgumentsBasePath();
        return new ApiGitHubClient<>(getBasePath(), getModel());
    }
}
