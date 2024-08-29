package org.codeprofile.github.contracts;

import org.codeprofile.core.network.Server;
import org.codeprofile.github.services.ApiGitHubClient;
import org.codeprofile.shared.contracts.Service;
import org.codeprofile.shared.database.Model;
import org.codeprofile.shared.enums.ExceptionMessage;
import org.codeprofile.shared.exceptions.ExpectedBasePathException;
import org.codeprofile.shared.http.Controller;
import org.codeprofile.shared.strategies.ServiceStrategy;

public interface ApiGitHub<T extends Model> extends ServiceStrategy {
    String getBasePath();

    Class<T> getModel();

    default void validateExpectedArgumentsBasePath() throws ExpectedBasePathException {
        String[] parameters = ((Controller) this).getParameters();

        for (String parameter : parameters) {
            if (!getBasePath().contains(parameter)) {
                String message = ExceptionMessage.EXPECTED_ARGUMENT.getMessage() + ": " + parameter;
                throw new ExpectedBasePathException(message);
            }
        }
    }

    @Override
    default Service newService() {
        try {
            validateExpectedArgumentsBasePath();
            return new ApiGitHubClient<>(getBasePath(), getModel());
        } catch (ExpectedBasePathException e) {
            System.out.println(e.printMainMessage());
            e.printStackTrace();
            Server.stop(0);
        }

        return null;
    }
}
