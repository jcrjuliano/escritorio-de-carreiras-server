/*
 * @(#)PreAuthenticatedUserFilter.java 1.0 1 26/10/2016
 *
 * Copyright (c) 2016, Fatec-Jessen Vidal. All rights reserved.
 * Fatec-Jessen Vidal proprietary/confidential. Use is subject to license terms.
 */

package br.gov.sp.fatec.ecarreiras.authentication;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.preauth.AbstractPreAuthenticatedProcessingFilter;

/**
 * A classe {@link PreAuthenticatedUserFilter}
 *
 * @author Marcelo
 * @version 1.0 26/10/2016
 */
public class PreAuthenticatedUserFilter extends AbstractPreAuthenticatedProcessingFilter {
	
	private static final String AUTHORIZATION = "Authorization";
	private static final Logger LOGGER = LoggerFactory.getLogger(PreAuthenticatedUserFilter.class);

	/** {@inheritDoc} */
	@Override
	protected String getPreAuthenticatedPrincipal(final HttpServletRequest request) {
		final String authorizationCookie = getAuthorization(request);
		if (authorizationCookie != null) {
            return authorizationCookie.contains(";") ? authorizationCookie.substring(7, authorizationCookie.indexOf(';')) : authorizationCookie.substring(7);
        }
		return request.getHeader(AUTHORIZATION) != null ? request.getHeader(AUTHORIZATION) : request.getParameter(AUTHORIZATION);
	}
	
	/** {@inheritDoc} */
	@Override
	protected Object getPreAuthenticatedCredentials(final HttpServletRequest request) {
		return null;
	}
	
	/** {@inheritDoc} */
    @Override
    public void doFilter(final ServletRequest request, final ServletResponse response,
    		final FilterChain chain) throws IOException, ServletException {
        try {
            super.doFilter(request, response, chain);
        } finally {
        	SecurityContextHolder.clearContext();
            final HttpSession session = ((HttpServletRequest) request).getSession(false);
            if (session != null) {
                session.removeAttribute("SPRING_SECURITY_CONTEXT");
            }
        }
    }
    
    private String getAuthorization(final HttpServletRequest request) {
        if (request.getCookies() != null) {
            for (final Cookie cookie : request.getCookies()) {
                if (AUTHORIZATION.equals(cookie.getName())) {
                    try {
                        return URLDecoder.decode(cookie.getValue(), "UTF-8");
                    } catch (final UnsupportedEncodingException exception) {
                    	LOGGER.info("Exception: ", exception);
                        throw new UnauthorizedAuthenticationException("Error decoding JWT token");
                    }
                }
            }
        }
        return null;
    }
}