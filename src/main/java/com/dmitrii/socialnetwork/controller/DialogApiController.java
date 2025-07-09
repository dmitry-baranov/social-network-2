package com.dmitrii.socialnetwork.controller;

import com.dmitrii.socialnetwork.controller.api.DialogApi;
import com.dmitrii.socialnetwork.controller.model.DialogMessage;
import com.dmitrii.socialnetwork.controller.model.DialogUserIdSendPostRequest;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("${openapi.social-network.base-path:}")
@RequiredArgsConstructor
public class DialogApiController implements DialogApi {

  @Override
  public ResponseEntity<List<DialogMessage>> dialogUserIdListGet(String userId) {
    return DialogApi.super.dialogUserIdListGet(userId);
  }

  @Override
  public ResponseEntity<Void> dialogUserIdSendPost(
      String userId, DialogUserIdSendPostRequest dialogUserIdSendPostRequest) {
    return DialogApi.super.dialogUserIdSendPost(userId, dialogUserIdSendPostRequest);
  }

}
