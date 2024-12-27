package com.nksolucoes.nkorderprocessms.core.interactors;

import com.nksolucoes.nkorderprocessms.core.exceptions.customer.DuplicateCustomerException;
import com.nksolucoes.nkorderprocessms.core.exceptions.customer.NotFoundCustomerException;
import com.nksolucoes.nkorderprocessms.core.repositories.CustomerRepository;
import com.nksolucoes.nkorderprocessms.transportlayer.documentacao.model.Customer;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CustomerInteractor {

	private final CustomerRepository customerRepository;

	public Customer createCustomer(Customer customer) {
		customerRepository.findByDocumentAndName(customer.getDocument(), customer.getName())
				.stream().findFirst().ifPresent(existingCustomer -> {
					throw new DuplicateCustomerException(
							String.format("Customer with document '%s' and name '%s' already exists.",
									customer.getDocument(), customer.getName()));
					});

		return this.customerRepository.createCustomer(customer);
	}

	public Page<Customer> listAllCustomers(Pageable pageable) {
		return this.customerRepository.listAllCustomers(pageable);
	}

	public Customer getCustomerById(String customerId) {
		Optional<Customer> customerData = this.customerRepository.findById(customerId);
		if (customerData.isEmpty()) {
			throw new NotFoundCustomerException(
					String.format("Customer with id '%s' is not found.", customerId));
		}
		return customerData.get();
	}


	public Customer updateCustomer(Customer customer, String customerId) {
		Optional<Customer> customerData = this.customerRepository.findById(customerId);

		if (customerData.isPresent()) {
			Customer updateCustomer = new Customer();
			updateCustomer.setId(customerData.get().getId());
			updateCustomer.setDocument(customer.getDocument());
			updateCustomer.setName(customer.getName());
			updateCustomer.setAddress(customer.getAddress());
			updateCustomer.setEmail(customer.getEmail());
			updateCustomer.setPhoneNumber(customer.getPhoneNumber());
			return this.customerRepository.updateCustomer(updateCustomer);
		} else {
			throw new RuntimeException("Customer not found");
		}
	}

	public ResponseEntity<Void> deleteCustomer(String customerId) {
		Customer customerData = this.customerRepository.findById(customerId).get();
		if(!Objects.isNull(customerData)) {
			this.customerRepository.deleteCustomer(customerId);
			return ResponseEntity.noContent().build();
		}else{
			throw new RuntimeException("Unable to find customer for delete");
		}

	}
}
