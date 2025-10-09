package com.mecaps.socialApp.controller;

import com.mecaps.socialApp.request.PostRequest;
import com.mecaps.socialApp.response.PostResponse;
import com.mecaps.socialApp.serviceImplementation.PostServiceImpl;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/post")
public class PostController {

    final private PostServiceImpl postService;

    public PostController(PostServiceImpl postService){
        this.postService = postService;
    }

    @GetMapping("/get")
    public List<PostResponse> getAllPosts(){
        return postService.getAllPosts();
    }

    @GetMapping("/get/{id}")
    public PostResponse getPostById(@PathVariable Long id){
        return postService.getPostById(id);
    }

    @PostMapping("/create")
    public ResponseEntity<?> createPost(@RequestBody PostRequest postRequest){
        return postService.createPost(postRequest);
    }

    @PutMapping("/update/{id}")
    public PostResponse updatePost(@RequestBody PostRequest postRequest, @PathVariable Long id){
        return postService.updatePost(postRequest,id);
    }

    @DeleteMapping("/delete/{id}")
    public String deletePost(@PathVariable Long id){
        return postService.deletePost(id);
    }
}
