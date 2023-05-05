package com.bbsw.bitboxer2.practica.security.dto;

import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;

import static com.bbsw.bitboxer2.practica.security.SecurityConstants.TOKEN_PREFIX;

@Getter
@Setter
public class JwtDTO {

    private String token;
    private String bearer = TOKEN_PREFIX;
    private String username;
    private Collection<? extends GrantedAuthority> authorities;

    public JwtDTO(String token, String username, Collection<? extends GrantedAuthority> authorities) {
        this.token = token;
        this.username = username;
        this.authorities = authorities;
    }

}
