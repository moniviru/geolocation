package com.project.ip.geolocation.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.util.ResourceUtils.getFile;

import com.project.ip.geolocation.GeolocationApplicationWebTests;
import com.project.ip.geolocation.repository.GeolocationRepository;
import com.project.ip.geolocation.repository.dto.Geolocation;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.io.FileInputStream;
import java.util.List;

public class GeolocationControllerTest extends GeolocationApplicationWebTests {

  private static final String BASE_URL = "/geolocation/";
  private static final String IP = "201.184.37.54";
  private static final String DECIMAL_IP = "3384288566";

  @MockBean
  private GeolocationRepository geolocationRepository;

  @Test
  public void loadTest() throws Exception {
    MockMultipartFile multipartFile = new MockMultipartFile("file", "test.csv",
        MediaType.TEXT_PLAIN_VALUE, new FileInputStream(getFile("src/test/test.csv")));
    mockMvc.perform(MockMvcRequestBuilders.multipart(BASE_URL).file(multipartFile)).andDo(print())
        .andExpect(status().isOk());
  }

  @Test
  public void loadTestInvalidFileExtension() throws Exception {
    MockMultipartFile multipartFile = new MockMultipartFile("file", "test.png",
        MediaType.IMAGE_PNG_VALUE, new FileInputStream(getFile("src/test/test.png")));
    mockMvc.perform(MockMvcRequestBuilders.multipart(BASE_URL).file(multipartFile)).andDo(print())
        .andExpect(status().isBadRequest());
  }

  @Test
  public void getGeolocationByIdTest() throws Exception {
    Mockito.doReturn(List.of(Geolocation.builder().build())).when(geolocationRepository)
        .findByIps_IpFrom(DECIMAL_IP);
    Mockito.doReturn(List.of(Geolocation.builder().build())).when(geolocationRepository)
        .findByIps_IpTo(DECIMAL_IP);
    mockMvc.perform(get(BASE_URL + IP).header("Content-Type", "application/json"))
        .andExpect(status().isOk());
  }

  @Test
  public void getGeolocationByIdNotFoundTest() throws Exception {
    mockMvc.perform(get(BASE_URL + IP).header("Content-Type", "application/json"))
        .andExpect(status().isBadRequest());
  }
}
