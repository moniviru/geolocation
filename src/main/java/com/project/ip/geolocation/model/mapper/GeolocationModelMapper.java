package com.project.ip.geolocation.model.mapper;

import com.project.ip.geolocation.controller.dto.GeolocationResponse;
import com.project.ip.geolocation.model.GeolocationModel;

public class GeolocationModelMapper {

  public static GeolocationResponse map(GeolocationModel geolocation) {
    return GeolocationResponse.builder().city(geolocation.getCity())
        .countryCode(geolocation.getCountryCode()).region(geolocation.getRegion())
        .timeZone(geolocation.getTimeZone()).build();
  }
}
