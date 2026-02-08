package com.example.crudOprt.Service;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Configuration
public class CustomLoginSuccessHandler extends SimpleUrlAuthenticationSuccessHandler {
    protected void handle(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException {
        String url=determineUrl(authentication);
        if (response.isCommitted()){
            return;
        }
        RedirectStrategy redirectStrategy=new DefaultRedirectStrategy();
        redirectStrategy.sendRedirect(request, response, url);
    }

    protected String determineUrl(Authentication authentication){
        String url="/login?error=true";
        Collection<? extends GrantedAuthority> authorities=authentication.getAuthorities();
        List<String> roles=new ArrayList<>();
        for (GrantedAuthority a: authorities){
            roles.add(a.getAuthority());
        }
        if (roles.contains("ROLE_AGENT") || roles.contains("ROLE_ADMIN")){
            url="/home/admin";
        }else if (roles.contains("ROLE_CLIENT")){
            url="/home/client";
        }
        return url;
    }
}
