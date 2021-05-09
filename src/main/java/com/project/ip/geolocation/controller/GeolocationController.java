package com.project.ip.geolocation.controller;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

import com.project.ip.geolocation.controller.dto.GeolocationResponse;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Set;

@RequestMapping(value = "/geolocation", produces = APPLICATION_JSON_VALUE)
public interface GeolocationController {

  @PostMapping(value = "/")
  void load(@RequestParam(value = "file", required = true) MultipartFile file) throws IOException;

  @GetMapping(path = "/{ipValue}")
  Set<GeolocationResponse> getGeolocationById(@PathVariable("ipValue") String ipValue);
}
