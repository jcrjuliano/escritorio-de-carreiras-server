/*
 * @(#)CareerOfficeException.java 1.0 1 02/01/2018
 *
 * Copyright (c) 2018, Fatec-Jessen Vidal. All rights reserved.
 * Fatec-Jessen Vidal proprietary/confidential. Use is subject to license terms.
 */

package br.gov.sp.fatec.ecarreiras.domain;

/**
 * A classe {@link CareerOfficeException} representa excecao
 * controlada pela aplicacao.
 *
 * @author Marcelo
 * @version 1.0 02/01/2018
 */
public abstract class CareerOfficeException extends RuntimeException {
	
	private static final long serialVersionUID = 1L;
	
	public CareerOfficeException(final String message, final Throwable cause) {
		super(message, cause);
	}
	
	public CareerOfficeException(final String message) {
		super(message);
	}
}