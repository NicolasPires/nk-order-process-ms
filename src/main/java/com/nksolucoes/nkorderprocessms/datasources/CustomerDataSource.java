package com.nksolucoes.nkorderprocessms.datasources;

import com.nksolucoes.nkorderprocessms.core.repositories.CustomerRepository;
import com.nksolucoes.nkorderprocessms.datasources.repositories.CustomerClientRepository;
import com.nksolucoes.nkorderprocessms.transportlayer.documentacao.model.Customer;
import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CustomerDataSource implements CustomerRepository {

	private final CustomerClientRepository customerClientRepository;

	@Override
	public Customer createCustomer(Customer customer) {
		return this.customerClientRepository.save(customer);
	}

	@Override
	public List<Customer> listAllCustomers() {
		return customerClientRepository.findAll();
	}

	@Override
	public Optional<Customer> findById(String customerId) {
		return customerClientRepository.findById(customerId);
	}

	@Override
	public Customer updateCustomer(Customer customer) {
		return this.customerClientRepository.save(customer);
	}

	@Override
	public void deleteCustomer(String customerId) {
		this.customerClientRepository.deleteById(customerId);
	}

	@Override
	public List<Customer> findByDocumentAndName(String document, String name) {
		return this.customerClientRepository.findByDocumentAndName(document, name);
	}
}
