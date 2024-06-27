package com.calendar.filters;

import com.calendar.components.AuthHandler;
import com.calendar.components.Secrets;
import com.calendar.exceptions.UnauthorizedRequestException;
import com.calendar.models.User;
import com.calendar.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import java.io.IOException;

@Component
public class CheckUserTokenFilter implements Filter {
    private AuthHandler<User> authHandler;
    private UserService userService;
    private Secrets secrets;

    @Autowired
    public void setAuthHandler(AuthHandler<User> authHandler) {
        this.authHandler = authHandler;
    }

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    @Autowired
    public void setSecrets(Secrets secrets) {
        this.secrets = secrets;
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain filterChain) throws IOException, ServletException {
        try {
            User user = authHandler.getAuthEntityOrThrowException(request,
                    this.userService, this.secrets.getUserSecret());
            request.setAttribute("userId", user.getId());

        } catch (UnauthorizedRequestException e) {
            response = FiltersUtils.createRejectResponse(response);
        }

        filterChain.doFilter(request, response);
    }
}
