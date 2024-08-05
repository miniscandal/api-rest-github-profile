package org.example.models;

public class Repo extends Model {
    private String id;
    private String name;
    private String full_name;
    private boolean isPrivate;
    private String html_url;
    private String description;
    private String url;
    private String commits_url;
    private String comments_url;
    private String created_at;
    private String updated_at;
    private String pushed_at;
    private String clone_url;
    private int size;

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getFullName() {
        return full_name;
    }

    public boolean isPrivate() {
        return isPrivate;
    }

    public String getHtmlUrl() {
        return html_url;
    }

    public String getDescription() {
        return description;
    }

    public String getUrl() {
        return url;
    }

    public String getCommitsUrl() {
        return commits_url;
    }

    public String getCommentsUrl() {
        return comments_url;
    }

    public String getCreatedAt() {
        return created_at;
    }

    public String getUpdatedAt() {
        return updated_at;
    }

    public String getPushedAt() {
        return pushed_at;
    }

    public String getCloneUrl() {
        return clone_url;
    }

    public int getSize() {
        return size;
    }

}
