package com.sha.springbootbooksat333.util;

import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.util.StringUtils;

import javax.servlet.http.HttpServletRequest;

public class SecurityUtils {

    public static final String ROLE_PREFIX = "ROLE_";    //Role değeri ROLE_ eki ile başlatıyoryz
    public static final String AUTH_HEADER = "authorization";
    public static final String AUTH_TOKEN_TYPE = "Bearer";
    public static final String AUTH_TOKEN_PREFIX = AUTH_TOKEN_TYPE + " ";


    public static SimpleGrantedAuthority convertToAuthority(String role)
    {
        String formattedRole = role.startsWith(ROLE_PREFIX) ? role : ROLE_PREFIX + role; //BaşınaROLe_ eki veriyoruz

        return new SimpleGrantedAuthority(formattedRole);
    }


    public static String extractAuthTokenFromRequest(HttpServletRequest request)
    {
        String bearerToken = request.getHeader(AUTH_HEADER);

        if (StringUtils.hasLength(bearerToken) && bearerToken.startsWith(AUTH_TOKEN_PREFIX))
        {
            return bearerToken.substring(7);
        }
        return null;
    }


















}
