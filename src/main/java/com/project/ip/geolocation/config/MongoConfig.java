package com.project.ip.geolocation.config;

import com.mongodb.ConnectionString;
import com.mongodb.MongoClientSettings;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.config.AbstractMongoClientConfiguration;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

import java.util.Collection;
import java.util.Collections;

@Configuration
@EnableMongoRepositories(basePackages = "com.project.ip.geolocation.repository")
public class MongoConfig extends AbstractMongoClientConfiguration {

  @Override
  protected String getDatabaseName() {
    return "test";
  }

  @Override
  public MongoClient mongoClient() {
    ConnectionString connectionString = new ConnectionString(
        "mongodb+srv://monvrz:monvrz@cluster0.uo6re.mongodb.net/test?retryWrites=true&w=majority");
    MongoClientSettings mongoClientSettings =
        MongoClientSettings.builder().applyConnectionString(connectionString).build();

    return MongoClients.create(mongoClientSettings);
  }

  @Override
  public Collection getMappingBasePackages() {
    return Collections.singleton("com.project.ip.geolocation");
  }
}
