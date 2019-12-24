package com.github.service.impl;

import com.github.configuration.GitHubConfiguration;
import com.github.dto.AccessTokenResponseDto;
import com.github.service.AuthService;
import com.github.util.Utility;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;

@Service
public class AuthServiceImpl implements AuthService {

  @Autowired private GitHubConfiguration configuration;

  public String getProfileWithAuth(String code, String handle) {
    String accessToken = getAccessTokenUsingCode(code);
    return getProfileDto(accessToken);
  }

  private String getProfileDto(String accessToken) {
    HttpHeaders httpHeaders = new HttpHeaders();
    httpHeaders.set("Authorization", new StringBuilder("token ").append(accessToken).toString());
    HttpEntity httpEntity = new HttpEntity(null, httpHeaders);
    return Utility.callHttpService(
            configuration.getProfileUrl(), HttpMethod.GET, httpEntity, String.class)
        .getBody();
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
