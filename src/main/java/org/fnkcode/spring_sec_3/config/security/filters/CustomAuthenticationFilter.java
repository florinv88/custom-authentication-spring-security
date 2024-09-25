package org.fnkcode.spring_sec_3.config.security.filters;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.fnkcode.spring_sec_3.config.security.authentication.CustomAuthentication;
import org.fnkcode.spring_sec_3.config.security.managers.CustomAuthenticationManager;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
@RequiredArgsConstructor
public class CustomAuthenticationFilter extends OncePerRequestFilter {

    private final CustomAuthenticationManager customAuthenticationManager;


    @Override
    protected void doFilterInternal(@NonNull HttpServletRequest request,
                                    @NonNull HttpServletResponse response,
                                    @NonNull FilterChain filterChain) throws ServletException, IOException {

        // 1. create an authentication object which is not yet authenticated
        // 2. delegate the authentication object to the manager
        // 3. get back the authentication from manager
        // 4. if the object is authenticated then send request to the next filter in the chain

        String key = String.valueOf(request.getHeader("KEY"));
        CustomAuthentication authentication = new CustomAuthentication(false,key);

        var a = customAuthenticationManager.authenticate(authentication);

        if (a.isAuthenticated()) {
            SecurityContextHolder.getContext().setAuthentication(a);

            //Move to the next security filter if it's all good
            filterChain.doFilter(request,response);
        }

    }
}
