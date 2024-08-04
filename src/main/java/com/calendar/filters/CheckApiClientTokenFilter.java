package com.calendar.filters;

import com.calendar.components.AuthHandler;

import com.calendar.components.Secrets;
import com.calendar.exceptions.UnauthorizedRequestException;
import com.calendar.models.ApiClient;
import com.calendar.services.ApiClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerExceptionResolver;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/*
 * define in filters.FiltersConfig.filterByApiClientBean
 */
@Component
public class CheckApiClientTokenFilter implements Filter {
    private AuthHandler<ApiClient> authHandler;
    private ApiClientService apiClientService;
    private Secrets secrets;

    private HandlerExceptionResolver handlerExceptionResolver;

    @Autowired
    public void setHandlerExceptionResolver(HandlerExceptionResolver handlerExceptionResolver) {
        this.handlerExceptionResolver = handlerExceptionResolver;
    }

    @Autowired
    public void setAuthHandler(AuthHandler<ApiClient> authHandler) {
        this.authHandler = authHandler;
    }

    @Autowired
    public void setApiClientService(ApiClientService apiClientService) {
        this.apiClientService = apiClientService;
    }

    @Autowired
    public void setSecrets(Secrets secrets) {
        this.secrets = secrets;
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {

        try {
            authHandler.getAuthEntityOrThrowException(request,
                    this.apiClientService, this.secrets.getApiClientSecret());

        } catch (UnauthorizedRequestException e) {
            this.handlerExceptionResolver
                    .resolveException((HttpServletRequest) request, (HttpServletResponse) response, null, e);
        }

        chain.doFilter(request, response);

    }


}

