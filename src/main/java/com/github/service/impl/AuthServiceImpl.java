package com.github.service.impl;

import com.github.configuration.GitHubConfiguration;
import com.github.constant.Constants;
import com.github.dao.AuthorizationDao;
import com.github.dto.AccessTokenResponseDto;
import com.github.dto.ProfileSearchResponseDto;
import com.github.service.AuthService;
import com.github.util.Utility;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

@Service
public class AuthServiceImpl implements AuthService {

  @Autowired private GitHubConfiguration configuration;
  @Autowired private AuthorizationDao authorizationDao;
  @Autowired private AuthService authService;

  public String loginUsingTemporaryCode(String code, Model model) {
    try {
      String accessToken = getAccessTokenUsingCode(code);
      String handle = getHandleName(accessToken);
      authorizationDao.saveAccessToken(handle, accessToken);
      model.addAttribute(Constants.USER_ID, handle);
      return Constants.HOME_PAGE;
    } catch (Exception e) {
      model.addAttribute(Constants.AUTH_URL, authService.getAuthUrl());
      return Constants.ERROR_PAGE;
    }
  }

  private String getHandleName(String accessToken) {
    HttpHeaders httpHeaders = new HttpHeaders();
    httpHeaders.set("Authorization", new StringBuilder("token ").append(accessToken).toString());
    ProfileSearchResponseDto profileSearchResponseDto =
        Utility.callHttpService(
                configuration.getUserUrl(),
                HttpMethod.GET,
                new HttpEntity(httpHeaders),
                ProfileSearchResponseDto.class)
            .getBody();
    return profileSearchResponseDto.getLogin();
  }

  public String getAuthUrl() {
    return new StringBuilder(configuration.getAuthUrl())
        .append("?client_id=")
        .append(configuration.getClientId())
        .append("&redirect_uri=")
        .append(configuration.getRedirectUri())
        .toString();
  }

  private String getAccessTokenUsingCode(String code) {
    HttpHeaders httpHeaders = new HttpHeaders();
    httpHeaders.set("Accept", "application/json");
    AccessTokenResponseDto accessTokenResponseDto =
        Utility.callHttpService(
                getAccessTokenUrl(code),
                HttpMethod.POST,
                new HttpEntity(null, httpHeaders),
                AccessTokenResponseDto.class)
            .getBody();
    return accessTokenResponseDto.getAccess_token();
  }

  private String getAccessTokenUrl(String code) {
    return new StringBuilder(configuration.getAccessTokenUrl())
        .append("?client_id=")
        .append(configuration.getClientId())
        .append("&client_secret=")
        .append(configuration.getClientSecret())
        .append("&code=")
        .append(code)
        .toString();
  }
}
