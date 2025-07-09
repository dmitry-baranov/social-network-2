package com.dmitrii.socialnetwork.controller.api;

import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Objects;
import org.springframework.web.context.request.NativeWebRequest;

public class ApiUtil {

  public static void setExampleResponse(NativeWebRequest req, String contentType, String example) {
    try {
      HttpServletResponse res = req.getNativeResponse(HttpServletResponse.class);
      Objects.requireNonNull(res).setCharacterEncoding("UTF-8");
      res.addHeader("Content-Type", contentType);
      res.getWriter().print(example);
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
  }
}
