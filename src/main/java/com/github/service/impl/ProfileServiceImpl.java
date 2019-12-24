package com.github.service.impl;

import com.github.configuration.GitHubConfiguration;
import com.github.service.ProfileService;
import com.github.service.SearchHistoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProfileServiceImpl implements ProfileService {

  @Autowired private GitHubConfiguration configuration;
  @Autowired private SearchHistoryService searchHistoryService;

  public String searchProfile(String handle) {
    searchHistoryService.addSearchHistory(handle);
    return getAuthUrl(handle);
  }

  private String getAuthUrl(String handle) {
    return new StringBuilder(configuration.getAuthUrl())
        .append("?client_id=")
        .append(configuration.getClientId())
        .append("&redirect_uri=")
        .append(configuration.getRedirectUri())
        .append(handle)
        .toString();
  }
}
