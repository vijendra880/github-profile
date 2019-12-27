package com.github.dao;

import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Map;

@Repository
public class AuthorizationDao {
  private static Map<String, String> accessTokenMap = new HashMap<String, String>();

  public void saveAccessToken(String handle, String accessToken) {
    accessTokenMap.put(handle, accessToken);
  }

}
