/*
 * @(#)AuthenticationListener.java 1.0 1 27/01/2017
 *
 * Copyright (c) 2017, Fatec-Jessen Vidal. All rights reserved.
 * Fatec-Jessen Vidal proprietary/confidential. Use is subject to license terms.
 */

package br.gov.sp.fatec.ecarreiras.authentication;

import java.io.IOException;

import javax.servlet.ServletException;

/**
 * A classe {@link AuthenticationListener}
 *
 * @author Marcelo
 * @version 1.0 27/01/2017
 */
@FunctionalInterface
public interface AuthenticationListener {
	
	/**
     * Executa uma a��o ap�s a autentica��o com sucesso na aplica��o.
     * 
     * @param event
     *            O evento de autentica��o.
     * @throws IOException
     *             Caso ocorra algum problema de I/O, como ao enviar um dado ao
     *             usu�rio por meio do <code>OutputStream</code> do objeto
     *             <code>HttpServletResponse</code>, presente no evento.
     * @throws ServletException
     *             Caso ocorra algum problema ao lidar com os objetos de
     *             requisi��o do usu�rio.
     */
    void onAuthenticationSuccess(final AuthenticationEvent event) throws IOException, ServletException;
}