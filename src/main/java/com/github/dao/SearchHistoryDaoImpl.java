package com.github.dao;

import org.springframework.stereotype.Repository;

import java.util.LinkedList;
import java.util.List;

@Repository
public class SearchHistoryDaoImpl implements SearchHistoryDao {
  private static List<String> searchHistoryList = new LinkedList<String>();

  public void addSearchHistory(String handle) {
    searchHistoryList.add(handle);
  }

  public void deleteSearchHistory(String handle) {
    searchHistoryList.remove(handle);
  }

  public List<String> getAllSearchHistory() {
    return searchHistoryList;
  }
}
