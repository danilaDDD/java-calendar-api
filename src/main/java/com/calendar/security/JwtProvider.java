package com.calendar.security;

import com.calendar.models.User;
import com.calendar.services.UserService;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;

import static io.jsonwebtoken.lang.Strings.hasText;
import static org.springframework.http.HttpHeaders.AUTHORIZATION;

@Component
@Log
public class JwtProvider {

    @Value("$(jwt.secret)")
    private String jwtSecret;

    @Value("$(jwt.user_secret)")
    private String userSecret;

    @Value("#{${expiration.days}}")
    private int expirationDays;

    public int getExpirationDays() {
        return this.expirationDays;
    }

    private UserService userService;

    @Autowired
    public void setUserService(UserService userService){
        this.userService = userService;
    }

    public String generateClientToken(String login) {
        return generateTokenByLogin(login, jwtSecret);
    }

    public String generateUserToken(String login){
        return generateTokenByLogin(login, userSecret);
    }

    public String generateTokenByLogin(String login, String secret){
        Date date = Date.from(LocalDate.now().plusDays(getExpirationDays()).atStartOfDay().atZone(ZoneId.systemDefault()).toInstant());
        return Jwts.builder()
                .setSubject(login)
                .setExpiration(date)
                .signWith(SignatureAlgorithm.HS512, secret)
                .compact();
    }

    public boolean validateToken(String token) {
        try {
            Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(token);
            return true;
        } catch (Exception e) {
            log.severe("invalid token");
        }
        return false;
    }

    public String getClientLoginFromToken(String token) {
        return getLoginFromToken(token, jwtSecret);
    }

    public String getUserLoginFromToken(String token){
        return getLoginFromToken(token, userSecret);
    }

    public String getLoginFromToken(String token, String secret){
        Claims claims = Jwts.parser().setSigningKey(secret).parseClaimsJws(token).getBody();
        return claims.getSubject();
    }

    public User getUserFromRequest(HttpServletRequest request){
        try {
            String token = request.getHeader("user_token");
            String userLogin = getUserLoginFromToken(token);
            return this.userService.findUserByLogin(userLogin);

        }catch (Exception e){
            return null;
        }
    }

    public String getClientTokenFromRequest(HttpServletRequest request) {
        String bearer = request.getHeader(AUTHORIZATION);
        if (hasText(bearer) && bearer.startsWith("Bearer ")) {
            return bearer.substring(7);
        }
        return null;
    }
}
