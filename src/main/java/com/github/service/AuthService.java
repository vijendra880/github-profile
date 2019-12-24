package com.github.service;

import com.github.dto.ProfileDto;

public interface AuthService {
    String getProfileWithAuth(String code, String handle);
}
