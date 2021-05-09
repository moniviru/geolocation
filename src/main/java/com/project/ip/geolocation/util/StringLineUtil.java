package com.project.ip.geolocation.util;

import org.springframework.util.StringUtils;

public class StringLineUtil {

  public static String removeDoubleQuote(String line) {
    if (!line.isBlank()) {
      return StringUtils.replace(line, "\"", "");
    }
    return "";
  }
}
