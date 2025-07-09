package com.dmitrii.socialnetwork.controller;

import com.dmitrii.socialnetwork.controller.api.PostApi;
import com.dmitrii.socialnetwork.controller.model.PostCreatePostRequest;
import com.dmitrii.socialnetwork.controller.model.PostDto;
import com.dmitrii.socialnetwork.controller.model.PostUpdatePutRequest;
import java.math.BigDecimal;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("${openapi.social-network.base-path:}")
@RequiredArgsConstructor
public class PostApiController implements PostApi {

  @Override
  public ResponseEntity<String> postCreatePost(PostCreatePostRequest postCreatePostRequest) {
    return PostApi.super.postCreatePost(postCreatePostRequest);
  }

  @Override
  public ResponseEntity<Void> postDeleteIdPut(String id) {
    return PostApi.super.postDeleteIdPut(id);
  }

  @Override
  public ResponseEntity<List<PostDto>> postFeedGet(BigDecimal offset, BigDecimal limit) {
    return PostApi.super.postFeedGet(offset, limit);
  }

  @Override
  public ResponseEntity<PostDto> postGetIdGet(String id) {
    return PostApi.super.postGetIdGet(id);
  }

  @Override
  public ResponseEntity<Void> postUpdatePut(PostUpdatePutRequest postUpdatePutRequest) {
    return PostApi.super.postUpdatePut(postUpdatePutRequest);
  }

}
