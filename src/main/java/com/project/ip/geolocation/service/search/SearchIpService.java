package com.project.ip.geolocation.service.search;

import com.project.ip.geolocation.model.GeolocationModel;

import java.util.Set;

public interface SearchIpService {

  Set<GeolocationModel> retrieveIpData(String ip);
}
