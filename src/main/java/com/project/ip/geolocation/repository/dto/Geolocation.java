package com.project.ip.geolocation.repository.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Geolocation {

  @Id
  private IpMixed ips;
  private String countryCode;
  private String country;
  private String region;
  private String city;
  private String latitude;
  private String longitude;
  private String timeZone;
}
