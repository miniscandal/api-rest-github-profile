package org.codeprofile.apirestgithub.services;

import org.codeprofile.shared.interfaces.Service;

public class ApiGitHubService implements Service {
    public String getAnime(String name) {
        System.out.println(name);

        return name;
    }
}
