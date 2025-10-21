package com.mecaps.socialApp.response;

import com.mecaps.socialApp.entity.Comment;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CommentResponse {
    private String commentString;
    private PostResponse postResponse;
    private UserResponse userResponse;


    public CommentResponse(Comment comment){
        this.commentString = comment.getCommentString();
        this.postResponse = new PostResponse(comment.getPostId());
        this.userResponse = new UserResponse(comment.getAuthor());
    }
}
