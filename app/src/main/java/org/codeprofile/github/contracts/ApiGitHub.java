package org.codeprofile.github.contracts;

import org.codeprofile.github.database.Model;
import org.codeprofile.github.services.ApiClient;
import org.codeprofile.shared.contracts.Service;
import org.codeprofile.shared.strategies.ServiceStrategy;

public interface ApiGitHub<T extends Model> extends ServiceStrategy {
    String getBasePath();

    Class<T> getModel();

    @Override
    default Service getService() {
        return new ApiClient();
    }
}
