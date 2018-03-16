/*
 * @(#)UserApplicationServices.java 1.0 1 02/01/2018
 *
 * Copyright (c) 2017, Fatec-Jessen Vidal. All rights reserved.
 * Fatec-Jessen Vidal proprietary/confidential. Use is subject to license terms.
 */

package br.gov.sp.fatec.ecarreiras.application;

import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.util.ObjectUtils;

import br.gov.sp.fatec.ecarreiras.domain.user.User;
import br.gov.sp.fatec.ecarreiras.domain.user.UserNotFoundException;
import br.gov.sp.fatec.ecarreiras.domain.user.UserRepository;
import lombok.AllArgsConstructor;

/**
 * A classe {@link UserApplicationServices}
 *
 * @author Marcelo
 * @version 1.0 02/01/2018
 */
@ApplicationServices
@AllArgsConstructor
public class UserApplicationServices {
	
	private final Logger logger = LoggerFactory.getLogger(getClass());
	
	private final UserRepository repository;
	private final PasswordEncoder encoder;

	public User findUserByUsername(final String username) {
		final Optional <User> user = repository.findByUsername(username);
		if (!user.isPresent()) {
			throw new UserNotFoundException(username);
		}
		return user.get();
	}
	
	/**
	 * Valida o usuario, verificando se ele nao eh nulo ou possua as credenciais invalidas
	 * 
	 * @param user Usuario a ser validado
	 * @param password Senha a ser validade
	 */
	public void authenticate(final User user, final String password) {
		if (ObjectUtils.isEmpty(user) || !encoder.matches(password, user.getPassword())) {
			logger.warn("username/password invalid");
			throw new BadCredentialsException("username/password invalid");
		}
	}
}