/*
 * @(#)UserController.java 1.0 1 03/01/2017
 *
 * Copyright (c) 2017, Fatec-Jessen Vidal. All rights reserved.
 * Fatec-Jessen Vidal proprietary/confidential. Use is subject to license terms.
 */

package br.gov.sp.fatec.ecarreiras.restapi;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.gov.sp.fatec.ecarreiras.application.UserApplicationServices;
import br.gov.sp.fatec.ecarreiras.domain.user.User;
import br.gov.sp.fatec.ecarreiras.restapi.wrapper.SingleWrapper;
import lombok.AllArgsConstructor;

/**
 * A classe {@link UserController} eh responsavel por conter os
 * endpoints (uri) para os servicos do aggregate User.
 *
 * @author Marcelo
 * @version 1.0 03/01/2017
 */
@RestController
@AllArgsConstructor
public class UserController {
	
	private final UserApplicationServices userService;
	
	/**
	 * Endpoint que retorna um usuario a partir de seu email.
	 */
	@GetMapping("/user")
	public SingleWrapper<User> getUser(@RequestParam("username") final String username) {
		final User user = userService.findUserByUsername(username);
		return new SingleWrapper<User>(user);
	}
}