package com.nksolucoes.nkorderprocessms.core.exceptions.order;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.CONFLICT)
public class DuplicateOrderException extends RuntimeException {
	public DuplicateOrderException(String message) {
		super(message);
	}
}
