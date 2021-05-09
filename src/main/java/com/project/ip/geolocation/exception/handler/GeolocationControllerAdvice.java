package com.project.ip.geolocation.exception.handler;

import com.project.ip.geolocation.exception.InvalidInputException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.io.IOException;

@ControllerAdvice
@Slf4j
public class GeolocationControllerAdvice {

  @ExceptionHandler({InvalidInputException.class, IOException.class})
  @ResponseStatus(value = HttpStatus.BAD_REQUEST)
  public @ResponseBody ResponseEntity<ErrorMessage> handleBadRequest(RuntimeException ex) {
    log.error("Something went wrong processing the input file", ex);
    return ResponseEntity.status(HttpStatus.BAD_REQUEST).contentType(MediaType.APPLICATION_JSON)
        .body(new ErrorMessage(HttpStatus.BAD_REQUEST.name(), ex.getMessage()));
  }
}
