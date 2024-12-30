package com.nksolucoes.nkorderprocessms.core.exceptions.order;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class NotFoundOrderException extends RuntimeException {
	public NotFoundOrderException(String message) {
		super(message);
	}
}
