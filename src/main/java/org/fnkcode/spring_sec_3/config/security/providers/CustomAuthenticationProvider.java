package org.fnkcode.spring_sec_3.config.security.providers;

import org.fnkcode.spring_sec_3.config.security.authentication.CustomAuthentication;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Component;

@Component
public class CustomAuthenticationProvider implements AuthenticationProvider {

    @Value("${secret.key}")
    private String key;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {

        CustomAuthentication ca = (CustomAuthentication) authentication;
        String headerKey = ca.getKey();
        if(key.equals(headerKey)) {
            return new CustomAuthentication(true,null);
        }
        throw new BadCredentialsException("Wrong key");
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return CustomAuthentication.class.equals(authentication);
    }
}
