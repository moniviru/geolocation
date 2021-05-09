package com.project.ip.geolocation.usecase;

import static org.springframework.util.ResourceUtils.getFile;

import com.project.ip.geolocation.GeolocationApplicationTests;
import com.project.ip.geolocation.exception.InvalidInputException;
import com.project.ip.geolocation.exception.IpNotFoundException;
import com.project.ip.geolocation.model.GeolocationModel;
import com.project.ip.geolocation.service.process.ProcessFileService;
import com.project.ip.geolocation.service.search.SearchIpService;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockMultipartFile;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Set;

public class GeolocationUseCaseTest extends GeolocationApplicationTests {

  @InjectMocks
  private GeolocationUseCaseImpl geolocationUseCase;

  @Mock
  private ProcessFileService processFileService;

  @Mock
  private SearchIpService searchIpService;

  private static final String IP = "201.184.37.54";
  private static final String INVALID_IP = "201.184.37.5445";

  @Test
  public void loadTest() throws IOException {
    MockMultipartFile multipartFile = new MockMultipartFile("file", "test.csv",
        MediaType.TEXT_PLAIN_VALUE, new FileInputStream(getFile("src/test/test.csv")));
    geolocationUseCase.load(multipartFile);
    Mockito.verify(processFileService, Mockito.times(1)).processFile(multipartFile);
  }

  @Test
  public void loadInvalidFileExtensionTest() throws IOException {
    MockMultipartFile multipartFile = new MockMultipartFile("file", "test.png",
        MediaType.IMAGE_PNG_VALUE, new FileInputStream(getFile("src/test/test.png")));
    Assert.assertThrows(InvalidInputException.class, () -> geolocationUseCase.load(multipartFile));
    Mockito.verify(processFileService, Mockito.times(0)).processFile(multipartFile);
  }

  @Test
  public void getIpInfoTest() {
    Mockito.doReturn(Set.of(GeolocationModel.builder().build())).when(searchIpService)
        .retrieveIpData(IP);
    Set<GeolocationModel> geolocationModelSet = geolocationUseCase.getIpInfo(IP);
    Assert.assertFalse(geolocationModelSet.isEmpty());
  }

  @Test
  public void getIpInfoInvalidIpTest() {
    Assert.assertThrows(InvalidInputException.class,
        () -> geolocationUseCase.getIpInfo(INVALID_IP));
  }

  @Test
  public void getIpInfoNoFoundTest() {
    Mockito.doReturn(Set.of()).when(searchIpService).retrieveIpData(IP);
    Assert.assertThrows(IpNotFoundException.class, () -> geolocationUseCase.getIpInfo(IP));
  }
}
