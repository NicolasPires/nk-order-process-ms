package com.nksolucoes.nkorderprocessms.transport.mapper;

import com.nksolucoes.nkorderprocessms.transportlayer.documentacao.model.Customer;
import com.nksolucoes.nkorderprocessms.transportlayer.documentacao.model.CustomerInput;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface CustomerMapper {
	CustomerMapper INSTANCE = Mappers.getMapper(CustomerMapper.class);

	Customer mapCustomerInputToCustomer(CustomerInput customerInput);
}
