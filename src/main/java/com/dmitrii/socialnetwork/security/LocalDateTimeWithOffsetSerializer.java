package com.dmitrii.socialnetwork.security;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;

public class LocalDateTimeWithOffsetSerializer extends JsonSerializer<LocalDateTime> {

  private static final DateTimeFormatter FORMATTER =
      DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSSXXX");

  @Override
  public void serialize(
      LocalDateTime value,
      JsonGenerator gen,
      SerializerProvider serializers
  ) throws IOException {
    String str = value
        .atOffset(ZoneOffset.UTC)
        .format(FORMATTER);
    gen.writeString(str);
  }
}
