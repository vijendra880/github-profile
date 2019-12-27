package com.github.dao;

import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
public class ProfileDaoImpl implements ProfileDao {
  private Map<String, Set<String>> profileSearchHistory = new HashMap<>();

  public void updateSearchHistory(String userId, String handle) {
    Set<String> handles = profileSearchHistory.get(userId);
    if (handles == null) {
      handles = new HashSet<>();
    }
    handles.add(handle);
    profileSearchHistory.put(userId, handles);
  }

  public void deleteSearchHistory(String userId, String handle) {
    Set<String> handles = profileSearchHistory.get(userId);
    if (handles != null) {
      handles.remove(handle);
    }
  }

  public Set<String> getSearchHistory(String userId) {
    return profileSearchHistory.getOrDefault(userId, new HashSet<>());
  }
}
