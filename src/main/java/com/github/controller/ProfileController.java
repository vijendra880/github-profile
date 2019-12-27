package com.github.controller;

import com.github.dto.ErrorResponse;
import com.github.dto.ProfileSearchHistoryDto;
import com.github.dto.ProfileRequestDto;
import com.github.dto.ProfileSearchResponseDto;
import com.github.service.ProfileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/v1/profile")
public class ProfileController {

  @Autowired private ProfileService profileService;

  @PostMapping
  public ResponseEntity<ProfileSearchResponseDto> searchProfile(
      @ModelAttribute ProfileRequestDto profileRequestDto) {
    return new ResponseEntity<ProfileSearchResponseDto>(
        profileService.searchProfile(profileRequestDto), HttpStatus.OK);
  }

  @GetMapping
  public ResponseEntity<ProfileSearchHistoryDto> getSearchHistory(
      @ModelAttribute ProfileRequestDto profileRequestDto) {
    return new ResponseEntity<ProfileSearchHistoryDto>(
        profileService.getSearchHistory(profileRequestDto.getUserId()), HttpStatus.OK);
  }

  // TODO- change it to @DeleteMapping
  @GetMapping("/delete")
  public ResponseEntity<ErrorResponse> deleteSearchHistory(
      @ModelAttribute ProfileRequestDto profileRequestDto) {
    profileService.deleteSearchHistory(
        profileRequestDto.getUserId(), profileRequestDto.getHandle());
    return new ResponseEntity(new ErrorResponse(), HttpStatus.OK);
  }
}
