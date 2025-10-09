package com.mecaps.socialApp.serviceImplementation;

import com.mecaps.socialApp.entity.Post;
import com.mecaps.socialApp.entity.User;
import com.mecaps.socialApp.repository.PostRepository;
import com.mecaps.socialApp.repository.UserRepository;
import com.mecaps.socialApp.request.PostRequest;
import com.mecaps.socialApp.response.PostResponse;
import com.mecaps.socialApp.service.PostService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class PostServiceImpl implements PostService {
    final private PostRepository postRepository;
    final private UserRepository userRepository;

    public PostServiceImpl(PostRepository postRepository, UserRepository userRepository){
        this.postRepository = postRepository;
        this.userRepository = userRepository;

    }

    public List<PostResponse> getAllPosts() {
        List<Post> postList = postRepository.findAll();

        return postList.stream().map(PostResponse::new).toList();
    }

    public PostResponse getPostById(Long id) {
        Post post = postRepository.findById(id).orElseThrow(() -> new RuntimeException("Id not found"));

        return new PostResponse(post);
    }

    public ResponseEntity<?> createPost(PostRequest postRequest) {
        User user = userRepository.findById(postRequest.getAuthor()).orElseThrow(() -> new RuntimeException("Id not found"));

        Post post = new Post();

        post.setContent(postRequest.getContent());
        post.setAuthor(user);

        Post save = postRepository.save(post);

        PostResponse postResponse =  new PostResponse(save);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body((Map.of(
                        "message","Post created successfully",
                        "body",postResponse,
                        "success",true
                )));
    }

    public PostResponse updatePost(PostRequest postRequest, Long id) {
        User user = userRepository.findById(postRequest.getAuthor()).orElseThrow(() -> new RuntimeException("User Id not found"));

        Post post = postRepository.findById(id).orElseThrow(() -> new RuntimeException("Post Id not found"));

        post.setContent(postRequest.getContent());
        post.setAuthor(user);

        Post save = postRepository.save(post);

        return new PostResponse(save);
    }

    public String deletePost(Long id) {
        Post post = postRepository.findById(id).orElseThrow(() -> new RuntimeException("Id not found"));
        postRepository.delete(post);
        return "Post with id : " + id + " deleted successfully";
    }
}
