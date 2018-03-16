/*
 * @(#)Login.java 1.0 1 02/11/2016
 *
 * Copyright (c) 2016, Fatec-Jessen Vidal. All rights reserved.
 * Fatec-Jessen Vidal proprietary/confidential. Use is subject to license terms.
 */

package br.gov.sp.fatec.ecarreiras.domain.user;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Table;

import lombok.Getter;

/**
 * A classe {@link Login} representa as credenciais de acesso a aplcacao.
 *
 * @author Marcelo
 * @version 1.0 02/11/2016
 */
@Getter
@Entity
@Table(name = "MAPSKILLS.LOGIN")
public class Login {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID")
	private Long id;

	@Column(name = "USERNAME", nullable = false, unique = true)
	private String username;
	
	@Lob
	@Column(name = "PASSWORD", nullable = false)
	private String password;
	
	@SuppressWarnings("unused")
	private Login() {
		this(null, null);
	}
	
	public Login(final String username, final String password) {
		this.username = username;
		this.password = password;
	}
	
	public void update(final Login newLogin) {
		username = newLogin.getUsername();
		password = newLogin.getPassword();
	}	
}