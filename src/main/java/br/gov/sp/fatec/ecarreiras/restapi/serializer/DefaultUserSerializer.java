/*
 * @(#)DefaultUserSerializer.java 1.0 1 02/01/2018
 *
 * Copyright (c) 2018, Fatec-Jessen Vidal. All rights reserved.
 * Fatec-Jessen Vidal proprietary/confidential. Use is subject to license terms.
 */

package br.gov.sp.fatec.ecarreiras.restapi.serializer;

import java.io.IOException;

import br.gov.sp.fatec.ecarreiras.domain.user.User;

/**
 * A classe {@link DefaultUserSerializer} e responsavel
 * por serializar os atributos em comum a todos usuarios.
 *
 * @author Marcelo
 * @version 1.0 02/01/2018
 */
public class DefaultUserSerializer<T extends User> extends AbstractSerializer<T> {
	
	@Override
	public void serialize(final T user, final Enum<?> arg1, final JsonWriter writer) throws IOException {
		writer.writeNumberField(SerializationKey.ID, user.getId());
		writer.writeStringField(SerializationKey.NAME, user.getName());
		writer.writeStringField(SerializationKey.PROFILE, user.getProfile());
		writer.writeStringField(SerializationKey.USERNAME, user.getUsername());
		writer.writeStringField(SerializationKey.PASS, SerializationKey.EMPTY_PASS);		
	}
}