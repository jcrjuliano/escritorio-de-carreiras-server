/*
 * @(#)DefaultAuthenticationProvider.java 1.0 1 02/01/2018
 *
 * Copyright (c) 2017, Fatec-Jessen Vidal. All rights reserved.
 * Fatec-Jessen Vidal proprietary/confidential. Use is subject to license terms.
 */

package br.gov.sp.fatec.ecarreiras.authentication;

import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Component;

import br.gov.sp.fatec.ecarreiras.application.UserApplicationServices;
import br.gov.sp.fatec.ecarreiras.domain.user.User;
import lombok.AllArgsConstructor;

/**
 * A classe {@link DefaultAuthenticationProvider} responsavel
 * por realizar as autenticacoes dos usuarios na aplicacao.
 *
 * @author Marcelo
 * @version 1.0 02/01/2018
 */
@Component
@AllArgsConstructor
public class DefaultAuthenticationProvider implements AuthenticationProvider {
	
	private final UserApplicationServices userService;

	/** {@inheritDoc} */
	@Override
	public Authentication authenticate(final Authentication authentication) throws AuthenticationException {
		final User user = userService.findUserByUsername(authentication.getName());		
		userService.authenticate(user, authentication.getCredentials().toString());	
		return new PreAuthenticatedAuthentication(user);
	}

	/** {@inheritDoc} */
	@Override
	public boolean supports(final Class<?> authentication) {
		return true;
	}
}