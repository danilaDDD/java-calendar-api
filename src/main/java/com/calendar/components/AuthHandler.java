package com.calendar.components;

import com.calendar.exceptions.UnauthorizedRequestException;
import com.calendar.models.AuthEntity;
import com.calendar.services.AuthService;
import io.jsonwebtoken.SignatureException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;
import java.util.Optional;
/*
get AuthEntity from request and throw UnauthorizedRequestException
 */
@Component
public class AuthHandler<T extends AuthEntity> {
    private JwtProvider jwtProvider;

    @Autowired
    public void setJwtProvider(JwtProvider jwtProvider) {
        this.jwtProvider = jwtProvider;
    }

    public T getAuthEntityOrThrowException(ServletRequest servletRequest,
                                           AuthService<T> authService, String secret){
        try {
            var request = (HttpServletRequest) servletRequest;
            String token = getBearerToken(request);

            return getEntity(token, secret, authService);
        }catch (SignatureException e){
            throw new UnauthorizedRequestException("Incorrect jwt token");
        }
    }

    private String getBearerToken(HttpServletRequest request){
        String token = request.getHeader("Authorization");
        String prefix = "Bearer ";
        int prefixIndex = token.indexOf(prefix);

        if(prefixIndex != 0)
            throw new UnauthorizedRequestException("bearer token not founded");

        return token.substring(prefix.length());
    }

    private T getEntity(String token, String secret, AuthService<T> authService){
        String subject = this.jwtProvider.getSubjectFromToken(token, secret);
        String[] subjectParts = subject.split(":");

        if(subjectParts.length != 2)
            throw new UnauthorizedRequestException("Incorrect jwt subject was given");

        Optional<T> entity = authService.findByLoginAndEncodedPassword(subjectParts[0], subjectParts[1]);
        if(entity.isPresent())
            return entity.get();

        else throw new UnauthorizedRequestException("Entity not found by login and password");
    }
}
