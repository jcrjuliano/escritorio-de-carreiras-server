/*
 * @(#)JwtSignatureVerifier.java 1.0 1 27/01/2017
 *
 * Copyright (c) 2017, Fatec-Jessen Vidal. All rights reserved.
 * Fatec-Jessen Vidal proprietary/confidential. Use is subject to license terms.
 */

package br.gov.sp.fatec.ecarreiras.authentication.jwt;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.nimbusds.jose.JOSEException;
import com.nimbusds.jose.JWSVerifier;
import com.nimbusds.jose.crypto.MACVerifier;
import com.nimbusds.jwt.JWT;
import com.nimbusds.jwt.SignedJWT;

/**
 * A classe {@link JwtSignatureVerifier}
 *
 * @author Marcelo
 * @version 1.0 27/01/2017
 */
public class JwtSignatureVerifier implements JwtVerifier {

	private static final Logger LOGGER = LoggerFactory.getLogger(JwtSignatureVerifier.class	);
	private final JWSVerifier verifier;
	
	public JwtSignatureVerifier(final String secret) throws JOSEException {
        this.verifier = new MACVerifier(secret);
    }

	/** {@inheritDoc} */
	@Override
	public void verify(final JWT jwt) {
		final SignedJWT signedJwt = (SignedJWT) jwt;
        try {
            if (!signedJwt.verify(verifier)) {
                throw new JwtTokenException("Invalid signature.");
            }
        } catch (final JOSEException exception) {
        	LOGGER.warn(exception.getMessage(), exception);
            throw new JwtTokenException("The JWT signature could not be verified.");
        }        
	}
}