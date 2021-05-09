package com.project.ip.geolocation.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class IpValidationUtil {

  private static Pattern ipPattern = Pattern.compile(
      "^((25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\\.){3}(25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)$");

  public static boolean validateIp(String ip) {
    Matcher matcher = ipPattern.matcher(ip);
    if (matcher.matches()) {
      return true;
    }
    return false;
  }
}
