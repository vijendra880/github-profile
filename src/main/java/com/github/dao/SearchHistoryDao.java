package com.github.dao;

import java.util.List;

public interface SearchHistoryDao {
    void addSearchHistory(String handle);
    void deleteSearchHistory(String handle);
    List<String> getAllSearchHistory();
}
