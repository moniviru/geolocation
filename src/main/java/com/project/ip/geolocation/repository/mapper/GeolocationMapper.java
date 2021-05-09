package com.project.ip.geolocation.repository.mapper;

import com.project.ip.geolocation.model.GeolocationModel;
import com.project.ip.geolocation.repository.dto.Geolocation;
import com.project.ip.geolocation.repository.dto.IpMixed;
import com.project.ip.geolocation.service.process.dto.GeolocationData;

public class GeolocationMapper {

  public static Geolocation map(GeolocationData geolocationData) {
    return Geolocation.builder()
        .ips(IpMixed.builder().ipFrom(geolocationData.getIpFrom()).ipTo(geolocationData.getIpTo())
            .build())
        .city(geolocationData.getCity()).country(geolocationData.getCountry())
        .countryCode(geolocationData.getCountryCode()).latitude(geolocationData.getLatitude())
        .longitude(geolocationData.getLongitude()).region(geolocationData.getRegion())
        .timeZone(geolocationData.getTimeZone()).build();
  }

  public static GeolocationModel map(Geolocation geolocation) {
    return GeolocationModel.builder().city(geolocation.getCity())
        .countryCode(geolocation.getCountryCode()).region(geolocation.getRegion())
        .timeZone(geolocation.getTimeZone()).build();
  }
}
