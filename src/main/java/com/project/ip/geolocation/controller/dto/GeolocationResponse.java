package com.project.ip.geolocation.controller.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class GeolocationResponse {

  private String countryCode;
  private String region;
  private String city;
  private String timeZone;
}
