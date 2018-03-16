/*
 * @(#)PreAuthenticatedAuthentication.java 1.0 1 27/01/2017
 *
 * Copyright (c) 2017, Fatec-Jessen Vidal. All rights reserved.
 * Fatec-Jessen Vidal proprietary/confidential. Use is subject to license terms.
 */

package br.gov.sp.fatec.ecarreiras.authentication;

import java.util.Arrays;
import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.web.authentication.preauth.PreAuthenticatedAuthenticationToken;

import br.gov.sp.fatec.ecarreiras.domain.user.User;

/**
 * 
 * A classe {@link PreAuthenticatedAuthentication}
 *
 * @author Marcelo
 * @version 1.0 27/01/2017
 */
public class PreAuthenticatedAuthentication extends PreAuthenticatedAuthenticationToken {
	
	private static final long serialVersionUID = 1L;

    public PreAuthenticatedAuthentication(final User user) {
        super(user, new DefaultGrantedAuthority(user.getProfile()), buildAuthorities(user));
        setAuthenticated(true);
    }

    @Override
    public User getPrincipal() {
        return (User) super.getPrincipal();
    }

    public boolean isAdmin() {
        final Collection<GrantedAuthority> authorities = getAuthorities();     
        return authorities.stream().anyMatch(authoritie -> ((DefaultGrantedAuthority) authoritie).isAdmin());
    }
    
    /**
     * Metodo responsavel por criar a autorizacao que o usuario logado possua.
     * 
     * @param user um usuario da aplicacao.
     * @return uma colecao de autorizacoes ao qual possui acesso.
     */
    private static Collection<GrantedAuthority> buildAuthorities(final User user) {
		return Arrays.asList(new DefaultGrantedAuthority(user.getProfile()));
	}
}