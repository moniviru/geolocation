package com.project.ip.geolocation.service;

import com.project.ip.geolocation.GeolocationApplicationTests;
import com.project.ip.geolocation.service.ip.ProcessIpServiceImpl;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;

import java.math.BigInteger;

public class ProcessIpServiceTest extends GeolocationApplicationTests {

  @InjectMocks
  private ProcessIpServiceImpl processIpService;

  @Test
  public void convertIpToDecimalTest() {
    Assert.assertEquals(new BigInteger("3384288566"),
        processIpService.convertIpToDecimal("201.184.37.54"));

    Assert.assertEquals(new BigInteger("3332636697"),
        processIpService.convertIpToDecimal("198.164.0.25"));

    Assert.assertEquals(new BigInteger("3568575766"),
        processIpService.convertIpToDecimal("212.180.37.22"));

    Assert.assertEquals(new BigInteger("2887778047"),
        processIpService.convertIpToDecimal("172.31.254.255"));

    Assert.assertEquals(new BigInteger("170006792"),
        processIpService.convertIpToDecimal("10.34.25.8"));

    Assert.assertEquals(new BigInteger("170014486"),
        processIpService.convertIpToDecimal("10.34.55.22"));

    Assert.assertEquals(new BigInteger("3512994818"),
        processIpService.convertIpToDecimal("209.100.12.2"));

    Assert.assertEquals(new BigInteger("3232255250"),
        processIpService.convertIpToDecimal("192.168.77.18"));

    Assert.assertEquals(new BigInteger("3397132809"),
        processIpService.convertIpToDecimal("202.124.34.9"));

    Assert.assertEquals(new BigInteger("167772160"),
        processIpService.convertIpToDecimal("10.0.0.0"));
  }
}
