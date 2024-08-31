package org.example.frameworkstudy.auth.entity;

import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;

@Getter
@Setter
public class AuthMember extends User {

    private String accessToken;
    private String refreshToken;

    public AuthMember(UserDetails userDetails) {
        super(userDetails.getUsername(),
                "",
                userDetails.getAuthorities());
    }

}
