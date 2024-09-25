package org.fnkcode.spring_sec_3.config.security.managers;

import lombok.RequiredArgsConstructor;
import org.fnkcode.spring_sec_3.config.security.providers.CustomAuthenticationProvider;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CustomAuthenticationManager implements AuthenticationManager {

    private final CustomAuthenticationProvider authenticationProvider;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {

        //iterate all the providers

        if(authenticationProvider.supports(authentication.getClass())){
            return authenticationProvider.authenticate(authentication);
        }

        throw new BadCredentialsException("Wrong key");
    }
}
