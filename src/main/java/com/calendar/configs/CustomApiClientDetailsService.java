package com.calendar.configs;

import com.calendar.models.ApiClient;
import com.calendar.models.User;
import com.calendar.services.ApiClientService;
import com.calendar.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

@Component
public class CustomApiClientDetailsService implements UserDetailsService {
    private ApiClientService apiClientService;

    @Autowired
    public void setApiClientService(ApiClientService service){
        apiClientService = service;
    }

    @Override
    public CustomApiClientDetails loadUserByUsername(String login) throws UsernameNotFoundException {
        ApiClient client = apiClientService.findByLogin(login);
        return CustomApiClientDetails.fromUserEntityToCustomUserDetails(client);
    }
}
