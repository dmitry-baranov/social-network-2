package com.dmitrii.socialnetwork.controller;

import com.dmitrii.socialnetwork.controller.api.FriendApi;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("${openapi.social-network.base-path:}")
@RequiredArgsConstructor
public class FriendApiController implements FriendApi {


  @Override
  public ResponseEntity<Void> friendDeleteUserIdPut(String userId) {
    return FriendApi.super.friendDeleteUserIdPut(userId);
  }

  @Override
  public ResponseEntity<Void> friendSetUserIdPut(String userId) {
    return FriendApi.super.friendSetUserIdPut(userId);
  }

}
