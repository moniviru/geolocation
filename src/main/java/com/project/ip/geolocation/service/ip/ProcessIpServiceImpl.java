package com.project.ip.geolocation.service.ip;

import org.springframework.stereotype.Component;

import java.math.BigInteger;

@Component
public class ProcessIpServiceImpl implements ProcessIpService {

  public BigInteger convertIpToDecimal(String ip) {
    String[] ipSection = ip.split("\\.");
    int n = ipSection.length - 1;
    BigInteger result = new BigInteger("0");
    BigInteger potential = new BigInteger("256");

    for (int i = 0; i < ipSection.length; i++) {
      result = result.add(new BigInteger(ipSection[i]).multiply(potential.pow(n)));
      n--;
    }
    return result;
  }
}
