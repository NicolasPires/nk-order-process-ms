package com.nksolucoes.nkorderprocessms.transport.handler;

import com.nksolucoes.nkorderprocessms.core.exceptions.customer.DuplicateCustomerException;
import com.nksolucoes.nkorderprocessms.core.exceptions.item.DuplicateItemException;
import com.nksolucoes.nkorderprocessms.core.exceptions.customer.NotFoundCustomerException;
import com.nksolucoes.nkorderprocessms.core.exceptions.order.DuplicateOrderException;
import com.nksolucoes.nkorderprocessms.core.exceptions.item.NotFoundItemException;
import com.nksolucoes.nkorderprocessms.core.exceptions.order.NotFoundOrderException;
import jakarta.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

	@ExceptionHandler(DuplicateItemException.class)
	public ResponseEntity<Map<String, Object>> handleDuplicateItemException(DuplicateItemException ex, HttpServletRequest request) {
		return buildResponse(ex, HttpStatus.CONFLICT, request);
	}

	@ExceptionHandler(NotFoundItemException.class)
	public ResponseEntity<Map<String, Object>> handleNotFoundItemException(NotFoundItemException ex, HttpServletRequest request) {
		return buildResponse(ex, HttpStatus.NOT_FOUND, request);
	}

	@ExceptionHandler(DuplicateCustomerException.class)
	public ResponseEntity<Map<String, Object>> handleDuplicateCustomerException(DuplicateCustomerException ex, HttpServletRequest request) {
		return buildResponse(ex, HttpStatus.CONFLICT, request);
	}

	@ExceptionHandler(NotFoundCustomerException.class)
	public ResponseEntity<Map<String, Object>> handleNotFoundCustomerException(NotFoundCustomerException ex, HttpServletRequest request) {
		return buildResponse(ex, HttpStatus.NOT_FOUND, request);
	}

	@ExceptionHandler(NotFoundOrderException.class)
	public ResponseEntity<Map<String, Object>> handleNotFoundOrderException(NotFoundOrderException ex, HttpServletRequest request) {
		return buildResponse(ex, HttpStatus.NOT_FOUND, request);
	}

	@ExceptionHandler(DuplicateOrderException.class)
	public ResponseEntity<Map<String, Object>> handleDuplicateOrderException(DuplicateOrderException ex, HttpServletRequest request) {
		return buildResponse(ex, HttpStatus.NOT_FOUND, request);
	}

	private ResponseEntity<Map<String, Object>> buildResponse(Exception ex, HttpStatus status, HttpServletRequest request) {
		Map<String, Object> response = new HashMap<>();
		response.put("timestamp", LocalDateTime.now());
		response.put("status", status.value());
		response.put("error", status.getReasonPhrase());
		response.put("message", ex.getMessage());
		response.put("path", request.getRequestURI());

		return ResponseEntity.status(status).body(response);
	}
}
