/*
 * Copyright (C) DATECH, Inc - All Rights Reserved
 *  Unauthorized copying of this file, via any medium is strictly prohibited
 *  Proprietary and confidential
 *  Written by Nguyen Trung Hieu, 2021
 */

package vn.com.datech.uic.portal.common.filter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import vn.com.datech.uic.portal.common.response.vo.ResponseMessage;
import vn.com.datech.uic.portal.common.utils.JwtTokenUtils;
import vn.com.datech.uic.portal.service.SecurityService;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * JWT request filter.
 */
@Component
public class JwtRequestFilter extends OncePerRequestFilter {
    /**
     * Security service.
     */
    private SecurityService securityService;
    /**
     * JWT token utility.
     */
    private JwtTokenUtils jwtTokenUtils;

    /**
     * Do filter.
     *
     * @param request  HttpServletRequest
     * @param response HttpServletResponse
     * @param chain    FilterChain
     * @throws ServletException when error
     * @throws IOException      when error
     */
    @Override
    protected void doFilterInternal(final HttpServletRequest request,
                                    final HttpServletResponse response,
                                    final FilterChain chain)
            throws ServletException, IOException {
        String requestTokenHeader = request.getHeader("Authorization");
        String username = null;
        String jwtToken = null;
        ResponseMessage responseMessage = new ResponseMessage();
        responseMessage.setSuccess(false);
        String requestMethod = request.getMethod();
        if ("option".equalsIgnoreCase(requestMethod)) {
            chain.doFilter(request, response);
        } else {
            // JWT Token is in the form "Bearer token". Remove Bearer word
            // and get
            // only the Token
            boolean validateToken = false;
            if (requestTokenHeader != null
                    && requestTokenHeader.startsWith("Bearer ")) {
                final int subStringIndex = 7;
                jwtToken = requestTokenHeader.substring(subStringIndex);
                try {
                    username = jwtTokenUtils.getUsernameFromToken(jwtToken);
                    validateToken =
                            jwtTokenUtils.validateToken(jwtToken, username);
                } catch (Exception e) {
                    responseMessage.setSuccess(false);
                }
            } else {
                responseMessage.setSuccess(false);
            }
            // Once we get the token validate it.
            if (username != null && validateToken) {
                UserDetails userDetails =
                        securityService.loadUserByUsername(username);
                // if token is valid configure Spring Security to manually set
                // authentication
                if (jwtTokenUtils.validateToken(jwtToken,
                        userDetails.getUsername())) {
                    UsernamePasswordAuthenticationToken
                            usernamePasswordAuthenticationToken =
                            new UsernamePasswordAuthenticationToken(
                                    userDetails, null,
                                    userDetails.getAuthorities());
                    usernamePasswordAuthenticationToken
                            .setDetails(new WebAuthenticationDetailsSource()
                                    .buildDetails(request));
                    SecurityContextHolder
                            .getContext()
                            .setAuthentication(usernamePasswordAuthenticationToken);
                    responseMessage.setSuccess(true);
                }
            }
            if (!responseMessage.isSuccess()) {
                response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            } else {
                chain.doFilter(request, response);
            }
        }
    }

    /**
     * Not filter.
     *
     * @param request HttpServletRequest
     * @return boolean
     */
    @Override
    protected boolean shouldNotFilter(final HttpServletRequest request) {
        return true;
    }

    /**
     * SecurityService.
     *
     * @param pSecurityService SecurityService
     */
    @Autowired
    public void setSecurityService(final SecurityService pSecurityService) {
        securityService = pSecurityService;
    }

    /**
     * JwtTokenUtils.
     *
     * @param pJwtTokenUtils JwtTokenUtils
     */
    @Autowired
    public void setJwtTokenUtils(final JwtTokenUtils pJwtTokenUtils) {
        jwtTokenUtils = pJwtTokenUtils;
    }

    private boolean isSwaggerRequest(final HttpServletRequest request) {
        String requestURI = request.getRequestURI();
        return requestURI.matches(".*/v2/api-docs.*")
                || requestURI.matches(".*/swagger-ui.html")
                || requestURI.matches(".*/swagger-ui/.*")
                || requestURI.matches(".*/swagger-resources.*")
                || requestURI.matches(".*/webjars/.*")
                || requestURI.matches(".*csrf.*");
    }
}
