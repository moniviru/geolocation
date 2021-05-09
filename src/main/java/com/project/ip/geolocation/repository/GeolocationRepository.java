package com.project.ip.geolocation.repository;

import com.project.ip.geolocation.repository.dto.Geolocation;
import com.project.ip.geolocation.repository.dto.IpMixed;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface GeolocationRepository extends MongoRepository<Geolocation, IpMixed> {

  List<Geolocation> findByIps_IpFrom(String ip);

  List<Geolocation> findByIps_IpTo(String ip);
}
