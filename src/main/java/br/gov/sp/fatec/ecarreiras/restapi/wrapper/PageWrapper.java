/*
 * @(#)PageWrapper.java 1.0 1 02/01/2018
 *
 * Copyright (c) 2018, Fatec-Jessen Vidal. All rights reserved.
 * Fatec-Jessen Vidal proprietary/confidential. Use is subject to license terms.
 */

package br.gov.sp.fatec.ecarreiras.restapi.wrapper;

import org.springframework.data.domain.Page;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import br.gov.sp.fatec.ecarreiras.restapi.serializer.PageSerializer;
import lombok.Getter;

/**
 * A classe {@link PageWrapper} encapsula uma lista paginada de objetos
 * para serializacao.
 *
 * @author Marcelo
 * @version 1.0 02/01/2018
 */
@Getter
@JsonSerialize(using = PageSerializer.class)
public class PageWrapper<T> extends ListWrapper<T> {
	
	private final Integer remaningPages;
	private final Long totalElements;
	
	public PageWrapper(final Page<T> page) {
		super(page.getContent());
		this.remaningPages = page.getTotalPages() - page.getNumber() - 1;
		this.totalElements = page.getTotalElements();
	}
}