package com.mecaps.socialApp.controller;

import com.mecaps.socialApp.request.CommentRequest;
import com.mecaps.socialApp.response.CommentResponse;
import com.mecaps.socialApp.serviceImplementation.CommentServiceImpl;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/comment")
public class CommentController {

    final private CommentServiceImpl commentService;

    public CommentController(CommentServiceImpl commentService){
        this.commentService = commentService;
    }

    @GetMapping("/get")
    public List<CommentResponse> getAllComments(){
        return commentService.getAllComments();
    }

    @GetMapping("/get/{id}")
    public CommentResponse getCommentById(@PathVariable Long id){
        return commentService.getCommentById(id);
    }

    @PostMapping("/create")
    public CommentResponse createComment(@RequestBody CommentRequest commentRequest){
        return commentService.createComment(commentRequest);
    }

    @PutMapping("/update/{id}")
    public CommentResponse updateComment(@RequestBody CommentRequest commentRequest, @PathVariable Long id){
        return commentService.updateComment(commentRequest, id);
    }

    @DeleteMapping("/delete/{id}")
    public String deleteComment(@PathVariable Long id){
        return commentService.deleteComment(id);
    }
}
