package com.mecaps.socialApp.service;

import com.mecaps.socialApp.request.PostRequest;
import com.mecaps.socialApp.response.PostResponse;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface PostService {
    List<PostResponse> getAllPosts();

    PostResponse getPostById(Long id);

    ResponseEntity<?> createPost(PostRequest postRequest);

    PostResponse updatePost(PostRequest postRequest, Long id);

    String deletePost(Long id);
}
