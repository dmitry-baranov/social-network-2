package com.dmitrii.socialnetwork.config;

import com.dmitrii.socialnetwork.security.LocalDateTimeWithOffsetSerializer;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import java.time.LocalDateTime;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class JacksonConfig {

  @Bean
  public ObjectMapper objectMapper() {
    JavaTimeModule module = new JavaTimeModule();
    module.addSerializer(LocalDateTime.class, new LocalDateTimeWithOffsetSerializer());

    return new ObjectMapper()
        .registerModule(module)
        .disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
  }
}