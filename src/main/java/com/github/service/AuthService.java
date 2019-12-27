package com.github.service;

import org.springframework.ui.Model;

public interface AuthService {
    String loginUsingTemporaryCode(String code, Model model);

    String getAuthUrl();
}
