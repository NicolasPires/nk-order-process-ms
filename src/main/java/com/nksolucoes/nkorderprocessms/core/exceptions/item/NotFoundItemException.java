package com.nksolucoes.nkorderprocessms.core.exceptions.item;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class NotFoundItemException extends RuntimeException {
	public NotFoundItemException(String message) {
		super(message);
	}
}
