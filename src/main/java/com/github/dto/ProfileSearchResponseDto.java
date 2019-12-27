package com.github.dto;

import com.google.gson.annotations.SerializedName;

public class ProfileSearchResponseDto {
    private String login;
    private String url;
    @SerializedName("repo-url")
    private String repoUrl;

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getRepoUrl() {
        return repoUrl;
    }

    public void setRepoUrl(String repoUrl) {
        this.repoUrl = repoUrl;
    }
}
