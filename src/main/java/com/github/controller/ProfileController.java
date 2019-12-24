package com.github.controller;

import com.github.dto.ProfileSearchHistoryDto;
import com.github.service.ProfileService;
import com.github.service.SearchHistoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/v1/profile")
public class ProfileController {

  @Autowired private ProfileService profileService;

  @Autowired private SearchHistoryService searchHistoryService;

  @PostMapping
  public ResponseEntity<String> searchProfile(@RequestParam String handle) {
    return new ResponseEntity<String>(profileService.searchProfile(handle), HttpStatus.OK);
  }

  @GetMapping
  public ResponseEntity<ProfileSearchHistoryDto> getSearchHistoryDto() {
    return new ResponseEntity<ProfileSearchHistoryDto>(
        searchHistoryService.getAllSearchHistory(), HttpStatus.OK);
  }

  @DeleteMapping
  public ResponseEntity deleteSearchHistory(@RequestParam String handle) {
    searchHistoryService.deleteSearchHistory(handle);
    return new ResponseEntity(HttpStatus.OK);
  }
}
