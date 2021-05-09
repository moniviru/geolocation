package com.project.ip.geolocation.usecase;

import com.project.ip.geolocation.exception.InvalidInputException;
import com.project.ip.geolocation.exception.IpNotFoundException;
import com.project.ip.geolocation.model.GeolocationModel;
import com.project.ip.geolocation.service.process.ProcessFileService;
import com.project.ip.geolocation.service.search.SearchIpService;
import com.project.ip.geolocation.util.FileValidationUtil;
import com.project.ip.geolocation.util.IpValidationUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Set;

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

  public Set<GeolocationModel> getIpInfo(String ip) {
    if (IpValidationUtil.validateIp(ip)) {
      Set<GeolocationModel> ipDataList = searchIpService.retrieveIpData(ip);
      if (ipDataList.isEmpty()) {
        throw new IpNotFoundException();
      } else {
        return searchIpService.retrieveIpData(ip);
      }
    } else {
      throw new InvalidInputException("The Ip Address doesn't match with a valid Ip");
    }
  }
}
