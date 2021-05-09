package com.project.ip.geolocation.service.process;

import com.project.ip.geolocation.exception.InvalidInputException;
import com.project.ip.geolocation.repository.GeolocationRepository;
import com.project.ip.geolocation.repository.mapper.GeolocationMapper;
import com.project.ip.geolocation.service.process.dto.GeolocationData;
import com.project.ip.geolocation.util.StringLineUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.stream.Collectors;

@Component
@Slf4j
public class ProcessFileServiceImpl implements ProcessFileService {

  @Autowired
  private GeolocationRepository repository;

  public void processFile(MultipartFile file) throws IOException {
    deleteGeolocationData();
    List<String> lines =
        new BufferedReader(new InputStreamReader(file.getInputStream(), StandardCharsets.UTF_8))
            .lines().collect(Collectors.toList());
    lines.parallelStream().forEach(line -> saveGeolocationRow(line));
  }

  private GeolocationData convert(String lineToConvert) {
    String[] line = lineToConvert.split(",");
    if (line.length == 9) {
      return GeolocationData.builder().ipFrom(StringLineUtil.removeDoubleQuote(line[0]))
          .ipTo(StringLineUtil.removeDoubleQuote(line[1]))
          .countryCode(StringLineUtil.removeDoubleQuote(line[2]))
          .country(StringLineUtil.removeDoubleQuote(line[3]))
          .region(StringLineUtil.removeDoubleQuote(line[4]))
          .city(StringLineUtil.removeDoubleQuote(line[5]))
          .latitude(StringLineUtil.removeDoubleQuote(line[6]))
          .longitude(StringLineUtil.removeDoubleQuote(line[7]))
          .timeZone(StringLineUtil.removeDoubleQuote(line[8])).build();
    } else {
      throw new InvalidInputException("Data input invalid line: " + lineToConvert);
    }
  }

  private void deleteGeolocationData() {
    repository.deleteAll();
  }

  private void saveGeolocationRow(String line) {
    repository.save(GeolocationMapper.map(convert(line)));
    log.debug(line);
  }
}
