package com.bbsw.bitboxer2.practica.security.jwt;

import com.bbsw.bitboxer2.practica.security.entity.UserPrincipal;
import io.jsonwebtoken.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import java.util.Date;

import static com.bbsw.bitboxer2.practica.security.SecurityConstants.EXPIRATION_TIME;
import static com.bbsw.bitboxer2.practica.security.SecurityConstants.SECRET;

@Component
public class JwtProvider {

    private static final Logger logger = LoggerFactory.getLogger(JwtProvider.class);

    public String generateToken(Authentication authentication) {
        UserPrincipal userPrincipal = (UserPrincipal) authentication.getPrincipal();
        return Jwts.builder().setSubject(userPrincipal.getUsername())
            .setIssuedAt(new Date())
            .setExpiration(new Date(new Date().getTime() + EXPIRATION_TIME))
            .signWith(SignatureAlgorithm.HS512, SECRET)
            .compact();
    }

    public String getUsernameFromToken(String token) {
        return Jwts.parser().setSigningKey(SECRET).parseClaimsJws(token).getBody().getSubject();
    }

    public boolean validateToken(String token) {
        try {
            Jwts.parser().setSigningKey(SECRET).parseClaimsJws(token);
            return true;
        } catch (MalformedJwtException e) {
            logger.error("Malformed JWT token");
        } catch (UnsupportedJwtException e) {
            logger.error("Unsupported JWT token");
        } catch (ExpiredJwtException e) {
            logger.error("Expired JWT token");
        } catch (IllegalArgumentException e) {
            logger.error("Empty JWT token");
        } catch (SignatureException e) {
            logger.error("Signature fail in JWT token");
        }
        return false;
    }

}
