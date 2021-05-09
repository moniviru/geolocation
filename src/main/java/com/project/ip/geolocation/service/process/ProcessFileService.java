package com.project.ip.geolocation.service.process;

import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface ProcessFileService {

  void processFile(MultipartFile file) throws IOException;
}
