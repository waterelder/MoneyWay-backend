package xyz.trackbuck.security;

/**
 * Created by lex on 07.10.16.
 */

import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.GenericFilterBean;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class TokenAuthenticationFilter extends GenericFilterBean {
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        String method = ((HttpServletRequest) servletRequest).getMethod();
        if (!method.equals("OPTIONS")) {
            String token = extractToken(servletRequest);
            TokenAuthentication tokenAuthentication = new TokenAuthentication(token, servletRequest);
            SecurityContextHolder.getContext().setAuthentication(tokenAuthentication);
        }
        filterChain.doFilter(servletRequest, servletResponse);

    }

    private String extractToken(ServletRequest servletRequest) {
        return ((HttpServletRequest) servletRequest).getHeader("token");
    }

}
