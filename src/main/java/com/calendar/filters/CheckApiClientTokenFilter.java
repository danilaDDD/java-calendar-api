package com.calendar.filters;

import com.calendar.components.AuthHandler;

import com.calendar.components.Secrets;
import com.calendar.exceptions.UnauthorizedRequestException;
import com.calendar.models.ApiClient;
import com.calendar.services.ApiClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import java.io.IOException;

/*
 * define in filters.FiltersConfig.filterByApiClientBean
 */
@Component
public class CheckApiClientTokenFilter implements Filter {
    private AuthHandler<ApiClient> authHandler;
    private ApiClientService apiClientService;
    private Secrets secrets;

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
            response = FiltersUtils.createRejectResponse(response);
        }

        chain.doFilter(request, response);

    }


}

