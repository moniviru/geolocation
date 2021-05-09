package com.project.ip.geolocation.usecase;

import com.project.ip.geolocation.exception.InvalidInputException;
import com.project.ip.geolocation.model.GeolocationModel;
import com.project.ip.geolocation.service.process.ProcessFileService;
import com.project.ip.geolocation.service.search.SearchIpService;
import com.project.ip.geolocation.util.FileValidationUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class GeolocationUseCaseImpl implements GeolocationUseCase {

  @Autowired
  private ProcessFileService processFileService;

  @Autowired
  private SearchIpService searchIpService;

  public void load(MultipartFile file) throws IOException {
    if (FileValidationUtil.validateGeolocationFileExtension(file.getOriginalFilename())) {
      processFileService.processFile(file);
    } else {
      throw new InvalidInputException("Incorrect file type attached, only use csv files");
    }
  }

  public Set<GeolocationModel> getIpInfo(String decimalIp) {
    return searchIpService.retrieveIpData(decimalIp);
  }
}
