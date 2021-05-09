package com.project.ip.geolocation.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class IpNotFoundException extends RuntimeException {

  private static final String message = "Ip Address not found in the database";

  public IpNotFoundException() {
    super(message);
  }
}
