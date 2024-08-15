package org.codeprofile.internal.models;

import org.codeprofile.shared.database.Model;

public class About extends Model {
    private String name;
    private String description;
    private String author;
    private String nickname;
    private String license;
    private String email;
    private String linkedin;
    private String github;

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public String getAuthor() {
        return author;
    }

    public String getNickname() {
        return nickname;
    }

    public String getLicense() {
        return license;
    }

    public String getEmail() {
        return email;
    }

    public String getLinkedin() {
        return linkedin;
    }

    public String getGithub() {
        return github;
    }

    public About() {
        this.name = "API Name";
        this.description = "This API is ...";
        this.author = "Oscar Gonzalez";
        this.nickname = "miniscandal";
        this.license = "MIT License";
        this.email = "oscar01dev@gmail.com";
        this.linkedin = "https://linkedin.com/oscar-gonzalez";
        this.github = "https://github.com/miniscandal";
    }
}
