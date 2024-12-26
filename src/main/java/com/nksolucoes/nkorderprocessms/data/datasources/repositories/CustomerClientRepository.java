package com.nksolucoes.nkorderprocessms.data.datasources.repositories;

import com.nksolucoes.nkorderprocessms.transportlayer.documentacao.model.Customer;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface CustomerClientRepository extends MongoRepository<Customer, String> {}
