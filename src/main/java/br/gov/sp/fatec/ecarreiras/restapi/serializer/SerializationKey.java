/*
 * @(#)SerializationKey.java 1.0 1 30/09/2017
 *
 * Copyright (c) 2017, Fatec-Jessen Vidal. All rights reserved.
 * Fatec-Jessen Vidal proprietary/confidential. Use is subject to license terms.
 */

package br.gov.sp.fatec.ecarreiras.restapi.serializer;

/**
 * A classe {@link SerializationKey} sao armazenados
 * todos as constantes para serilizacao e deserializacao
 * dos objetos da aplicacao.
 *
 * @author Marcelo
 * @version 1.0 30/09/2017
 */
public enum SerializationKey {
	
	ID("id"),
	
	/* USER */
	NAME("name"),
	PROFILE("profile"),
	USERNAME("username"),
	PASS("password"),
	EMPTY_PASS(""),
	
	/*PAGE*/
	REMAINING_PAGES("remainingPages"),
	TOTAL_ELEMENTS("totalElements"),
	CONTENT("content");
	
	private final String key;
	
	private SerializationKey(final String key) {
		this.key = key;
	}
	
	@Override
	public String toString() {
		return key;
	}
}