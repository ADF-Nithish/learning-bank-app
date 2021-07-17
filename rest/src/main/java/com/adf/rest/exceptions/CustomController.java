package com.adf.rest.exceptions;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
class CustomController {

  @ResponseBody
  @ExceptionHandler(CustomNotFound.class)
  @ResponseStatus(HttpStatus.NOT_FOUND)
  Map<String,String> handler(CustomNotFound ex) {
    Map<String,String> m = new HashMap<>();
    m.put("Error",ex.getMessage());
    return m;
  }

  @ResponseBody
  @ExceptionHandler(CustomBadRequest.class)
  @ResponseStatus(HttpStatus.BAD_REQUEST)
  Map<String,String> handler(CustomBadRequest ex) {
    Map<String,String> m = new HashMap<>();
    m.put("Error",ex.getMessage());
    return m;
  }
}