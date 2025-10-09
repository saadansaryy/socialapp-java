package com.mecaps.socialApp.response;

import com.mecaps.socialApp.entity.Post;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PostResponse {
    private String content;
    private String postedAt;
    private UserResponse userResponse;

    public PostResponse(Post post){
        this.content = post.getContent();
        this.postedAt = post.getPostedAt();
        this.userResponse = new UserResponse(post.getAuthor());
    }
}
