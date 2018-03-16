/*
 * @(#)DefaultAuthenticationSuccessHandler.java 1.0 1 03/09/2015
 *
 * Copyright (c) 2015, Fatec-Jessen Vidal. All rights reserved.
 * Fatec-Jessen Vidal proprietary/confidential. Use is subject to license terms.
 */

package br.gov.sp.fatec.ecarreiras.authentication;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

/**
 * A classe {@link DefaultAuthenticationSuccessHandler} e responsavel por
 * invocar os <i>listeners</i> do evento de autenticacao com sucesso do usuario
 * na aplicacao.
 *
 * @author Roberto Perillo
 * @version 1.0 03/09/2015
 */
@Component
public class DefaultAuthenticationSuccessHandler implements AuthenticationSuccessHandler {
	
	private final List<AuthenticationListener> authenticationListeners = new LinkedList<>();
	
	@Autowired
	public DefaultAuthenticationSuccessHandler(final List<AuthenticationListener> listeners) {
        this.authenticationListeners.addAll(listeners);
    }

	/**
     * Itera a lista de objetos <code>AuthenticationSuccessHandler</code>,
     * invocando o metodo <code>onAuthenticationSuccess</code> de cada objeto.
     * Cada objeto fara uma operacao de login com sucesso.
     *
     * @param request
     *            O objeto que representa a requisicao HTTP.
     * @param response
     *            O objeto que representa a resposta (onde o valor da
     *            <code>String</code> randômica sera colocada como cabecalho).
     * @param authentication
     *            O objeto que representa o usuario autenticado.
     */
	@Override
	public void onAuthenticationSuccess(final HttpServletRequest request, final HttpServletResponse response,
			final Authentication auth) throws IOException, ServletException {
		final AuthenticationEvent event = new AuthenticationEvent(response, auth);
        for (final AuthenticationListener listener : authenticationListeners) {
            listener.onAuthenticationSuccess(event);
        }
	}
}