package com.nksolucoes.nkorderprocessms.core.exceptions.item;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.CONFLICT)
public class DuplicateItemException extends RuntimeException {
	public DuplicateItemException(String message) {
		super(message);
	}
}
