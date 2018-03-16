/*
 * @(#)UserSerializer.java 1.0 1 02/01/2018
 *
 * Copyright (c) 2018, Fatec-Jessen Vidal. All rights reserved.
 * Fatec-Jessen Vidal proprietary/confidential. Use is subject to license terms.
 */

package br.gov.sp.fatec.ecarreiras.restapi.serializer;

import java.io.IOException;
import java.util.Map;

import org.springframework.stereotype.Component;

import br.gov.sp.fatec.ecarreiras.domain.user.ProfileType;
import br.gov.sp.fatec.ecarreiras.domain.user.User;
import lombok.AllArgsConstructor;

/**
 * A classe {@link UserSerializer}
 *
 * @author Marcelo
 * @version 1.0 02/01/2018
 */
@Component
@AllArgsConstructor
public class UserSerializer extends AbstractSerializer<User> {
	
	/**
	 * Mapa de definicao de serializadores para os perfis.
	 * @see <code>SerializersConfig</code>.
	 */
	@SuppressWarnings("rawtypes")
	private final Map<ProfileType, AbstractSerializer> userSerializerMap;

	@Override
	public void serialize(final User user, final Enum<?> arg1, final JsonWriter writer) throws IOException {
		@SuppressWarnings("unchecked")
		final AbstractSerializer<User> serializer = userSerializerMap.get(user.getProfile());
		serializer.serialize(user, writer);
	}
}