/*
 * @(#)DefaultUserDetails.java 1.0 1 02/01/2018
 *
 * Copyright (c) 2017, Fatec-Jessen Vidal. All rights reserved.
 * Fatec-Jessen Vidal proprietary/confidential. Use is subject to license terms.
 */

package br.gov.sp.fatec.ecarreiras.authentication;

import java.util.Arrays;
import java.util.Collection;
import java.util.Optional;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import br.gov.sp.fatec.ecarreiras.domain.user.Login;
import br.gov.sp.fatec.ecarreiras.domain.user.UserRepository;
import lombok.AllArgsConstructor;

/**
 * A classe {@link DefaultUserDetails}
 *
 * @author Marcelo
 * @version 1.0 02/01/2018
 */
@Component
@AllArgsConstructor
public class DefaultUserDetails implements UserDetailsService {
	
	private final UserRepository repository;

	@Override
	public UserDetails loadUserByUsername(final String username) {
		final Optional <br.gov.sp.fatec.ecarreiras.domain.user.User> user = repository.findByUsername(username);
		
		if(!user.isPresent()) {
			throw new UsernameNotFoundException("No user present with username: " + username);
		}
		final Login login = user.get().getLogin();
		return new User(login.getUsername(), login.getPassword(), getRoles(user.get()));
	}
	
	private Collection<GrantedAuthority> getRoles(final br.gov.sp.fatec.ecarreiras.domain.user.User user) {
		return Arrays.asList(new DefaultGrantedAuthority(user.getProfile()));
	}
}