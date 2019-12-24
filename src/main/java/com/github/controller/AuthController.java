package com.github.controller;

import com.github.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/v1/auth")
public class AuthController {

  @Autowired private AuthService authService;

  @GetMapping
  public ResponseEntity<String> getProfileWithAuth(
      @RequestParam String code, @RequestParam String handle) {
    return new ResponseEntity<String>(
        authService.getProfileWithAuth(code, handle), HttpStatus.OK);
  }
}
