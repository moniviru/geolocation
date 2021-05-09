package com.project.ip.geolocation.service.process.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class GeolocationData {

  private String ipFrom;
  private String ipTo;
  private String countryCode;
  private String country;
  private String region;
  private String city;
  private String latitude;
  private String longitude;
  private String timeZone;
}
