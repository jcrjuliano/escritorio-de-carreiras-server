/*
 * @(#)CareerOfficeApplication.java 1.0 1 02/01/2018
 *
 * Copyright (c) 2018, Fatec-Jessen Vidal. All rights reserved.
 * Fatec-Jessen Vidal proprietary/confidential. Use is subject to license terms.
 */

package br.gov.sp.fatec.ecarreiras;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * A classe {@link CareerOfficeApplication} contem o metodo
 * main reponsavel por iniciar a aplicacao spring boot.
 *
 * @author Marcelo
 * @version 1.0 02/01/2018
 */
@SpringBootApplication
public class CareerOfficeApplication {
	
	public static void main(final String[] args) {
		SpringApplication.run(CareerOfficeApplication.class, args);
	}
}