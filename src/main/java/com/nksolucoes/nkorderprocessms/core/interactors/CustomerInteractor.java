package com.nksolucoes.nkorderprocessms.core.interactors;

import com.nksolucoes.nkorderprocessms.core.entity.CustomerEntity;
import com.nksolucoes.nkorderprocessms.core.repositories.CustomerRepository;
import com.nksolucoes.nkorderprocessms.transportlayer.documentacao.model.Customer;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CustomerInteractor {

	private final CustomerRepository customerRepository;

	public Customer createCustomer(Customer customer) {
		return this.customerRepository.createCustomer(customer);
	}

	public List<Customer> listAllCustomers() {
		return this.customerRepository.listAllCustomers();
	}

	public Customer getCustomerById(String customerId) {
		Optional<Customer> customerData = this.customerRepository.findById(customerId);
		if (customerData.isEmpty()) {
			new RuntimeException("Customer not found");
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
