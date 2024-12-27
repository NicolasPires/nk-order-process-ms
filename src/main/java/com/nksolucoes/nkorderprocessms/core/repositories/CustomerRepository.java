package com.nksolucoes.nkorderprocessms.core.repositories;

import com.nksolucoes.nkorderprocessms.transportlayer.documentacao.model.Customer;
import java.util.List;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface CustomerRepository {

	Customer createCustomer(Customer customer);

	Page<Customer> listAllCustomers(Pageable pageable);

	Optional<Customer> findById(String customerId);

	Customer updateCustomer(Customer customer);

	void deleteCustomer(String customerId);

	List<Customer> findByDocumentAndName(String document, String name);
}
