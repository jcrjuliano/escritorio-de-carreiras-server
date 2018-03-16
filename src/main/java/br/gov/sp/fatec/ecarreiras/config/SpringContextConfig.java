/*
 * @(#)SpringContextConfig.java 1.0 1 02/01/2018
 *
 * Copyright (c) 2017, Fatec-Jessen Vidal. All rights reserved.
 * Fatec-Jessen Vidal proprietary/confidential. Use is subject to license terms.
 */

package br.gov.sp.fatec.ecarreiras.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;

/**
 * 
 * A classe {@link SpringContextConfig} representa as
 * configuracoes necessarias para o uso da aplicacao em producao.
 *
 * @author Marcelo
 * @version 1.0 01/11/2016
 */
@Configuration
@PropertySource({"classpath:application.properties", "classpath:authentication.properties"})
public class SpringContextConfig {
	
	@Bean
    public static PropertySourcesPlaceholderConfigurer propertySourcesPlaceholderConfigurer() {
        return new PropertySourcesPlaceholderConfigurer();
    }
}