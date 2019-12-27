package com.github.service.impl;

import com.github.builder.ProfileBuilder;
import com.github.configuration.GitHubConfiguration;
import com.github.dao.ProfileDao;
import com.github.dto.ProfileRequestDto;
import com.github.dto.ProfileSearchHistoryDto;
import com.github.dto.ProfileSearchResponseDto;
import com.github.service.ProfileService;
import com.github.util.Utility;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Service
public class ProfileServiceImpl implements ProfileService {

  @Autowired private GitHubConfiguration configuration;
  @Autowired private ProfileDao profileDao;

  public ProfileSearchResponseDto searchProfile(ProfileRequestDto profileRequestDto) {
    ProfileSearchResponseDto profileSearchResponseDto =
        getProfileDto(profileRequestDto.getHandle());
    profileDao.updateSearchHistory(profileRequestDto.getUserId(), profileRequestDto.getHandle());
    return profileSearchResponseDto;
  }

  @Override
  public ProfileSearchHistoryDto getSearchHistory(String userId) {
    Set<String> handles = profileDao.getSearchHistory(userId);
    return ProfileBuilder.buildProfileSearchHistoryDto(handles);
  }

  @Override
  public void deleteSearchHistory(String userId, String handle) {
    profileDao.deleteSearchHistory(userId, handle);
  }

  private ProfileSearchResponseDto getProfileDto(String handle) {
    return Utility.callHttpService(
            getProfileSearchUrl(handle), HttpMethod.GET, null, ProfileSearchResponseDto.class)
        .getBody();
  }

  private String getProfileSearchUrl(String handle) {
    return new StringBuilder(configuration.getProfileUrl()).append(handle).toString();
  }
}
