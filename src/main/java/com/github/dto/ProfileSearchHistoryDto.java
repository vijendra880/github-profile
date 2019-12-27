package com.github.dto;

import java.util.List;

public class ProfileSearchHistoryDto{
    private List<String> searchHistoryList;

    public List<String> getSearchHistoryList() {
        return searchHistoryList;
    }

    public void setSearchHistoryList(List<String> searchHistoryList) {
        this.searchHistoryList = searchHistoryList;
    }
}
