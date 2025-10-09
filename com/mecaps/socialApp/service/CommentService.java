package com.mecaps.socialApp.service;

import com.mecaps.socialApp.request.CommentRequest;
import com.mecaps.socialApp.response.CommentResponse;

import java.util.List;

public interface CommentService {
    List<CommentResponse> getAllComments();

    CommentResponse getCommentById(Long id);

    CommentResponse createComment(CommentRequest commentRequest);

    CommentResponse updateComment(CommentRequest commentRequest, Long id);

    String deleteComment(Long id);
}
