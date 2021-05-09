package com.project.ip.geolocation.exception.handler;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ErrorMessage {
  private String description;
  private String errorCode;
}
