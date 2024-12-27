package com.nksolucoes.nkorderprocessms.transport.api;

import com.nksolucoes.nkorderprocessms.core.interactors.CustomerInteractor;
import com.nksolucoes.nkorderprocessms.transport.mapper.CustomerMapper;
import com.nksolucoes.nkorderprocessms.transportlayer.documentacao.model.Customer;
import com.nksolucoes.nkorderprocessms.transportlayer.documentacao.model.CustomerInput;
import com.nksolucoes.nkorderprocessms.transportlayer.documentacao.model.ListCustomers200Response;
import com.nksolucoes.nkorderprocessms.transportlayer.documentacao.openapi.CustomerApi;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RequiredArgsConstructor
@RestController
public class CustomerHttp implements CustomerApi {

	private final CustomerInteractor customerInteractor;

	private final CustomerMapper customerMapper;

	@Override
	public ResponseEntity<Customer> createCustomer(CustomerInput customerInput) {
		return ResponseEntity.status(HttpStatus.CREATED).body(customerInteractor.createCustomer(customerMapper.mapToCustomer(customerInput)));
	}

	@Override
	public ResponseEntity<ListCustomers200Response> listCustomers(Integer page, Integer size) {
		int currentPage = (page != null) ? page : 0;
		int pageSize = (size != null) ? size : 10;

		Pageable pageable = PageRequest.of(currentPage, pageSize);

		Page<Customer> customerPage = customerInteractor.listAllCustomers(pageable);

		List<Customer> content = customerMapper.toListCustomers200Response(customerPage).getContent();

		ListCustomers200Response response = new ListCustomers200Response()
				.content(content)
				.number(customerPage.getNumber())
				.size(customerPage.getSize())
				.totalElements((int) customerPage.getTotalElements())
				.totalPages(customerPage.getTotalPages());

		return ResponseEntity.ok(response);
	}

	@Override
	public ResponseEntity<Customer> getCustomerById(String customerId) {
		return ResponseEntity.ok(customerInteractor.getCustomerById(customerId));
	}

	@Override
	public ResponseEntity<Customer> updateCustomer(String customerId, CustomerInput customerInput) {
		Customer customer = customerInteractor.updateCustomer(customerMapper.mapToCustomer(customerInput), customerId);
		return ResponseEntity.status(HttpStatus.OK).body(customer);
	}

	@Override
	public ResponseEntity<Void> deleteCustomer(String customerId) {
		return this.customerInteractor.deleteCustomer(customerId);
	}
}
