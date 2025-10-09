package com.mecaps.socialApp.request;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class PostRequest {

    private String content;
    private Long author;

}
