/*
 * @(#)JwtAuthenticationManager.java 1.0 1 28/01/2017
 *
 * Copyright (c) 2017, Fatec-Jessen Vidal. All rights reserved.
 * Fatec-Jessen Vidal proprietary/confidential. Use is subject to license terms.
 */

package br.gov.sp.fatec.ecarreiras.authentication.jwt;

import java.text.ParseException;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import com.nimbusds.jwt.JWT;
import com.nimbusds.jwt.JWTClaimsSet;
import com.nimbusds.jwt.JWTParser;

import br.gov.sp.fatec.ecarreiras.authentication.PreAuthenticatedAuthentication;
import br.gov.sp.fatec.ecarreiras.domain.user.User;
import br.gov.sp.fatec.ecarreiras.domain.user.UserRepository;

/**
 * A classe {@link JwtAuthenticationManager}
 *
 * @author Marcelo
 * @version 1.0 28/01/2017
 */
@Component
public class JwtAuthenticationManager implements AuthenticationManager {

	private static final Logger LOGGER = LoggerFactory.getLogger(JwtAuthenticationManager.class);
	
	private final UserRepository repository;
    private final List<JwtVerifier> verifiersList = new LinkedList<>();
    
    @Autowired
    public JwtAuthenticationManager(final UserRepository repository,
    		final List<JwtVerifier> verifiersList) {
        this.repository = repository;
        this.verifiersList.addAll(verifiersList);
    }

	@Override
	public Authentication authenticate(final Authentication auth) {
		final String token = String.valueOf(auth.getPrincipal()).substring(6).trim();
        final JWT jwt;
        final JWTClaimsSet claims;

        try {
            jwt = JWTParser.parse(token);
            claims = jwt.getJWTClaimsSet();
        } catch (final ParseException exception) {
            throw new JwtTokenException("The given JWT could not be parsed.");
        }

        for (final JwtVerifier verifier : verifiersList) {
            verifier.verify(jwt);
        }

        final String username;
		try {
			username = claims.getStringClaim("username");
		} catch (final ParseException e) {
			LOGGER.warn(e.getMessage());
			throw new JwtTokenException("The user from jwt not found.");
		}
        
        final Optional <User> user = repository.findByUsername(username);		
        return new PreAuthenticatedAuthentication(user.get());
	}
}