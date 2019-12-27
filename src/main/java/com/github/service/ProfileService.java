package com.github.service;

import com.github.dto.ProfileSearchHistoryDto;
import com.github.dto.ProfileRequestDto;
import com.github.dto.ProfileSearchResponseDto;

public interface ProfileService {

    ProfileSearchResponseDto searchProfile(ProfileRequestDto profileRequestDto);

    ProfileSearchHistoryDto getSearchHistory(String userId);

    void deleteSearchHistory(String userId, String handle);
}
