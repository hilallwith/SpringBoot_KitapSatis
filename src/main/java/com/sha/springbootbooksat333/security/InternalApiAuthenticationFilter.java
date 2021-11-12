package com.sha.springbootbooksat333.security;

import com.sha.springbootbooksat333.service.UserPrincipal;
import com.sha.springbootbooksat333.util.SecurityUtils;
import org.springframework.web.filter.OncePerRequestFilter;
import lombok.extern.slf4j.Slf4j;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;


@Slf4j
public class InternalApiAuthenticationFilter extends OncePerRequestFilter { //Filtreler için kullanıyoruz



    private final String accessKey;

    public InternalApiAuthenticationFilter(String accessKey)
    {
        this.accessKey = accessKey;
    }

    @Override
    protected boolean shouldNotFilter(HttpServletRequest request) throws ServletException
    {
        return !request.getRequestURI().startsWith("/api/internal");
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException
    {
        try
        {
            String requestKey = SecurityUtils.extractAuthTokenFromRequest(request);

            if (requestKey == null || !requestKey.equals(accessKey))
            {
                log.warn("Internal key endpoint requested without/wrong key uri: {}", request.getRequestURI());
                throw new RuntimeException("UNAUTHORIZED");
            }

            UserPrincipal user = UserPrincipal.createSuperUser();
            UsernamePasswordAuthenticationToken authentication =
                    new UsernamePasswordAuthenticationToken(user, null, user.getAuthorities());
            SecurityContextHolder.getContext().setAuthentication(authentication);
        }
        catch (Exception ex)
        {
            log.error("Güvenlik bağlamında kullanıcı kimlik doğrulaması ayarlanamadı", ex);
        }

        filterChain.doFilter(request, response);
    }



}
