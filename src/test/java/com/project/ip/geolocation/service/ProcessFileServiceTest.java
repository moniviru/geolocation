package com.project.ip.geolocation.service;

import static org.springframework.util.ResourceUtils.getFile;

import com.project.ip.geolocation.GeolocationApplicationTests;
import com.project.ip.geolocation.exception.InvalidInputException;
import com.project.ip.geolocation.repository.GeolocationRepository;
import com.project.ip.geolocation.service.process.ProcessFileServiceImpl;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockMultipartFile;

import java.io.FileInputStream;
import java.io.IOException;

public class ProcessFileServiceTest extends GeolocationApplicationTests {

  @Mock
  private GeolocationRepository geolocationRepository;

  @InjectMocks
  private ProcessFileServiceImpl processFileService;

  @Test
  public void processFileTest() throws IOException {
    Mockito.doNothing().when(geolocationRepository).deleteAll();
    MockMultipartFile multipartFile = new MockMultipartFile("file", "test.csv",
        MediaType.TEXT_PLAIN_VALUE, new FileInputStream(getFile("src/test/test.csv")));
    processFileService.processFile(multipartFile);
    Mockito.verify(geolocationRepository, Mockito.times(1)).deleteAll();
    Mockito.verify(geolocationRepository, Mockito.times(9)).save(Mockito.any());
  }

  @Test
  public void processFileInvalidRowTest() throws IOException {
    Mockito.doNothing().when(geolocationRepository).deleteAll();
    MockMultipartFile multipartFile = new MockMultipartFile("file", "test2.csv",
        MediaType.TEXT_PLAIN_VALUE, new FileInputStream(getFile("src/test/test2.csv")));
    Assert.assertThrows(InvalidInputException.class,
        () -> processFileService.processFile(multipartFile));
  }
}
