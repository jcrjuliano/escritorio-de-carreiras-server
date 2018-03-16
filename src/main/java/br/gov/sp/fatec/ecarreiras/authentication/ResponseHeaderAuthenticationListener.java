/*
 * @(#)ResponseHeaderAuthenticationListener.java 1.0 1 02/01/2018
 *
 * Copyright (c) 2017, Fatec-Jessen Vidal. All rights reserved.
 * Fatec-Jessen Vidal proprietary/confidential. Use is subject to license terms.
 */

package br.gov.sp.fatec.ecarreiras.authentication;

import java.io.IOException;
import java.net.URLEncoder;
import java.sql.Date;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.stereotype.Component;

import com.nimbusds.jose.JOSEException;
import com.nimbusds.jose.JWSAlgorithm;
import com.nimbusds.jose.JWSHeader;
import com.nimbusds.jose.JWSSigner;
import com.nimbusds.jose.crypto.MACSigner;
import com.nimbusds.jwt.JWTClaimsSet;
import com.nimbusds.jwt.SignedJWT;

/**
 * A classe {@link ResponseHeaderAuthenticationListener} eh
 * reponsavel por gerar o token JWT, e gravar no header da
 * resposta o token que dara ao cliente autorizacao aos
 * servicos fornecidos pela aplicacao.
 *
 * @author Marcelo
 * @version 1.0 02/01/2018
 */
@Component
public class ResponseHeaderAuthenticationListener implements AuthenticationListener {
	
	private static Logger logger = LoggerFactory.getLogger(ResponseHeaderAuthenticationListener.class);
	private static final long FIVE_HOURS_IN_MILLISECONDS = 60000L * 300L;
    private final JWSSigner signer;
    
    @Autowired
    public ResponseHeaderAuthenticationListener(@Value("${jwt.secret}") final String secret) throws JOSEException {
        this.signer = new MACSigner(secret);
    }

	@Override
	public void onAuthenticationSuccess(final AuthenticationEvent event) throws IOException, ServletException {
		final long now = System.currentTimeMillis();
		final JWTClaimsSet claimsSet = new JWTClaimsSet.Builder()
				.subject(event.getUsername())
				.claim("username", event.getUserDomain().getUsername())
				.claim("profile", event.getUserDomain().getProfile())
				.issueTime(new Date(now))
				.issuer("https://ecarreiras.fatec.sp.gov.br")
				.expirationTime(new Date(now + FIVE_HOURS_IN_MILLISECONDS))
				.notBeforeTime(new Date(now))
				.build();

        final SignedJWT signedJWT = new SignedJWT(new JWSHeader(JWSAlgorithm.HS256), claimsSet);

        try {
            signedJWT.sign(signer);
        } catch (final JOSEException exception) {
        	logger.warn(exception.getMessage(), exception);
            throw new AuthenticationServiceException("The given JWT could not be signed.");
        }

        final HttpServletResponse resp = event.getResponse();
        final String bearer = String.format("Bearer %s", signedJWT.serialize());
        resp.setHeader("Authorization", bearer);
        final Cookie cookie = new Cookie("Authorization", URLEncoder.encode(bearer, "UTF-8"));
        cookie.setHttpOnly(true);
        resp.addCookie(cookie);
	}
}