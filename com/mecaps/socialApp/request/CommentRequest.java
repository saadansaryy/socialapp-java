package com.mecaps.socialApp.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CommentRequest {

    private String commentString;
    private Long postId;
    private Long author;

}
