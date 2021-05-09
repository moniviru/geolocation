package com.project.ip.geolocation.controller;

import com.project.ip.geolocation.controller.dto.GeolocationResponse;
import com.project.ip.geolocation.model.mapper.GeolocationModelMapper;
import com.project.ip.geolocation.usecase.GeolocationUseCase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Set;
import java.util.stream.Collectors;

@RestController
public class GeolocationControllerImpl implements GeolocationController {

  @Autowired
  private GeolocationUseCase geolocationUseCase;

  @Override
  public void load(MultipartFile file) throws IOException {
    geolocationUseCase.load(file);
  }

  @Override
  public Set<GeolocationResponse> getGeolocationById(String ip) {
    return geolocationUseCase.getIpInfo(ip).stream()
        .map(geolocation -> GeolocationModelMapper.map(geolocation)).collect(Collectors.toSet());
  }
}
