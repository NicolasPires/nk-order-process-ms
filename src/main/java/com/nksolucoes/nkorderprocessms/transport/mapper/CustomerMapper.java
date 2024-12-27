package com.nksolucoes.nkorderprocessms.transport.mapper;

import com.nksolucoes.nkorderprocessms.transportlayer.documentacao.model.Customer;
import com.nksolucoes.nkorderprocessms.transportlayer.documentacao.model.CustomerInput;
import com.nksolucoes.nkorderprocessms.transportlayer.documentacao.model.ListCustomers200Response;
import java.util.List;
import org.mapstruct.Mapper;
import org.springframework.data.domain.Page;

@Mapper(componentModel = "spring")
public interface CustomerMapper {

	Customer mapToCustomer(CustomerInput customerInput);

	Customer mapToCustomerResponse(Customer customer);

	default List<Customer> mapToCustomerResponseList(List<Customer> customers) {
		return customers.stream().map(this::mapToCustomerResponse).toList();
	}

	default ListCustomers200Response toListCustomers200Response(Page<Customer> customerPage) {
		ListCustomers200Response response = new ListCustomers200Response();
		response.setContent(mapToCustomerResponseList(customerPage.getContent()));
		response.setNumber(customerPage.getNumber());
		response.setSize(customerPage.getSize());
		response.setTotalElements((int) customerPage.getTotalElements());
		response.setTotalPages(customerPage.getTotalPages());
		return response;
	}
}

