package com.adf.rest.exceptions;


public class CustomNotFound extends RuntimeException {

    public CustomNotFound(String message) {
      super(message);
    }
  }