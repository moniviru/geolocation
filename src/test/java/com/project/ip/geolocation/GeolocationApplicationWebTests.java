package com.project.ip.geolocation;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.context.WebApplicationContext;

@AutoConfigureMockMvc
public class GeolocationApplicationWebTests extends GeolocationApplicationTests {

  @Autowired
  protected WebApplicationContext webApplicationContext;

  @Autowired
  protected MockMvc mockMvc;

  @Test
  public void contextLoads() {
    assertThat(webApplicationContext).isNotNull();
  }
}
