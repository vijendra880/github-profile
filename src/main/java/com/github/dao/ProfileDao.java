package com.github.dao;

import java.util.Set;

public interface ProfileDao {

  void updateSearchHistory(String userId, String handle);

  void deleteSearchHistory(String userId, String handle);

  Set<String> getSearchHistory(String userId);
}
