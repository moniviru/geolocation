package com.project.ip.geolocation.exception.handler.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ErrorMessage {

  private String errorCode;
  private String description;
}
