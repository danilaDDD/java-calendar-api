package com.calendar.components;

import com.calendar.models.AuthEntity;
import com.calendar.services.UserService;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.Duration;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;

@Component
@Log
public class JwtProvider {

    private UserService userService;
    private Secrets secrets;

    @Autowired
    public void setUserService(UserService userService){
        this.userService = userService;
    }

    @Autowired
    public void setSecrets(Secrets secrets) {
        this.secrets = secrets;
    }

    public String generateClientToken(AuthEntity entity) {
        return generateTokenByEntity(entity, secrets.getApiClientSecret(), secrets.getApiClientActualDays());
    }

    public String generateUserToken(AuthEntity entity){
        return generateTokenByEntity(entity, secrets.getUserSecret(), secrets.getUserActualDays());
    }

    public String generateTokenByEntity(AuthEntity entity, String secret, int actualDays){
        Date expirationDate = Date.from(LocalDate.now().plusDays(actualDays).atStartOfDay().atZone(ZoneId.systemDefault()).toInstant());
        return Jwts.builder()
                .setSubject(entity.getLogin() + ":" + entity.getEncodedPassword())
                .setExpiration(expirationDate)
                .signWith(SignatureAlgorithm.HS512, secret)
                .compact();
    }

    public String getSubjectFromToken(String token, String secret){
        Claims claims = Jwts.parser().setSigningKey(secret).parseClaimsJws(token).getBody();
        return claims.getSubject();
    }
}
