/*
 * @(#)CorsFilterConfig.java 1.0 1 02/01/2018
 *
 * Copyright (c) 2017, Fatec-Jessen Vidal. All rights reserved.
 * Fatec-Jessen Vidal proprietary/confidential. Use is subject to license terms.
 */

package br.gov.sp.fatec.ecarreiras.config;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.web.filter.OncePerRequestFilter;

/**
 * 
 * A classe {@link CorsFilter} representa as
 * configuracoes de <i>CORS</i> da aplicacao.
 *
 * @author Marcelo
 * @version 1.0 08/01/2017
 */
public class CorsFilterConfig extends OncePerRequestFilter {

	@Override
	protected void doFilterInternal(final HttpServletRequest request, final HttpServletResponse response,
			final FilterChain chain) throws ServletException, IOException {		
		response.setHeader(HttpHeaders.ACCESS_CONTROL_ALLOW_ORIGIN, "*");
        response.setHeader(HttpHeaders.ACCESS_CONTROL_ALLOW_METHODS, "GET, POST, PUT, OPTIONS, DELETE");
        response.setHeader(HttpHeaders.ACCESS_CONTROL_MAX_AGE, "3600");
        response.setHeader(HttpHeaders.ACCESS_CONTROL_ALLOW_HEADERS, "Origin, X-Requested-With, Content-Type, Accept, Authorization");
        response.setHeader(HttpHeaders.ACCESS_CONTROL_EXPOSE_HEADERS, "Authorization");
        
        if (HttpMethod.OPTIONS.toString().equals(request.getMethod())) {
            return;
        }        
        chain.doFilter(request, response);
	}
}