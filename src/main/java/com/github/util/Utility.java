package com.github.util;

import com.github.exception.ApiError;
import com.google.gson.Gson;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

public class Utility {
  private static Gson gson = new Gson();

  public static <T> ResponseEntity<T> callHttpService(
      String url, HttpMethod method, HttpEntity httpEntity, Class<T> classType) {
    try{
    return new RestTemplate().exchange(url, method, httpEntity, classType);
    }catch(Exception e){
      throw new ApiError(e.getMessage());
    }
  }

  public static <T> T convertStringToObject(String jsonString, Class<T> classType) {
    return gson.fromJson(jsonString, classType);
  }
}
