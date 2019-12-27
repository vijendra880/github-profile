package com.github.builder;

import com.github.dto.ProfileSearchHistoryDto;

import java.util.ArrayList;
import java.util.Set;

public class ProfileBuilder {

  public static ProfileSearchHistoryDto buildProfileSearchHistoryDto(Set<String> handles) {
    ProfileSearchHistoryDto profileSearchHistoryDto = new ProfileSearchHistoryDto();
    profileSearchHistoryDto.setSearchHistoryList(new ArrayList<>(handles));
    return profileSearchHistoryDto;
  }
}
