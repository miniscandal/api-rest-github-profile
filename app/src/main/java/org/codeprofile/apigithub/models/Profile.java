package org.codeprofile.apigithub.models;

import org.codeprofile.apigithub.database.Model;

public class Profile extends Model {
    private String avatar_url;
    private String name;
    private String company;
    private String location;
    private String email;
    private String bio;
    private String twitter_username;
    private int public_repos;
    private int public_gists;
    private int followers;
    private int following;
    private String created_at;
    private String updated_at;

    public String getAvatarUrl() {
        return avatar_url;
    }

    public String getName() {
        return name;
    }

    public String getCompany() {
        return company;
    }

    public String getLocation() {
        return location;
    };

    public String getEmail() {
        return email;
    };

    public String getBio() {
        return bio;
    };

    public String getTwitterUsername() {
        return twitter_username;
    };

    public int getPublicRepos() {
        return public_repos;
    };

    public int getPublicGists() {
        return public_gists;
    };

    public int getFollowers() {
        return followers;
    };

    public int getFollowing() {
        return following;
    };

    public String getCreatedAt() {
        return created_at;
    };

    public String getUpdatedAt() {
        return updated_at;
    };
}
