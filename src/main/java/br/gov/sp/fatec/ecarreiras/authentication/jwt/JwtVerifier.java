/*
 * @(#)JwtVerifier.java 1.0 1 27/01/2017
 *
 * Copyright (c) 2017, Fatec-Jessen Vidal. All rights reserved.
 * Fatec-Jessen Vidal proprietary/confidential. Use is subject to license terms.
 */

package br.gov.sp.fatec.ecarreiras.authentication.jwt;

import com.nimbusds.jwt.JWT;

/**
 * A classe {@link JwtVerifier}
 *
 * @author Marcelo
 * @version 1.0 27/01/2017
 */
@FunctionalInterface
public interface JwtVerifier {
	
	void verify(final JWT jwt);
}