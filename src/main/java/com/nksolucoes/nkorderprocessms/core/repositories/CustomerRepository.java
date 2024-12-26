package com.nksolucoes.nkorderprocessms.core.repositories;

import com.nksolucoes.nkorderprocessms.transportlayer.documentacao.model.Customer;
import java.util.List;
import java.util.Optional;

public interface CustomerRepository {

	Customer createCustomer(Customer customer);

	List<Customer> listAllCustomers();

	Optional<Customer> findById(String customerId);

	Customer updateCustomer(Customer customer);

	void deleteCustomer(String customerId);
}
