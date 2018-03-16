/*
 * @(#)UserRepository.java 1.0 1 01/11/2016
 *
 * Copyright (c) 2016, Fatec-Jessen Vidal. All rights reserved.
 * Fatec-Jessen Vidal proprietary/confidential. Use is subject to license terms.
 */

package br.gov.sp.fatec.ecarreiras.domain.user;

import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

/**
 * A classe {@link UserRepository} e responsavel por
 * que seja possivel realizar as buscas dos usuarios
 * da aplicacao, sem perfil especifico.
 *
 * @author Marcelo
 * @version 1.0 01/11/2016
 */
public interface UserRepository extends CrudRepository<User, Long> {

	User findByLogin(final Login login);
	
	/**
	 * Metodo que recupera um usuario da aplicao.
	 * 
	 * @param username
	 * 		E-mail do usuario.
	 * @return
	 * 		Usuario encontrado.
	 */
	@Query("SELECT user FROM #{#entityName} user WHERE user.login.username = ?1")
	Optional <User> findByUsername(final String username);
	
	@Query("SELECT user FROM #{#entityName} user WHERE user.login.username = ?#{principal.login.username}")
	User getLoggedUser();
}