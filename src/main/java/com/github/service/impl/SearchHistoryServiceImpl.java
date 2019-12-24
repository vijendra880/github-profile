package com.github.service.impl;

import com.github.dao.SearchHistoryDao;
import com.github.dto.ProfileSearchHistoryDto;
import com.github.service.SearchHistoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SearchHistoryServiceImpl implements SearchHistoryService {

  @Autowired private SearchHistoryDao searchHistoryDao;

  public void addSearchHistory(String handle) {
    searchHistoryDao.addSearchHistory(handle);
  }

  public void deleteSearchHistory(String handle) {
    searchHistoryDao.deleteSearchHistory(handle);
  }

  public ProfileSearchHistoryDto getAllSearchHistory() {
    ProfileSearchHistoryDto profileSearchHistoryDto = new ProfileSearchHistoryDto();
    profileSearchHistoryDto.setSearchHistoryList(searchHistoryDao.getAllSearchHistory());
    return profileSearchHistoryDto;
  }
}
