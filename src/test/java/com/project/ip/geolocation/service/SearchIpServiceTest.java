package com.project.ip.geolocation.service;

import com.project.ip.geolocation.GeolocationApplicationTests;
import com.project.ip.geolocation.model.GeolocationModel;
import com.project.ip.geolocation.repository.GeolocationRepository;
import com.project.ip.geolocation.repository.dto.Geolocation;
import com.project.ip.geolocation.repository.dto.IpMixed;
import com.project.ip.geolocation.service.ip.ProcessIpService;
import com.project.ip.geolocation.service.search.SearchIpServiceImpl;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;

import java.math.BigInteger;
import java.util.List;
import java.util.Set;

public class SearchIpServiceTest extends GeolocationApplicationTests {

  @InjectMocks
  private SearchIpServiceImpl searchIpService;

  @Mock
  private GeolocationRepository geolocationRepository;

  @Mock
  private ProcessIpService processIpService;

  private static final String IP = "201.184.37.54";
  private static final String DECIMAL_IP = "3384288566";

  @Test
  public void retrieveIpDataTest() {
    Mockito.doReturn(new BigInteger(DECIMAL_IP)).when(processIpService).convertIpToDecimal(IP);
    Mockito
        .doReturn(List
            .of(Geolocation.builder().ips(IpMixed.builder().ipFrom(DECIMAL_IP).build()).build()))
        .when(geolocationRepository).findByIps_IpFrom(DECIMAL_IP);
    Mockito.doReturn(List.of(Geolocation.builder().build())).when(geolocationRepository)
        .findByIps_IpTo(DECIMAL_IP);
    Set<GeolocationModel> ipDataList = searchIpService.retrieveIpData(IP);
    Mockito.verify(geolocationRepository, Mockito.times(1)).findByIps_IpTo(DECIMAL_IP);
    Mockito.verify(geolocationRepository, Mockito.times(1)).findByIps_IpFrom(DECIMAL_IP);
    Assert.assertFalse(ipDataList.isEmpty());
  }


}
