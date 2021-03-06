package com.github.controller;

import com.github.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/v1/auth")
public class AuthController {

  @Autowired private AuthService authService;

  @GetMapping
  public String loginUsingTemporaryCode(@RequestParam String code, Model model) {
    return authService.loginUsingTemporaryCode(code, model);
  }
}
