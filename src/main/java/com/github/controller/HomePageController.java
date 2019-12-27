package com.github.controller;

import com.github.constant.Constants;
import com.github.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/home")
public class HomePageController {

  @Autowired private AuthService authService;

  @GetMapping
  public String home(Model model) {
    model.addAttribute(Constants.AUTH_URL, authService.getAuthUrl());
    return Constants.INDEX_PAGE;
  }
}
