package com.springapp.authentication;

/**
 * Created by franschl on 06.04.15.
 */

        import javax.servlet.ServletException;
        import java.io.IOException;
        import javax.servlet.FilterChain;
        import javax.servlet.ServletRequest;
        import javax.servlet.ServletResponse;
        import javax.servlet.http.HttpServletRequest;

        import com.springapp.hibernate.UserDAO;
        import org.hibernate.Hibernate;
        import org.springframework.security.authentication.AuthenticationManager;
        import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
        import org.springframework.security.core.context.SecurityContext;
        import org.springframework.security.core.userdetails.User;
        import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
        import org.springframework.web.filter.GenericFilterBean;

public class AuthenticationTokenProcessingFilter extends GenericFilterBean {

    TokenProvider tokenProvider = new TokenProvider();

    AuthenticationManager authManager;
    SecurityContextProvider securityContextProvider;

    WebAuthenticationDetailsSource webAuthenticationDetailsSource = new WebAuthenticationDetailsSource();

    public AuthenticationTokenProcessingFilter(AuthenticationManager authManager) {
        this.authManager = authManager;
        this.securityContextProvider = new SecurityContextProvider();
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {

        String token = null;

        HttpServletRequest httpServletRequest = (HttpServletRequest) request;

        // get token from request
        if (httpServletRequest.getParameter("token") != null) {
            token = httpServletRequest.getParameter("token");
            // Because token containing "A+A==" is received as "A A=="
            token = token.replaceAll("\u0020", "+");
        } else if (httpServletRequest.getHeader("Authentication-token") != null) {
            token = httpServletRequest.getHeader("Authentication-token");
        }

        if (token != null) {
            if (tokenProvider.isTokenValid(token)) {
                UserDAO user = tokenProvider.getUserFromToken(token);
                Hibernate.initialize(user);
                authenticateUser(httpServletRequest, user);
            }
        }

        chain.doFilter(request, response);
    }

    private void authenticateUser(HttpServletRequest request, UserDAO user) {
        UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(user.getNick(), user.getPassword());
        authentication.setDetails(webAuthenticationDetailsSource.buildDetails(request));
        SecurityContext sc = securityContextProvider.getSecurityContext();
        sc.setAuthentication(authManager.authenticate(authentication));
    }
}