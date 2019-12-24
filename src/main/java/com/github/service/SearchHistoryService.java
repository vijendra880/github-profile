package com.github.service;

import com.github.dto.ProfileSearchHistoryDto;

public interface SearchHistoryService {

    void addSearchHistory(String handle);

    void deleteSearchHistory(String handle);

    ProfileSearchHistoryDto getAllSearchHistory();
}
