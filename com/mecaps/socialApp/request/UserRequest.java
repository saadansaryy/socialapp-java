package com.mecaps.socialApp.request;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class UserRequest {

    private String userName;
    private String email;
    private String password;

}
