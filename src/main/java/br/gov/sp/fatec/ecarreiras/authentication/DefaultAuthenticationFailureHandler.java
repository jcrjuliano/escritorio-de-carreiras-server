/*
 * @(#)DefaultAuthenticationFailureHandler.java 1.0 1 27/01/2017
 *
 * Copyright (c) 2017, Fatec-Jessen Vidal. All rights reserved.
 * Fatec-Jessen Vidal proprietary/confidential. Use is subject to license terms.
 */

package br.gov.sp.fatec.ecarreiras.authentication;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.stereotype.Component;

/**
 * A classe {@link DefaultAuthenticationFailureHandler}
 *
 * @author Marcelo
 * @version 1.0 27/01/2017
 */
@Component
public class DefaultAuthenticationFailureHandler implements AuthenticationFailureHandler {
	
	private static final String JSON = "{\"errorCode\": %d, \"message\": \"%s\"}";

	/** {@inheritDoc} */
	@Override
	public void onAuthenticationFailure(final HttpServletRequest request, final HttpServletResponse response,
			final AuthenticationException exception) throws IOException, ServletException {
		response.setContentType(MediaType.APPLICATION_JSON_VALUE);
        response.setStatus(HttpStatus.UNAUTHORIZED.value());
        response.getOutputStream().print(String.format(JSON, HttpStatus.UNAUTHORIZED.value(), exception.getMessage()));
	}
}