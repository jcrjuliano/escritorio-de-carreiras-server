/*
 * @(#)SerializersConfig.java 1.0 1 02/01/2018
 *
 * Copyright (c) 2018, Fatec-Jessen Vidal. All rights reserved.
 * Fatec-Jessen Vidal proprietary/confidential. Use is subject to license terms.
 */

package br.gov.sp.fatec.ecarreiras.config;

import java.util.EnumMap;
import java.util.HashMap;
import java.util.Map;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import br.gov.sp.fatec.ecarreiras.domain.user.ProfileType;
import br.gov.sp.fatec.ecarreiras.restapi.serializer.AbstractSerializer;

/**
 * A classe {@link SerializersConfig} possui uma configuracao de estrategia de
 * serializacao de perfil, onde cada perfil possui seu serializador.
 *
 * @author Marcelo
 * @version 1.0 02/01/2018
 */
@Configuration
public class SerializersConfig {
	
	/**
	 * Define um serializador para cada perfil de usuario da aplicacao.
	 */
	@Bean("userSerializerMap")
	@SuppressWarnings("rawtypes")
	public Map<ProfileType, AbstractSerializer> userSerializerMap(
			/*@Qualifier("studentSerializer") final AbstractSerializer<Student> studentSerializer,
			@Qualifier("mentorSerializer") final AbstractSerializer<Mentor> mentorSerializer*/) {
		
		final Map<ProfileType, AbstractSerializer> map = new EnumMap<>(ProfileType.class);
		/*map.put(ProfileType.MENTOR, mentorSerializer);
		map.put(ProfileType.STUDENT, studentSerializer);*/
		return map;
	}
	
	@Bean
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public Map<Class, AbstractSerializer> domainSerializerMap(
			/*@Qualifier("exemploSerializer") final AbstractSerializer exemploSerializer*/) {
		
		final Map map = new HashMap<>();
		//Classe da Entidade, Serializador da Entidade.
		//map.put(Exemplo.class, exemploSerializer);
		return map;		
	}
}