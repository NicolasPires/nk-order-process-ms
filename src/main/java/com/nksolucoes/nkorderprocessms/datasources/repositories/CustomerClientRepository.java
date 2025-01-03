package com.nksolucoes.nkorderprocessms.datasources.repositories;

import com.nksolucoes.nkorderprocessms.transportlayer.documentacao.model.Customer;
import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;


public interface CustomerClientRepository extends MongoRepository<Customer, String> {
	List<Customer> findByDocumentAndName(String document, String name);
	Page<Customer> findAll(Pageable pageable);
}
