package com.nksolucoes.nkorderprocessms.transport.api;

import com.nksolucoes.nkorderprocessms.core.interactors.CustomerInteractor;
import com.nksolucoes.nkorderprocessms.transport.mapper.CustomerMapper;
import com.nksolucoes.nkorderprocessms.transportlayer.documentacao.model.Customer;
import com.nksolucoes.nkorderprocessms.transportlayer.documentacao.model.CustomerInput;
import com.nksolucoes.nkorderprocessms.transportlayer.documentacao.openapi.CustomerApi;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RequiredArgsConstructor
@RestController
public class CustomerHttp implements CustomerApi {

	private final CustomerInteractor customerInteractor;

	@Override
	public ResponseEntity<Customer> createCustomer(CustomerInput customerInput) {
		return ResponseEntity.status(HttpStatus.CREATED).body(customerInteractor.createCustomer(CustomerMapper.INSTANCE.mapCustomerInputToCustomer(customerInput)));
	}

	@Override
	public ResponseEntity<List<Customer>> listCustomer() {
		return ResponseEntity.ok(customerInteractor.listAllCustomers());
	}

	@Override
	public ResponseEntity<Customer> getCustomerById(String customerId) {
		return ResponseEntity.ok(customerInteractor.getCustomerById(customerId));
	}

	@Override
	public ResponseEntity<Customer> updateCustomer(String customerId, CustomerInput customerInput) {
		Customer customer = customerInteractor.updateCustomer(CustomerMapper.INSTANCE.mapCustomerInputToCustomer(customerInput), customerId);
		return ResponseEntity.status(HttpStatus.OK).body(customer);
	}

	@Override
	public ResponseEntity<Void> deleteCustomer(String customerId) {
		return this.customerInteractor.deleteCustomer(customerId);
	}
}
