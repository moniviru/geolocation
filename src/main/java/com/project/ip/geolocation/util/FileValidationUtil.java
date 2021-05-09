package com.project.ip.geolocation.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class FileValidationUtil {

  private static Pattern filePattern = Pattern.compile("([^\\s]+(\\.(?i)(CSV|csv))$)");

  public static boolean validateGeolocationFileExtension(String fileName) {
    Matcher matcher = filePattern.matcher(fileName);
    if (matcher.matches()) {
      return true;
    }
    return false;
  }

}
