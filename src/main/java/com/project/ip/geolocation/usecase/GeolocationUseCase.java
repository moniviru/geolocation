package com.project.ip.geolocation.usecase;

import com.project.ip.geolocation.model.GeolocationModel;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Set;

public interface GeolocationUseCase {

  void load(MultipartFile file) throws IOException;

  Set<GeolocationModel> getIpInfo(String ip);
}
