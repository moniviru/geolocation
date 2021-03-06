package com.project.ip.geolocation.service.search;

import com.project.ip.geolocation.model.GeolocationModel;
import com.project.ip.geolocation.repository.GeolocationRepository;
import com.project.ip.geolocation.repository.mapper.GeolocationMapper;
import com.project.ip.geolocation.service.ip.ProcessIpService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Component
public class SearchIpServiceImpl implements SearchIpService {

  @Autowired
  private GeolocationRepository geolocationRepository;

  @Autowired
  private ProcessIpService processIpService;

  public Set<GeolocationModel> retrieveIpData(String ip) {

    String decimalIp = processIpService.convertIpToDecimal(ip).toString();

    List<GeolocationModel> foundIpFromList =
        geolocationRepository.findByIps_IpFrom(decimalIp).stream()
            .map(geolocation -> GeolocationMapper.map(geolocation)).collect(Collectors.toList());

    List<GeolocationModel> foundIpToList = geolocationRepository.findByIps_IpTo(decimalIp).stream()
        .map(geolocation -> GeolocationMapper.map(geolocation)).collect(Collectors.toList());

    return Stream.concat(foundIpFromList.stream(), foundIpToList.stream())
        .collect(Collectors.toSet());
  }

}
