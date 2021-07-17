package com.adf.rest.exceptions;


public class CustomBadRequest extends RuntimeException {

    public CustomBadRequest(String message) {
      super(message);
    }
  }