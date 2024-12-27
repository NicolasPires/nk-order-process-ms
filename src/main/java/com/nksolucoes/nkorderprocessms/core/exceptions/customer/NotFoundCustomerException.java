package com.nksolucoes.nkorderprocessms.core.exceptions.customer;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class NotFoundCustomerException extends RuntimeException {
	public NotFoundCustomerException(String message) {
		super(message);
	}
}
